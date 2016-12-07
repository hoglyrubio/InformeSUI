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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static com.empresaprivadaservicios.informesui.informe.InformeConstantes.ESTADO_NORMAL;
import static com.empresaprivadaservicios.informesui.informe.InformeConstantes.ESTADO_SOLO_INTERESES;
import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

@Service
public class SuiAcueductoService {

  private static final Logger LOG = LoggerFactory.getLogger(SuiAcueductoService.class);

  private static final int SCALE = 4;

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
      checkTotalFacturado(sui);
      acueductoRepository.save(sui);
    }
    return informes.size();
  }

  private SuiAcueducto transform(Informe informe, Periodo periodo) {
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
    Days peridias = Days.daysBetween(new LocalDate(periodo.getPerifein()), new LocalDate(periodo.getPerifefi()));
    sui.setC14(peridias.getDays());

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
    sui.setC23(new BigDecimal(informe.getInfocons()));

    Liquidacion liquidacion = liquidacionService.process(informe.getInfocons(), suiCateSuca, periodo);

    // 24. Cargo Fijo
    sui.setC24( liquidacion.getTarifaCargoFijoPlena() );
    // 25. Consumo basico
    sui.setC25( informe.getInfocons() > 0 ? liquidacion.getTarifaConsumoBasicoPlena() : ZERO );
    // 26. Consumo complementario
    sui.setC26( informe.getInfocons() > 0 ? liquidacion.getTarifaConsumoComplementarioPlena() : ZERO );
    // 27. Consumo suntuario
    sui.setC27( informe.getInfocons() > 0 ? liquidacion.getTarifaConsumoSuntuarioPlena() : ZERO );
    // 28. CMT
    sui.setC28(ZERO);
    // 29. Valor por metro cubico
    if (informe.getInfocons() > 0) {
      sui.setC29( informe.getInfocbas()
        .add(informe.getInfoccom())
        .add(informe.getInfocsun())
        .divide(new BigDecimal(informe.getInfocons()), SCALE, ROUND_HALF_UP) );
    } else {
      sui.setC29(ZERO);
    }
    // 30. Valor facturado por consumo
    sui.setC30(liquidacion.valorConsumo());
    // 31. Valor subsidio
    sui.setC31( liquidacion.totalSubsidio() );
    // 32. Valor contribucion
    sui.setC32( liquidacion.totalContribucion() );
    // 33. Factor subsidio o contribucion
    sui.setC33( liquidacion.getSuapCargoFijo().divide(liquidacion.getValorCargoFijo(), 3, ROUND_HALF_UP) );
    // 34. Factor subsidio o contribucion consumo
    if (liquidacion.valorConsumo().compareTo(ZERO) != 0) {
      sui.setC34( liquidacion.suapConsumo().divide(liquidacion.valorConsumo(), 3, ROUND_HALF_UP) );
    } else {
      sui.setC34(ZERO);
    }

    // 35. Cargo por conexion // NO EXISTE
    sui.setC35(ZERO);
    // 36. Cargo por Reconexion // 50% de INFODURE
    sui.setC36( informe.getInfosure().divide(new BigDecimal(2), SCALE, ROUND_HALF_UP));
    // 37. Cargo por Reinstalacion // NO EXISTE
    sui.setC37(ZERO);
    // 38. Cargo por suspension // 50% de INFODURE
    sui.setC38( informe.getInfosure().divide(new BigDecimal(2), SCALE, ROUND_HALF_UP));
    // 39. Cargo por corte
    sui.setC39(ZERO);
    // 40. Pago anticipado del servicio
    sui.setC40(ZERO);
    // 41. Dias de mora.
    sui.setC41( informe.getInfonuat() * 30 );
    // 42. Valor de mora
    sui.setC42( informe.getInforeag() );
    // 43. Intereses por mora
    sui.setC43( informe.getInfoinag() );
    // 44. Otros Cobros

    sui.setC44( informe.getInfoinan() // Intereses anteriores
      .add(informe.getInforeva()) // Refacturado
      .add(informe.getInfoinva()) // Intereses varios
      .add(informe.getInfomedi())
      .add(informe.getInfotanq())
      .add(informe.getInfoacom())
      .add(informe.getInfootca())
      .subtract(informe.getInfoajus()) );

    // MACHETE
    /*if (informe.getInformePk().getInfocodi() == 20050) {
      sui.setC44( sui.getC31() );
      sui.setC31(ZERO);
      sui.setC24(ZERO);
    }*/

    // En el 201105 se present Refacturado negativo, lo sumamos a otros cobros.
    if (sui.getC42().compareTo(ZERO) < 0) {
      sui.setC44(sui.getC44().add(sui.getC42()));
      sui.setC42(ZERO);
    }

    // Por si los intereses son negativos
    if (sui.getC43().compareTo(ZERO) < 0) {
      sui.setC44(sui.getC44().add(sui.getC43()));
      sui.setC43(ZERO);
    }

    //  Dias de mora por si no tiene y hay mora
    if (sui.getC42().compareTo(ZERO) > 0 && sui.getC41() == 0) {
      sui.setC41(30);
    }

    // En algunos meses del 2011 se presenta que hay intereses pero no refacturado,
    // por lo tanto se suma este Interes por mora a Otros cobros.
    if (sui.getC42().compareTo(ZERO) == 0 && sui.getC43().compareTo(ZERO) != 0) {
      sui.setC44(sui.getC44().add(sui.getC43()));
      sui.setC43(ZERO);
    }

    // 45. Causal de Refacturacion
    sui.setC45(0);
    // 46. Numero de factura objeto de refacturacion
    sui.setC46(null);
    // 47.Valor total facturado ACUEDUCTO
    sui.setC47(informe.getInfovaim().subtract(informe.getInfoalca().add(informe.getInfoinal()).add(informe.getInforeal())));

    // Ajuste de los totales
    BigDecimal total = sui.getC24()
      .add(sui.getC30())
      .subtract(sui.getC31())
      .add(sui.getC32())
      .add(sui.getC35())
      .add(sui.getC36())
      .add(sui.getC37())
      .add(sui.getC38())
      .add(sui.getC39())
      .add(sui.getC42())
      .add(sui.getC43())
      .add(sui.getC44())
      .subtract(sui.getC40());

    sui.setC44(sui.getC44().add(sui.getC47()).subtract(total));

    // 48. Pagos del usuario durante el mes de reporte, se calcula para Acuedcucto
    if (informe.getInfovapa().compareTo(sui.getC47()) > 0) {
      sui.setC48(sui.getC47());
    } else {
      sui.setC48(informe.getInfovapa());
    }

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
      throw new BusinessException(MessageFormat.format("El código <{0}> tiene un estado inválido <{1}>.",
              informe.getInformePk().getInfocodi(), informe.getInfoesta()));
    }

    if (suiCateSuca == null) {
      throw new BusinessException(MessageFormat.format("El codigo <{0}> no tiene homologación para Uso <{1}> y Estrato <{2}>",
              informe.getInformePk().getInfocodi(), informe.getInfocate(), informe.getInfosuca()));
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
    BigDecimal valorCF = informe.getInfocafi();
    Tarifa tarifaCF = tarifaRepository.findTarifaCFByValue(ano, mes, valorCF.doubleValue());
    if (tarifaCF == null) {
      LOG.warn("No se encuentra tarifa de CF. Codigo {} Valor Cargo fijo {} (Uso {} Est {}). Se usará tarifa media",
        informe.getInformePk().getInfocodi(), informe.getInfocafi(), informe.getInfocate(), informe.getInfosuca());
      tarifaCF = tarifaRepository.findOne(ano, mes, SuiConstantes.TARICLUS_PLENA, SuiConstantes.TARICODI_CARGO_FIJO);
    }
    return tarifaCF;
  }

  public String obtainCsvLines(Integer pericodi) {
    List<SuiAcueducto> suiAcueductos = acueductoRepository.findBySuiAcueductoPk_periodo(pericodi);
    LOG.info("Acueductos: {}", suiAcueductos.size());
    return suiAcueductos
      .stream()
      .map(suiAcueducto -> SuiHelper.toCsv(suiAcueducto))
      .collect(Collectors.joining("\n"));
  }

  private void checkTotalFacturado(SuiAcueducto suiAcueducto) {
    BigDecimal sum = suiAcueducto.getC24()
      .add(suiAcueducto.getC30())
      .subtract(suiAcueducto.getC31())
      .add(suiAcueducto.getC32())
      .add(suiAcueducto.getC35())
      .add(suiAcueducto.getC36())
      .add(suiAcueducto.getC37())
      .add(suiAcueducto.getC38())
      .add(suiAcueducto.getC39())
      .add(suiAcueducto.getC42())
      .add(suiAcueducto.getC43())
      .add(suiAcueducto.getC44())
      .subtract(suiAcueducto.getC40());

    if (sum.compareTo(suiAcueducto.getC47()) != 0) {
      System.err.println("Diferencia de total facturado: " + suiAcueducto.getC47() + " vs. sumatoria: " + sum);
    }
  }


}
