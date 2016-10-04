package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.periodo.Periodo;
import com.empresaprivadaservicios.informesui.tarifa.Tarifa;
import com.empresaprivadaservicios.informesui.tarifa.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    Tarifa tarifaCF = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CARGO_FIJO);
    Tarifa tarifaCFPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CARGO_FIJO);
    BigDecimal valorCargoFijo = tarifaCF.getTarivalo();
    BigDecimal suapCargoFijo = valorCargoFijo.subtract(tarifaCFPlena.getTarivalo());

    Tarifa tarifaCBas = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CONSUMO_BASICO);
    Tarifa tarifaCBasPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CONSUMO_BASICO);
    BigDecimal valorConsumoBasico = tarifaCBas.getTarivalo().multiply(new BigDecimal(consumoBas));
    BigDecimal suapConsumoBasico = valorConsumoBasico.subtract(tarifaCBasPlena.getTarivalo().multiply(new BigDecimal(consumoBas)));

    Tarifa tarifaCCom = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CONSUMO_COMPLEMENTARIO);
    Tarifa tarifaCComPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CONSUMO_COMPLEMENTARIO);
    BigDecimal valorConsumoComplementario = tarifaCCom.getTarivalo().multiply(new BigDecimal(consumoCom));
    BigDecimal suapConsumoComplementario = valorConsumoComplementario.subtract(tarifaCComPlena.getTarivalo().multiply(new BigDecimal(consumoCom)));

    Tarifa tarifaCSun = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), suiCateSuca.getCasucodi(), SuiConstantes.TARICODI_CONSUMO_SUNTUARIO);
    Tarifa tarifaCSunPlena = tarifaRepository.findOne(periodo.getPeriano(), periodo.getPerimes(), SuiConstantes.DEFAULT_CASUCODI, SuiConstantes.TARICODI_CONSUMO_SUNTUARIO);
    BigDecimal valorConsumoSuntuario = tarifaCSun.getTarivalo().multiply(new BigDecimal(consumoSun));
    BigDecimal suapConsumoSuntuario = valorConsumoSuntuario.subtract(tarifaCSunPlena.getTarivalo().multiply(new BigDecimal(consumoSun)));

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
