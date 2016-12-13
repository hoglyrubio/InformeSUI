package com.empresaprivadaservicios.informesui.sui;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="sui_catesuca")
public class SuiCateSuca {

  @EmbeddedId
  private SuiCateSucaPk suiCateSucaPk;

  private Integer casucodi;
  private String casudesc;

  public SuiCateSuca() {

  }

  public SuiCateSuca(SuiCateSucaPk suiCateSucaPk) {
    this.suiCateSucaPk = suiCateSucaPk;
  }

  public SuiCateSuca(SuiCateSucaPk suiCateSucaPk, Integer casucodi, String casudesc) {
    this.suiCateSucaPk = suiCateSucaPk;
    this.casucodi = casucodi;
    this.casudesc = casudesc;
  }

  public SuiCateSucaPk getSuiCateSucaPk() {
    return suiCateSucaPk;
  }

  public void setSuiCateSucaPk(SuiCateSucaPk suiCateSucaPk) {
    this.suiCateSucaPk = suiCateSucaPk;
  }

  public Integer getCasucodi() {
    return casucodi;
  }

  public void setCasucodi(Integer casucodi) {
    this.casucodi = casucodi;
  }

  public String getCasudesc() {
    return casudesc;
  }

  public void setCasudesc(String casudesc) {
    this.casudesc = casudesc;
  }

  @Override
  public String toString() {
    return "SuiCateSuca{" +
            "suiCateSucaPk=" + suiCateSucaPk +
            ", casucodi=" + casucodi +
            ", casudesc='" + casudesc + '\'' +
            '}';
  }
}
