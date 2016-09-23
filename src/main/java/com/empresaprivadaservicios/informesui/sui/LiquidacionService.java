package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.tarifa.Tarifa;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiquidacionService {

  private static final int DECIMALS = 4;
  private TarifaRepository tarifaRepository;

  @Autowired
  public LiquidacionService(TarifaRepository tarifaRepository) {
    this.tarifaRepository = tarifaRepository;
  }

  public Liquidacion process(Integer consumo, SuiCateSuca suiCateSuca, Periodo periodo) {

    Integer consumoBas = consumo > 20 ? 20 : consumo;
    Integer consumoCom = (consumo - consumoBas) > 20 ? 20 : (consumo - consumoBas);
    Integer consumoSun = (consumo - consumoBas - consumoCom) > 0 ? (consumo - consumoBas - consumoCom) : 0;

    Tarifa tarifaCF = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CARGO_FIJO);
    Tarifa tarifaCFPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CARGO_FIJO);
    Double valorCargoFijo = tarifaCF.getTarivalo();
    Double suapCargoFijo = valorCargoFijo - tarifaCFPlena.getTarivalo();

    Tarifa tarifaCBas = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CONSUMO_BASICO);
    Tarifa tarifaCBasPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CONSUMO_BASICO);
    Double valorConsumoBasico = tarifaCBas.getTarivalo() * consumoBas;
    valorConsumoBasico = SuiHelper.round(valorConsumoBasico, DECIMALS);
    Double suapConsumoBasico = (valorConsumoBasico - tarifaCBasPlena.getTarivalo()) * consumoBas;

    Tarifa tarifaCCom = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CONSUMO_COMPLEMENTARIO);
    Tarifa tarifaCComPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CONSUMO_COMPLEMENTARIO);
    Double valorConsumoComplementario = tarifaCCom.getTarivalo() * consumoCom;
    Double suapConsumoComplementario = (valorConsumoComplementario - tarifaCComPlena.getTarivalo()) * consumoCom;

    Tarifa tarifaCSun = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CONSUMO_SUNTUARIO);
    Tarifa tarifaCSunPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CONSUMO_SUNTUARIO);
    Double valorConsumoSuntuario = tarifaCSun.getTarivalo() * consumoSun;
    Double suapConsumoSuntuario = (valorConsumoSuntuario - tarifaCSunPlena.getTarivalo()) * consumoSun;

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

}
