package com.empresaprivadaservicios.informesui.sui;

import com.empresaprivadaservicios.informesui.periodo.Periodo;

public class Liquidacion {

  private SuiCateSuca suiCateSuca;
  private Periodo periodo;

  private Integer consumo;
  private Integer consumoBas;
  private Integer consumoCom;
  private Integer consumoSun;

  private Double tarifaCargoFijo;
  private Double tarifaConsumoBasico;
  private Double tarifaConsumoComplementario;
  private Double tarifaConsumoSuntuario;

  private Double tarifaCargoFijoPlena;
  private Double tarifaConsumoBasicoPlena;
  private Double tarifaConsumoComplementarioPlena;
  private Double tarifaConsumoSuntuarioPlena;

  private Double valorCargoFijo;
  private Double valorConsumoBasico;
  private Double valorConsumoComplementario;
  private Double valorConsumoSuntuario;

  private Double suapCargoFijo;
  private Double suapConsumoBasico;
  private Double suapConsumoComplementario;
  private Double suapConsumoSuntuario;

  public Double valorConsumo() {
    return valorConsumoBasico + valorConsumoComplementario + valorConsumoSuntuario;
  }

  public Double suapConsumo() {
    return suapConsumoBasico + suapConsumoComplementario + suapConsumoSuntuario;
  }

  public Double totalSubsidio() {
    return Math.abs((suapCargoFijo < 0 ? suapCargoFijo : 0) +
           (suapConsumoBasico < 0 ? suapConsumoBasico : 0) +
           (suapConsumoComplementario < 0 ? suapConsumoComplementario : 0) +
           (suapConsumoSuntuario < 0 ? suapConsumoSuntuario : 0));
  }

  public Double totalContribucion() {
    return Math.abs((suapCargoFijo > 0 ? suapCargoFijo : 0) +
           (suapConsumoBasico > 0 ? suapConsumoBasico : 0) +
           (suapConsumoComplementario > 0 ? suapConsumoComplementario : 0) +
           (suapConsumoSuntuario > 0 ? suapConsumoSuntuario : 0));
  }

  public SuiCateSuca getSuiCateSuca() {
    return suiCateSuca;
  }

  public Double getTarifaCargoFijoPlena() {
    return tarifaCargoFijoPlena;
  }

  public void setTarifaCargoFijoPlena(Double tarifaCargoFijoPlena) {
    this.tarifaCargoFijoPlena = tarifaCargoFijoPlena;
  }

  public Double getTarifaConsumoBasicoPlena() {
    return tarifaConsumoBasicoPlena;
  }

  public void setTarifaConsumoBasicoPlena(Double tarifaConsumoBasicoPlena) {
    this.tarifaConsumoBasicoPlena = tarifaConsumoBasicoPlena;
  }

  public Double getTarifaConsumoComplementarioPlena() {
    return tarifaConsumoComplementarioPlena;
  }

  public void setTarifaConsumoComplementarioPlena(Double tarifaConsumoComplementarioPlena) {
    this.tarifaConsumoComplementarioPlena = tarifaConsumoComplementarioPlena;
  }

  public Double getTarifaConsumoSuntuarioPlena() {
    return tarifaConsumoSuntuarioPlena;
  }

  public void setTarifaConsumoSuntuarioPlena(Double tarifaConsumoSuntuarioPlena) {
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

  public Double getTarifaCargoFijo() {
    return tarifaCargoFijo;
  }

  public void setTarifaCargoFijo(Double tarifaCargoFijo) {
    this.tarifaCargoFijo = tarifaCargoFijo;
  }

  public Double getTarifaConsumoBasico() {
    return tarifaConsumoBasico;
  }

  public void setTarifaConsumoBasico(Double tarifaConsumoBasico) {
    this.tarifaConsumoBasico = tarifaConsumoBasico;
  }

  public Double getTarifaConsumoComplementario() {
    return tarifaConsumoComplementario;
  }

  public void setTarifaConsumoComplementario(Double tarifaConsumoComplementario) {
    this.tarifaConsumoComplementario = tarifaConsumoComplementario;
  }

  public Double getTarifaConsumoSuntuario() {
    return tarifaConsumoSuntuario;
  }

  public void setTarifaConsumoSuntuario(Double tarifaConsumoSuntuario) {
    this.tarifaConsumoSuntuario = tarifaConsumoSuntuario;
  }

  public Double getValorCargoFijo() {
    return valorCargoFijo;
  }

  public void setValorCargoFijo(Double valorCargoFijo) {
    this.valorCargoFijo = valorCargoFijo;
  }

  public Double getValorConsumoBasico() {
    return valorConsumoBasico;
  }

  public void setValorConsumoBasico(Double valorConsumoBasico) {
    this.valorConsumoBasico = valorConsumoBasico;
  }

  public Double getValorConsumoComplementario() {
    return valorConsumoComplementario;
  }

  public void setValorConsumoComplementario(Double valorConsumoComplementario) {
    this.valorConsumoComplementario = valorConsumoComplementario;
  }

  public Double getValorConsumoSuntuario() {
    return valorConsumoSuntuario;
  }

  public void setValorConsumoSuntuario(Double valorConsumoSuntuario) {
    this.valorConsumoSuntuario = valorConsumoSuntuario;
  }

  public Double getSuapCargoFijo() {
    return suapCargoFijo;
  }

  public void setSuapCargoFijo(Double suapCargoFijo) {
    this.suapCargoFijo = suapCargoFijo;
  }

  public Double getSuapConsumoBasico() {
    return suapConsumoBasico;
  }

  public void setSuapConsumoBasico(Double suapConsumoBasico) {
    this.suapConsumoBasico = suapConsumoBasico;
  }

  public Double getSuapConsumoComplementario() {
    return suapConsumoComplementario;
  }

  public void setSuapConsumoComplementario(Double suapConsumoComplementario) {
    this.suapConsumoComplementario = suapConsumoComplementario;
  }

  public Double getSuapConsumoSuntuario() {
    return suapConsumoSuntuario;
  }

  public void setSuapConsumoSuntuario(Double suapConsumoSuntuario) {
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