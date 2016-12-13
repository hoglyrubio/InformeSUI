package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.informe.Informe;
import com.empresaprivadaservicios.informesui.informe.InformeRepository;
import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.periodo.PeriodoRepository;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.empresaprivadaservicios.informesui.sui.SuiConstantes.*;
import static com.empresaprivadaservicios.informesui.sui.SuiConstantes.SIN_AFORO_ALCANTARILLADO;
import static com.empresaprivadaservicios.informesui.sui.SuiHelper.isNegative;
import static com.empresaprivadaservicios.informesui.sui.SuiHelper.isNonZero;
import static com.empresaprivadaservicios.informesui.sui.SuiHelper.isPositive;
import static com.empresaprivadaservicios.informesui.sui.SuiHelper.isZero;
import static java.math.BigDecimal.ZERO;

@Service
public class SuiAlcantarilladoService {

  private static final Logger LOG = LoggerFactory.getLogger(SuiAlcantarilladoService.class);

  private static final int SCALE = 4;

  private final InformeRepository informeRepository;
  private final SuiAlcantarilladoRepository alcantarilladoRepository;
  private final PeriodoRepository periodoRepository;
  private final TarifaRepository tarifaRepository;
  private final SuiCateSucaRepository suiCateSucaRepository;
  private final LiquidacionService liquidacionService;
  private final SuiCommon suiCommon;

  @Autowired
  public SuiAlcantarilladoService(InformeRepository informeRepository, SuiAlcantarilladoRepository alcantarilladoRepository,
                                  PeriodoRepository periodoRepository, TarifaRepository tarifaRepository,
                                  SuiCateSucaRepository suiCateSucaRepository, LiquidacionService liquidacionService, SuiCommon suiCommon) {
    this.informeRepository = informeRepository;
    this.alcantarilladoRepository = alcantarilladoRepository;
    this.periodoRepository = periodoRepository;
    this.tarifaRepository = tarifaRepository;
    this.suiCateSucaRepository = suiCateSucaRepository;
    this.liquidacionService = liquidacionService;
    this.suiCommon = suiCommon;
  }

  @Transactional
  public Integer process(Integer pericodi) {
    Periodo periodo = periodoRepository.findOne(pericodi);
    alcantarilladoRepository.deleteBySuiAlcantarilladoPk_periodo(pericodi);
    List<Informe> informes = informeRepository.findByInformePk_infoperi(pericodi);

    informes.forEach(informe -> {
      LOG.info("Processing ALC: {} {}", pericodi, informe.getInformePk().getInfocodi());
      SuiAlcantarillado sui = transform(informe, periodo);
      checkTotalFacturado(sui);
      alcantarilladoRepository.save(sui);
    });

    return informes.size();
  }

  private SuiAlcantarillado transform(Informe informe, Periodo periodo) {
    // 1. Nuid
    SuiAlcantarillado sui = new SuiAlcantarillado(informe.getInformePk().getInfoperi(), informe.getInformePk().getInfocodi());
    // 2. Numero de cuenta o contrato
    sui.setC02(informe.getInformePk().getInfocodi());
    // 3. Codigo dane depto
    sui.setC03(DANE_DEPARTAMENTO);
    // 4. Codigo dane mpio
    sui.setC04(DANE_MUNICIPIO);
    // 5. Zona IGAC
    sui.setC05(IGAC_ZONA);
    // 6. Sector IGAC
    sui.setC06(IGAC_SECTOR);
    // 7. Manzana o vereda IGAC
    sui.setC07(IGAC_MANZANA);
    // 8. Numero del predio IGAC
    sui.setC08(IGAC_NUMERO);
    // 9. Condicion de propiedad del predio IGAC
    sui.setC09(IGAC_CONDICION);
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

    SuiCateSuca suiCateSuca = suiCommon.obtainSuiCateSuca(informe, periodo);

    // 15. Codigo clase de uso
    sui.setC15(suiCateSuca.getCasucodi());
    // 16. Unidades multiusuario residenciales
    sui.setC16(null);
    // 17. Unidades multiusuario no residenciales
    sui.setC17(null);
    // 18. Hogar comunitario o sustituto
    sui.setC18(NO_ES_HOGAR_COMUNITARIO_SUSTITUTO);
    // 19. Usuarios facturados con aforo
    sui.setC19(SIN_AFORO_ALCANTARILLADO);
    // 20. Usuario cuenta con caracterizacion
    sui.setC20(CON_CARACTERIZACION_ALCANTARILLADO);
    // 21. Cargo fijo
    sui.setC21(informe.getInfoalca());
    // 22. Cargo por vertimiento basico
    sui.setC22(ZERO);
    // 23. Cargo por vertimiento complementario
    sui.setC23(ZERO);
    // 24. Cargo por vertimiento suntuario
    sui.setC24(ZERO);
    // 25. CMT
    sui.setC25(ZERO);
    // 26. Vertimiento del periodo en metros cubicos
    sui.setC26(ZERO);
    // 27. Valor facturado por vertimiento
    sui.setC27(ZERO);
    // 28. Valor subsidio
    sui.setC28(ZERO);
    // 29. Valor contribucion
    sui.setC29(ZERO);
    // 30. Factor subsidio o contribucion cargp fijo
    sui.setC30(ZERO);
    // 31. Factor subsidio o contribucion por vertimiento
    sui.setC31(ZERO);
    // 32. Cargo por conexion
    sui.setC32(ZERO);
    // 33. Pago anticipado por servicio
    sui.setC33(ZERO);
    // 34. Dias de mora
    sui.setC34( informe.getInfonuat() * 30 );
    // 35. Valor por mora
    sui.setC35(informe.getInforeal());
    // 36. Intereses por mora
    sui.setC36(informe.getInfoinal());
    // 37. Otros cobros
    sui.setC37(ZERO);

    // Por si el Refacturado es negativo, se agrega a Otros cobros.
    if (isNegative(sui.getC35())) {
      sui.setC37(sui.getC37().add(sui.getC35()));
      sui.setC35(ZERO);
    }

    // Por si el Valor en Mora es negativo, se agrega a Otros cobros.
    if (isNegative(sui.getC36())) {
      sui.setC37(sui.getC37().add(sui.getC36()));
      sui.setC36(ZERO);
    }

    //  Dias de mora por si no tiene y hay mora
    if (isPositive(sui.getC35()) && sui.getC34() == 0) {
      sui.setC34(30);
    }

    // En algunos meses del 2011 se presentan Intereses por mora sin haber Refacturado
    // por lo tanto, se suman a Otros cobros.
    if (isZero(sui.getC35()) && isNonZero(sui.getC36())) {
      sui.setC37(sui.getC37().add(sui.getC36()));
      sui.setC36(ZERO);
    }

    // 38. Causal refacturacion
    sui.setC38(0);

    // 39. Numero de factura objeto de refacturacion
    sui.setC39(null);

    // 40. Valor total facturado
    sui.setC40(sui.getC21()
      .add(sui.getC35())
      .add(sui.getC36())
      .add(sui.getC37())
    );

    // 41. Pagos del usuario recibidos durante el mes de reporte
    sui.setC41(informe.getInfovapa()
      .subtract(informe.getInfovaim()
        .subtract(sui.getC40())
      )
    );
    if (isNegative(sui.getC41())) {
      sui.setC41(ZERO);
    }

    return sui;
  }

  private void checkTotalFacturado(SuiAlcantarillado sui) {
    // TODO: Completar validacion del total facturado
  }

  public String obtainCsvLines(Integer pericodi) {
    List<SuiAlcantarillado> suiAlcantarillados = alcantarilladoRepository.findBySuiAlcantarilladoPk_periodo(pericodi);
    return suiAlcantarillados
      .stream()
      .map(suiAlcantarillado -> SuiHelper.toCsv(suiAlcantarillado))
      .collect(Collectors.joining("\n"));
  }
}
