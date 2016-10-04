package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.periodo.Periodo;

import java.math.BigDecimal;

public class Liquidacion {

  private SuiCateSuca suiCateSuca;
  private Periodo periodo;

  private Integer consumo;
  private Integer consumoBas;
  private Integer consumoCom;
  private Integer consumoSun;

  private BigDecimal tarifaCargoFijo;
  private BigDecimal tarifaConsumoBasico;
  private BigDecimal tarifaConsumoComplementario;
  private BigDecimal tarifaConsumoSuntuario;

  private BigDecimal tarifaCargoFijoPlena;
  private BigDecimal tarifaConsumoBasicoPlena;
  private BigDecimal tarifaConsumoComplementarioPlena;
  private BigDecimal tarifaConsumoSuntuarioPlena;

  private BigDecimal valorCargoFijo;
  private BigDecimal valorConsumoBasico;
  private BigDecimal valorConsumoComplementario;
  private BigDecimal valorConsumoSuntuario;

  private BigDecimal suapCargoFijo;
  private BigDecimal suapConsumoBasico;
  private BigDecimal suapConsumoComplementario;
  private BigDecimal suapConsumoSuntuario;

  public BigDecimal valorConsumo() {
    return valorConsumoBasico.add(valorConsumoComplementario).add(valorConsumoSuntuario);
  }

  public BigDecimal suapConsumo() {
    return suapConsumoBasico.add(suapConsumoComplementario).add(suapConsumoSuntuario);
  }

  public BigDecimal totalSubsidio() {
    BigDecimal sacf = (suapCargoFijo.compareTo(BigDecimal.ZERO) < 0 ? suapCargoFijo : BigDecimal.ZERO);
    BigDecimal sacb = (suapConsumoBasico.compareTo(BigDecimal.ZERO) < 0 ? suapConsumoBasico : BigDecimal.ZERO);
    BigDecimal sacc = (suapConsumoComplementario.compareTo(BigDecimal.ZERO) < 0 ? suapConsumoComplementario : BigDecimal.ZERO);
    BigDecimal sacs = (suapConsumoSuntuario.compareTo(BigDecimal.ZERO) < 0 ? suapConsumoSuntuario : BigDecimal.ZERO);
    return sacf.add(sacb).add(sacc).add(sacs).abs();
  }

  public BigDecimal totalContribucion() {
    BigDecimal sacf = (suapCargoFijo.compareTo(BigDecimal.ZERO) > 0 ? suapCargoFijo : BigDecimal.ZERO);
    BigDecimal sacb = (suapConsumoBasico.compareTo(BigDecimal.ZERO) > 0 ? suapConsumoBasico : BigDecimal.ZERO);
    BigDecimal sacc = (suapConsumoComplementario.compareTo(BigDecimal.ZERO) > 0 ? suapConsumoComplementario : BigDecimal.ZERO);
    BigDecimal sacs = (suapConsumoSuntuario.compareTo(BigDecimal.ZERO) > 0 ? suapConsumoSuntuario : BigDecimal.ZERO);
    return sacf.add(sacb).add(sacc).add(sacs).abs();
  }

  public SuiCateSuca getSuiCateSuca() {
    return suiCateSuca;
  }

  public BigDecimal getTarifaCargoFijoPlena() {
    return tarifaCargoFijoPlena;
  }

  public void setTarifaCargoFijoPlena(BigDecimal tarifaCargoFijoPlena) {
    this.tarifaCargoFijoPlena = tarifaCargoFijoPlena;
  }

  public BigDecimal getTarifaConsumoBasicoPlena() {
    return tarifaConsumoBasicoPlena;
  }

  public void setTarifaConsumoBasicoPlena(BigDecimal tarifaConsumoBasicoPlena) {
    this.tarifaConsumoBasicoPlena = tarifaConsumoBasicoPlena;
  }

  public BigDecimal getTarifaConsumoComplementarioPlena() {
    return tarifaConsumoComplementarioPlena;
  }

  public void setTarifaConsumoComplementarioPlena(BigDecimal tarifaConsumoComplementarioPlena) {
    this.tarifaConsumoComplementarioPlena = tarifaConsumoComplementarioPlena;
  }

  public BigDecimal getTarifaConsumoSuntuarioPlena() {
    return tarifaConsumoSuntuarioPlena;
  }

  public void setTarifaConsumoSuntuarioPlena(BigDecimal tarifaConsumoSuntuarioPlena) {
    this.tarifaConsumoSuntuarioPlena = tarifaConsumoSuntuarioPlena;
  }

  public void setSuiCateSuca(SuiCateSuca suiCateSuca) {
    this.suiCateSuca = suiCateSuca;
  }

  public Periodo getPeriodo() {
    return periodo;
  }

  public void setPeriodo(Periodo periodo) {
    this.periodo = periodo;
  }

  public Integer getConsumo() {
    return consumo;
  }

  public void setConsumo(Integer consumo) {
    this.consumo = consumo;
  }

  public Integer getConsumoBas() {
    return consumoBas;
  }

  public void setConsumoBas(Integer consumoBas) {
    this.consumoBas = consumoBas;
  }

  public Integer getConsumoCom() {
    return consumoCom;
  }

  public void setConsumoCom(Integer consumoCom) {
    this.consumoCom = consumoCom;
  }

  public Integer getConsumoSun() {
    return consumoSun;
  }

  public void setConsumoSun(Integer consumoSun) {
    this.consumoSun = consumoSun;
  }

  public BigDecimal getTarifaCargoFijo() {
    return tarifaCargoFijo;
  }

  public void setTarifaCargoFijo(BigDecimal tarifaCargoFijo) {
    this.tarifaCargoFijo = tarifaCargoFijo;
  }

  public BigDecimal getTarifaConsumoBasico() {
    return tarifaConsumoBasico;
  }

  public void setTarifaConsumoBasico(BigDecimal tarifaConsumoBasico) {
    this.tarifaConsumoBasico = tarifaConsumoBasico;
  }

  public BigDecimal getTarifaConsumoComplementario() {
    return tarifaConsumoComplementario;
  }

  public void setTarifaConsumoComplementario(BigDecimal tarifaConsumoComplementario) {
    this.tarifaConsumoComplementario = tarifaConsumoComplementario;
  }

  public BigDecimal getTarifaConsumoSuntuario() {
    return tarifaConsumoSuntuario;
  }

  public void setTarifaConsumoSuntuario(BigDecimal tarifaConsumoSuntuario) {
    this.tarifaConsumoSuntuario = tarifaConsumoSuntuario;
  }

  public BigDecimal getValorCargoFijo() {
    return valorCargoFijo;
  }

  public void setValorCargoFijo(BigDecimal valorCargoFijo) {
    this.valorCargoFijo = valorCargoFijo;
  }

  public BigDecimal getValorConsumoBasico() {
    return valorConsumoBasico;
  }

  public void setValorConsumoBasico(BigDecimal valorConsumoBasico) {
    this.valorConsumoBasico = valorConsumoBasico;
  }

  public BigDecimal getValorConsumoComplementario() {
    return valorConsumoComplementario;
  }

  public void setValorConsumoComplementario(BigDecimal valorConsumoComplementario) {
    this.valorConsumoComplementario = valorConsumoComplementario;
  }

  public BigDecimal getValorConsumoSuntuario() {
    return valorConsumoSuntuario;
  }

  public void setValorConsumoSuntuario(BigDecimal valorConsumoSuntuario) {
    this.valorConsumoSuntuario = valorConsumoSuntuario;
  }

  public BigDecimal getSuapCargoFijo() {
    return suapCargoFijo;
  }

  public void setSuapCargoFijo(BigDecimal suapCargoFijo) {
    this.suapCargoFijo = suapCargoFijo;
  }

  public BigDecimal getSuapConsumoBasico() {
    return suapConsumoBasico;
  }

  public void setSuapConsumoBasico(BigDecimal suapConsumoBasico) {
    this.suapConsumoBasico = suapConsumoBasico;
  }

  public BigDecimal getSuapConsumoComplementario() {
    return suapConsumoComplementario;
  }

  public void setSuapConsumoComplementario(BigDecimal suapConsumoComplementario) {
    this.suapConsumoComplementario = suapConsumoComplementario;
  }

  public BigDecimal getSuapConsumoSuntuario() {
    return suapConsumoSuntuario;
  }

  public void setSuapConsumoSuntuario(BigDecimal suapConsumoSuntuario) {
    this.suapConsumoSuntuario = suapConsumoSuntuario;
  }

  @Override
  public String toString() {
    return "Liquidacion{" +
            "consumo=" + consumo +
            ", consumoBas=" + consumoBas +
            ", consumoCom=" + consumoCom +
            ", consumoSun=" + consumoSun +
            ", valorCargoFijo=" + valorCargoFijo +
            ", valorConsumoBasico=" + valorConsumoBasico +
            ", valorConsumoComplementario=" + valorConsumoComplementario +
            ", valorConsumoSuntuario=" + valorConsumoSuntuario +
            ", suapCargoFijo=" + suapCargoFijo +
            ", suapConsumoBasico=" + suapConsumoBasico +
            ", suapConsumoComplementario=" + suapConsumoComplementario +
            ", suapConsumoSuntuario=" + suapConsumoSuntuario +
            '}';
  }
}