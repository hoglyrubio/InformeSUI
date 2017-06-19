package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.BusinessException;
import com.empresaprivadaservicios.informesui.informe.Informe;
import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.tarifa.Tarifa;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Optional;

import static com.empresaprivadaservicios.informesui.informe.InformeConstantes.ESTADO_NORMAL;
import static com.empresaprivadaservicios.informesui.informe.InformeConstantes.ESTADO_SOLO_INTERESES;

@Component
public class SuiCommon {

  private static final Logger LOG = LoggerFactory.getLogger(SuiCommon.class);

  private final SuiCateSucaRepository suiCateSucaRepository;
  private final TarifaRepository tarifaRepository;

  @Autowired
  public SuiCommon(SuiCateSucaRepository suiCateSucaRepository, TarifaRepository tarifaRepository) {
    this.suiCateSucaRepository = suiCateSucaRepository;
    this.tarifaRepository = tarifaRepository;
  }

  /**
   * Obtiene el Uso y Estrato de acuerdo con la homologación en la tabla SUI_CATESUCA, tiene soporte para las dos
   * versiones de datos que usan R o 1 para el Uso desde el año 2014
   * @param informe
   * @param periodo
   * @return
   */
  public SuiCateSuca obtainSuiCateSuca(Informe informe, Periodo periodo) {

    SuiCateSuca suiCateSuca = null;

    Tarifa tarifaCF = obtainTarifaCargoFijoCobrada(informe, periodo);
    Integer uso = tarifaCF.getTarifaPk().getTaricate();
    Integer est = tarifaCF.getTarifaPk().getTarisuca();

    if (ESTADO_NORMAL.equals(informe.getInfoesta())) {
      suiCateSuca = suiCateSucaRepository.findOne(new SuiCateSucaPk(uso.toString(), est.toString()));
    } else if (ESTADO_SOLO_INTERESES.equals(informe.getInfoesta())) {
      suiCateSuca = Optional
        .ofNullable(suiCateSucaRepository.findOne(new SuiCateSucaPk(informe.getInfocate(), informe.getInfosuca())))
        .orElse(suiCateSucaRepository.findOne(new SuiCateSucaPk(uso.toString(), est.toString())));
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
  public Tarifa obtainTarifaCargoFijoCobrada(Informe informe, Periodo periodo) {
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
}
