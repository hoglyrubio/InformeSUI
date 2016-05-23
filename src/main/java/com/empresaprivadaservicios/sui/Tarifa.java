package com.empresaprivadaservicios.sui;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "sui_tarifa")
public class Tarifa {

  @EmbeddedId
  private TarifaPk tarifaPk;

  private Double tarivalo;
  private Double tariporc;
  private String taridesc;
  private Integer tariclus;

  public Tarifa() {

  }

  public Tarifa(TarifaPk tarifaPk) {
    this.tarifaPk = tarifaPk;
  }

  public Tarifa(TarifaPk tarifaPk, Double tarivalo, Double tariporc, String taridesc, Integer tariclus) {
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

  public Double getTarivalo() {
    return tarivalo;
  }

  public void setTarivalo(Double tarivalo) {
    this.tarivalo = tarivalo;
  }

  public Double getTariporc() {
    return tariporc;
  }

  public void setTariporc(Double tariporc) {
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
