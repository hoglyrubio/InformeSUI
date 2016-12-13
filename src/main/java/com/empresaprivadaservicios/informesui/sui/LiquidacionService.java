package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.tarifa.Tarifa;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.empresaprivadaservicios.informesui.sui.SuiConstantes.*;

@Service
public class LiquidacionService {

  private TarifaRepository tarifaRepository;

  @Autowired
  public LiquidacionService(TarifaRepository tarifaRepository) {
    this.tarifaRepository = tarifaRepository;
  }

  public Liquidacion process(Integer consumo, SuiCateSuca suiCateSuca, Periodo periodo) {

    Integer consumoBas = consumo > 20 ? 20 : consumo;
    Integer consumoCom = (consumo - consumoBas) > 20 ? 20 : (consumo - consumoBas);
    Integer consumoSun = (consumo - consumoBas - consumoCom) > 0 ? (consumo - consumoBas - consumoCom) : 0;

    Tarifa tarifaCF = tarifa(periodo, suiCateSuca.getCasucodi(), TARICODI_CARGO_FIJO); //tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), TARICODI_CARGO_FIJO);
    Tarifa tarifaCFPlena = tarifa(periodo, DEFAULT_CASUCODI, TARICODI_CARGO_FIJO); //tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), DEFAULT_CASUCODI, TARICODI_CARGO_FIJO);
    BigDecimal valorCargoFijo = tarifaCFPlena.getTarivalo();
    BigDecimal cobradoCargoFijo = tarifaCF.getTarivalo();
    BigDecimal suapCargoFijo = cobradoCargoFijo.subtract(valorCargoFijo);

    Tarifa tarifaCBas = tarifa(periodo, suiCateSuca.getCasucodi(), TARICODI_CONSUMO_BASICO); //tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), TARICODI_CONSUMO_BASICO);
    Tarifa tarifaCBasPlena = tarifa(periodo, DEFAULT_CASUCODI, TARICODI_CONSUMO_BASICO); //tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), DEFAULT_CASUCODI, TARICODI_CONSUMO_BASICO);
    BigDecimal valorConsumoBasico = tarifaCBasPlena.getTarivalo().multiply(new BigDecimal(consumoBas));
    BigDecimal cobradoConsumoBasico = tarifaCBas.getTarivalo().multiply(new BigDecimal(consumoBas));
    BigDecimal suapConsumoBasico = cobradoConsumoBasico.subtract(valorConsumoBasico);

    Tarifa tarifaCCom = tarifa(periodo, suiCateSuca.getCasucodi(), TARICODI_CONSUMO_COMPLEMENTARIO);//tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), TARICODI_CONSUMO_COMPLEMENTARIO);
    Tarifa tarifaCComPlena = tarifa(periodo, DEFAULT_CASUCODI, TARICODI_CONSUMO_COMPLEMENTARIO); //tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), DEFAULT_CASUCODI, TARICODI_CONSUMO_COMPLEMENTARIO);
    BigDecimal valorConsumoComplementario = tarifaCComPlena.getTarivalo().multiply(new BigDecimal(consumoCom));
    BigDecimal cobradoConsumoComplementario = tarifaCCom.getTarivalo().multiply(new BigDecimal(consumoCom));
    BigDecimal suapConsumoComplementario = cobradoConsumoComplementario.subtract(cobradoConsumoComplementario);

    Tarifa tarifaCSun = tarifa(periodo, suiCateSuca.getCasucodi(), TARICODI_CONSUMO_SUNTUARIO);//tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), TARICODI_CONSUMO_SUNTUARIO);
    Tarifa tarifaCSunPlena = tarifa(periodo, DEFAULT_CASUCODI, TARICODI_CONSUMO_SUNTUARIO); //tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), DEFAULT_CASUCODI, TARICODI_CONSUMO_SUNTUARIO);
    BigDecimal valorConsumoSuntuario = tarifaCSunPlena.getTarivalo().multiply(new BigDecimal(consumoSun));
    BigDecimal cobradoConsumoSuntuario = tarifaCSun.getTarivalo().multiply(new BigDecimal(consumoSun));
    BigDecimal suapConsumoSuntuario = cobradoConsumoSuntuario.subtract(valorConsumoSuntuario);

    Liquidacion liquidacion = new Liquidacion();

    liquidacion.setTarifaCargoFijo(tarifaCF.getTarivalo());
    liquidacion.setTarifaConsumoBasico((tarifaCBas.getTarivalo()));
    liquidacion.setTarifaConsumoComplementario(tarifaCCom.getTarivalo());
    liquidacion.setTarifaConsumoSuntuario(tarifaCSun.getTarivalo());

    liquidacion.setTarifaCargoFijoPlena(tarifaCFPlena.getTarivalo());
    liquidacion.setTarifaConsumoBasicoPlena((tarifaCBasPlena.getTarivalo()));
    liquidacion.setTarifaConsumoComplementarioPlena(tarifaCComPlena.getTarivalo());
    liquidacion.setTarifaConsumoSuntuarioPlena(tarifaCSunPlena.getTarivalo());

    liquidacion.setConsumoBas(consumoBas);
    liquidacion.setConsumoCom(consumoCom);
    liquidacion.setConsumoSun(consumoSun);

    liquidacion.setValorCargoFijo(valorCargoFijo);
    liquidacion.setValorConsumoBasico(valorConsumoBasico);
    liquidacion.setValorConsumoComplementario(valorConsumoComplementario);
    liquidacion.setValorConsumoSuntuario(valorConsumoSuntuario);

    liquidacion.setSuapCargoFijo(suapCargoFijo);
    liquidacion.setSuapConsumoBasico(suapConsumoBasico);
    liquidacion.setSuapConsumoComplementario(suapConsumoComplementario);
    liquidacion.setSuapConsumoSuntuario(suapConsumoSuntuario);

    return liquidacion;
  }

  private Tarifa tarifa(Periodo periodo, Integer casucodi, String taricodi) {
    return tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), casucodi, taricodi);
  }

}
