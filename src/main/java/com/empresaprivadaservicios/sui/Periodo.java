package com.empresaprivadaservicios.sui;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="sui_periodo")
public class Periodo {

  @Id
  private Integer pericodi;
  private Integer periano;
  private Integer perimes;
  private Date perifein;
  private Date perifefi;
  private Date perifege;
  
  public Periodo() {
    super();
  }

  public Periodo(Integer pericodi, Integer periano, Integer perimes, Date perifein, Date perifefi, Date perifege) {
    super();
    this.pericodi = pericodi;
    this.periano = periano;
    this.perimes = perimes;
    this.perifein = perifein;
    this.perifefi = perifefi;
    this.perifege = perifege;
  }

  public Integer getPericodi() {
    return pericodi;
  }

  public void setPericodi(Integer pericodi) {
    this.pericodi = pericodi;
  }

  public Integer getPeriano() {
    return periano;
  }

  public void setPeriano(Integer periano) {
    this.periano = periano;
  }

  public Integer getPerimes() {
    return perimes;
  }

  public void setPerimes(Integer perimes) {
    this.perimes = perimes;
  }

  public Date getPerifein() {
    return perifein;
  }

  public void setPerifein(Date perifein) {
    this.perifein = perifein;
  }

  public Date getPerifefi() {
    return perifefi;
  }

  public void setPerifefi(Date perifefi) {
    this.perifefi = perifefi;
  }

  public Date getPerifege() {
    return perifege;
  }

  public void setPerifege(Date perifege) {
    this.perifege = perifege;
  }
  
}
