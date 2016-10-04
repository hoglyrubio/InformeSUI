package com.empresaprivadaservicios.informesui.tarifa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "sui_tarifa")
public class Tarifa {

  @EmbeddedId
  private TarifaPk tarifaPk;

  private BigDecimal tarivalo;
  private BigDecimal tariporc;
  private String taridesc;
  private Integer tariclus;

  public Tarifa() {

  }

  public Tarifa(TarifaPk tarifaPk) {
    this.tarifaPk = tarifaPk;
  }

  public Tarifa(TarifaPk tarifaPk, BigDecimal tarivalo, BigDecimal tariporc, String taridesc, Integer tariclus) {
    this.tarifaPk = tarifaPk;
    this.tarivalo = tarivalo;
    this.tariporc = tariporc;
    this.taridesc = taridesc;
    this.tariclus = tariclus;
  }

  public TarifaPk getTarifaPk() {
    return tarifaPk;
  }

  public void setTarifaPk(TarifaPk tarifaPk) {
    this.tarifaPk = tarifaPk;
  }

  public BigDecimal getTarivalo() {
    return tarivalo;
  }

  public void setTarivalo(BigDecimal tarivalo) {
    this.tarivalo = tarivalo;
  }

  public BigDecimal getTariporc() {
    return tariporc;
  }

  public void setTariporc(BigDecimal tariporc) {
    this.tariporc = tariporc;
  }

  public String getTaridesc() {
    return taridesc;
  }

  public void setTaridesc(String taridesc) {
    this.taridesc = taridesc;
  }

  public Integer getTariclus() {
    return tariclus;
  }

  public void setTariclus(Integer tariclus) {
    this.tariclus = tariclus;
  }
}
