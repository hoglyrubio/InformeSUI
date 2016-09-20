package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.BusinessException;
import com.empresaprivadaservicios.informesui.informe.Informe;
import com.empresaprivadaservicios.informesui.informe.InformeRepository;
import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.periodo.PeriodoRepository;
import com.empresaprivadaservicios.informesui.tarifa.Tarifa;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.text.MessageFormat;
import java.util.List;

import static com.empresaprivadaservicios.informesui.informe.InformeConstantes.ESTADO_NORMAL;
import static com.empresaprivadaservicios.informesui.informe.InformeConstantes.ESTADO_SOLO_INTERESES;

@Service
public class SuiAcueductoService {

  private static final Logger LOG = LoggerFactory.getLogger(SuiAcueductoService.class);

  private final InformeRepository informeRepository;
  private final SuiAcueductoRepository acueductoRepository;
  private final PeriodoRepository periodoRepository;
  private final TarifaRepository tarifaRepository;
  private final SuiCateSucaRepository suiCateSucaRepository;
  private final LiquidacionService liquidacionService;

  @Autowired
  public SuiAcueductoService(InformeRepository informeRepository, SuiAcueductoRepository acueductoRepository,
                             PeriodoRepository periodoRepository, TarifaRepository tarifaRepository,
                             SuiCateSucaRepository suiCateSucaRepository, LiquidacionService liquidacionService) {
    this.informeRepository = informeRepository;
    this.acueductoRepository = acueductoRepository;
    this.periodoRepository = periodoRepository;
    this.tarifaRepository = tarifaRepository;
    this.suiCateSucaRepository = suiCateSucaRepository;
    this.liquidacionService = liquidacionService;
  }

  @Transactional
  public Integer process(Integer infoperi) {
    Periodo periodo = periodoRepository.findOne(infoperi);
    acueductoRepository.deleteBySuiAcueductoPk_periodo(infoperi);

    List<Informe> informes = informeRepository.findByInformePk_infoperi(infoperi);

    for (Informe informe : informes) {
      LOG.info("Procesing: {}", informe.getInformePk().getInfocodi() );
      SuiAcueducto sui = transform(informe, periodo);
      acueductoRepository.save(sui);
    }
    return informes.size();
  }

  private SuiAcueducto transform(Informe informe, Periodo periodo) {
    StopWatch stopWatch = new StopWatch(informe.getInformePk().getInfocodi().toString());

    stopWatch.start("Fase 1");

    // 1. Nuid
    SuiAcueducto sui = new SuiAcueducto(informe.getInformePk().getInfoperi(), informe.getInformePk().getInfocodi());
    // 2. Numero de cuenta o contrato
    sui.setC02(informe.getInformePk().getInfocodi());
    // 3. Codigo dane depto
    sui.setC03(SuiConstantes.DANE_DEPARTAMENTO);
    // 4. Codigo dane mpio
    sui.setC04(SuiConstantes.DANE_MUNICIPIO);
    // 5. Zona IGAC
    sui.setC05(SuiConstantes.IGAC_ZONA);
    // 6. Sector IGAC
    sui.setC06(SuiConstantes.IGAC_SECTOR);
    // 7. Manzana o vereda IGAC
    sui.setC07(SuiConstantes.IGAC_MANZANA);
    // 8. Numero del predio IGAC
    sui.setC08(SuiConstantes.IGAC_NUMERO);
    // 9. Condicion de propiedad del predio IGAC
    sui.setC09(SuiConstantes.IGAC_CONDICION);
    // 10. Direccion
    sui.setC10(informe.getInfodire());
    // 11. Numero de factura
    sui.setC11(informe.getInforeci());
    // 12. Fecha expedicion factura
    sui.setC12(periodo.getPerifege());
    // 13. fecha inicio periodo
    sui.setC13(periodo.getPerifein());
    // 14. Dias facturados
    Days peridias = Days.daysBetween(new LocalDate(periodo.getPerifefi()), new LocalDate(periodo.getPerifein()));
    sui.setC14(peridias.getDays());

    stopWatch.stop();
    stopWatch.start("Fase 2");

    SuiCateSuca suiCateSuca = obtainSuiCateSuca(informe, periodo);

    // 15. Codigo clase de uso
    sui.setC15(suiCateSuca.getCasucodi());
    // 16. Unidades multiusuario residenciales
    sui.setC16(null);
    // 17. Unidades multiusuario no residenciales
    sui.setC17(null);
    // 18. Hogar comunitario o sustituto
    sui.setC18(SuiConstantes.NO_ES_HOGAR_COMUNITARIO_SUSTITUTO);
    // 19. Estado del medidor
    sui.setC19( SuiHelper.isResidencial(suiCateSuca) ? SuiEstadoMedidor.BUEN_ESTADO.value() : SuiEstadoMedidor.DANADO.value());
    // 20. Determinacion del consumo
    sui.setC20( SuiHelper.isResidencial(suiCateSuca) ? SuiDeterminacionConsumo.LEIDO.value() : SuiDeterminacionConsumo.PROMEDIO.value());
    // 21. Lectura Anterior
    sui.setC21(informe.getInfoleac() - informe.getInfocons());
    // 22. Lectura Actual
    sui.setC22(informe.getInfoleac());
    // 23. Consumo del periodo en m3
    sui.setC23(informe.getInfocons().doubleValue());

    stopWatch.stop();
    stopWatch.start("Fase 3 Liquidacion");

    Liquidacion liquidacion = liquidacionService.process(informe.getInfocons(), suiCateSuca, periodo);

    stopWatch.stop();
    stopWatch.start("Fase 4");

    // 24. Cargo Fijo
    sui.setC24( liquidacion.getTarifaCargoFijoPlena() );
    // 25. Consumo basico
    sui.setC25( informe.getInfocons() > 0 ? liquidacion.getTarifaConsumoBasicoPlena() : 0 );
    // 26. Consumo complementario
    sui.setC26( informe.getInfocons() > 0 ? liquidacion.getTarifaConsumoComplementarioPlena() : 0 );
    // 27. Consumo suntuario
    sui.setC27( informe.getInfocons() > 0 ? liquidacion.getTarifaConsumoSuntuarioPlena() : 0 );
    // 28. CMT
    sui.setC28(0D);
    // 29. Valor por metro cubico
    if (informe.getInfocons() > 0) {
      sui.setC29((informe.getInfocbas() + informe.getInfoccom() + informe.getInfocsun()) / informe.getInfocons());
    } else {
      sui.setC29(0D);
    }
    // 30. Valor facturado por consumo
    sui.setC30(liquidacion.valorConsumo());
    // 31. Valor subsidio
    sui.setC31( liquidacion.totalSubsidio() );
    // 32. Valor contribucion
    sui.setC32( liquidacion.totalContribucion() );
    // 33. Factor subsidio o contribucion
    sui.setC33( liquidacion.getSuapCargoFijo() / liquidacion.getValorCargoFijo() );
    // 34. Factor subsidio o contribucion consumo
    if (liquidacion.valorConsumo() != 0) {
      sui.setC34( liquidacion.suapConsumo() / liquidacion.valorConsumo() );
    } else {
      sui.setC34(0D);
    }

    // 35. Cargo por conexion // NO EXISTE
    sui.setC35(0D);
    // 36. Cargo por Reconexion // 50% de INFODURE
    sui.setC36( informe.getInfosure() / 2 );
    // 37. Cargo por Reinstalacion // NO EXISTE
    sui.setC37(0D);
    // 38. Cargo por suspension // 50% de INFODURE
    sui.setC38( informe.getInfosure() / 2 );
    // 39. Cargo por corte
    sui.setC39(0D);
    // 40. Pago anticipado del servicio
    sui.setC40(0D);
    // 41. Dias de mora.
    sui.setC41( informe.getInfonuat() * 30 );
    // 42. Valor de mora
    sui.setC42( informe.getInforeag() );
    // 43. Intereses por mora
    sui.setC43( informe.getInfoinag() );
    // 44. Otros Cobros
    sui.setC44( informe.getInfoinan() + // Intereses anteriores
            informe.getInforeva() + informe.getInfoinva() + // Refacturado e intereses Varios
            informe.getInfomedi() + informe.getInfotanq() + informe.getInfoacom() +
            informe.getInfootca() - informe.getInfoajus() );

    stopWatch.stop();
    stopWatch.start("Fase 4");

    // MACHETE
    if (informe.getInformePk().getInfocodi() == 20050) {
      sui.setC44( sui.getC31() );
      sui.setC31(0D);
      sui.setC24(0D);
    }

    // En el 201105 se present Refacturado negativo, lo sumamos a otros cobros.
    if (sui.getC42() < 0) {
      sui.setC44(sui.getC44() + sui.getC42());
      sui.setC42(0D);
    }

    // Por si los intereses son negativos
    if (sui.getC43() < 0) {
      sui.setC44(sui.getC44() + sui.getC43());
      sui.setC43(0D);
    }

    //  Dias de mora por si no tiene y hay mora
    if (sui.getC42() > 0 && sui.getC41() == 0) {
      sui.setC41(30);
    }

    // En algunos meses del 2011 se presenta que hay intereses pero no refacturado,
    // por lo tanto se suma este Interes por mora a Otros cobros.
    if (sui.getC42() == 0 && sui.getC43() != 0) {
      sui.setC44(sui.getC44() + sui.getC43());
      sui.setC43(0D);
    }

    stopWatch.stop();
    stopWatch.start("Fase 5");

    // 45. Causal de Refacturacion
    sui.setC45(0);
    // 46. Numero de factura objeto de refacturacion
    sui.setC46(null);
    // 47.Valor total facturado ACUEDUCTO
    sui.setC47(informe.getInfovaim() - (informe.getInfoalca() + informe.getInfoinal() + informe.getInforeal()));

    // Ajuste de los totales
    Double total = sui.getC24() + sui.getC30() - sui.getC31() + sui.getC32() + sui.getC35() + sui.getC36() +
            sui.getC37() + sui.getC38() + sui.getC39() + sui.getC42() + sui.getC43() + sui.getC44() - sui.getC40();

    sui.setC44(sui.getC44() + sui.getC47() - total);

    // 48. Pagos del usuario durante el mes de reporte, se calcula para Acuedcucto
    if (informe.getInfovapa() > sui.getC47()) {
      sui.setC48(sui.getC47());
    } else {
      sui.setC48(informe.getInfovapa());
    }

    stopWatch.stop();
    LOG.info(stopWatch.prettyPrint());
    return sui;
  }

  /**
   * Obtiene el Uso y Estrato de acuerdo con la homologación en la tabla SUI_CATESUCA, tiene soporte para las dos
   * versiones de datos que usan R o 1 para el Uso desde el año 2014
   * @param informe
   * @param periodo
   * @return
   */
  private SuiCateSuca obtainSuiCateSuca(Informe informe, Periodo periodo) {

    SuiCateSuca suiCateSuca = null;

    if (ESTADO_NORMAL.equals(informe.getInfoesta())) {
      Tarifa tarifaCF = obtainTarifaCargoFijoCobrada(informe, periodo);
      Integer uso = tarifaCF.getTarifaPk().getTaricate();
      Integer est = tarifaCF.getTarifaPk().getTarisuca();
      suiCateSuca = suiCateSucaRepository.findOne(new SuiCateSucaPk(uso.toString(), est.toString()));
    } else if (ESTADO_SOLO_INTERESES.equals(informe.getInfoesta())) {
      suiCateSuca = suiCateSucaRepository.findOne(new SuiCateSucaPk(informe.getInfocate(), informe.getInfosuca()));
    } else {
      throw new BusinessException(MessageFormat.format("El código <{0}> tiene un estado inválido <{0}>.",
              informe.getInformePk().getInfocodi().toString(), informe.getInfoesta()));
    }

    if (suiCateSuca == null) {
      throw new BusinessException(MessageFormat.format("El codigo <{0}> no tiene homologación para Uso <{1}> y Estrato <{2}>",
              informe.getInformePk().getInfocodi().toString(), informe.getInfoesta()));
    }

    return suiCateSuca;
  }

  /**
   *
   * @param informe
   * @param periodo
   * @return
   */
  private Tarifa obtainTarifaCargoFijoCobrada(Informe informe, Periodo periodo) {
    Integer ano = periodo.getPeriano();
    Integer mes = periodo.getPerimes();
    Double valorCF = informe.getInfocafi();
    Tarifa tarifaCF = tarifaRepository.findTarifaCFByValue(ano, mes, valorCF);
    if (tarifaCF == null) {
      throw new BusinessException(MessageFormat.format("No se encuentra tarifa de CF. Codigo {0} Valor Cargo fijo {1}",
              informe.getInformePk().getInfocodi().toString(), informe.getInfocafi()));
    }
    return tarifaCF;
  }

}
