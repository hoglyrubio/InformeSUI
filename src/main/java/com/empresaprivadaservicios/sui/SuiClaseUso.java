package com.empresaprivadaservicios.sui;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sui_claseuso")
public class SuiClaseUso {

  @Id
  private Integer cluscodi;
  private String clusnomb;
  private Integer cluscate;
  private Integer clussuca;
  private String clususo;

  public SuiClaseUso() {
  }

  public SuiClaseUso(Integer cluscodi, String clusnomb, Integer cluscate, Integer clussuca, String clususo) {
    this.cluscodi = cluscodi;
    this.clusnomb = clusnomb;
    this.cluscate = cluscate;
    this.clussuca = clussuca;
    this.clususo = clususo;
  }

  public Integer getCluscodi() {
    return cluscodi;
  }

  public void setCluscodi(Integer cluscodi) {
    this.cluscodi = cluscodi;
  }

  public String getClusnomb() {
    return clusnomb;
  }

  public void setClusnomb(String clusnomb) {
    this.clusnomb = clusnomb;
  }

  public Integer getCluscate() {
    return cluscate;
  }

  public void setCluscate(Integer cluscate) {
    this.cluscate = cluscate;
  }

  public Integer getClussuca() {
    return clussuca;
  }

  public void setClussuca(Integer clussuca) {
    this.clussuca = clussuca;
  }

  public String getClususo() {
    return clususo;
  }

  public void setClususo(String clususo) {
    this.clususo = clususo;
  }

  @Override
  public String toString() {
    return "SuiClaseUso{" +
            "cluscodi=" + cluscodi +
            ", clusnomb='" + clusnomb + '\'' +
            ", cluscate=" + cluscate +
            ", clussuca=" + clussuca +
            ", clususo='" + clususo + '\'' +
            '}';
  }
}
