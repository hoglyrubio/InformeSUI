package com.empresaprivadaservicios.informesui.tarifa;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TarifaPk implements Serializable {

  private Integer tariano;
  private Integer tarimes;
  private String taricodi;
  private Integer taricate;
  private Integer tarisuca;

  public TarifaPk() {

  }

  public TarifaPk(Integer tariano, Integer tarimes, String taricodi, Integer taricate, Integer tarisuca) {
    this.tariano = tariano;
    this.tarimes = tarimes;
    this.taricodi = taricodi;
    this.taricate = taricate;
    this.tarisuca = tarisuca;
  }

  public Integer getTariano() {
    return tariano;
  }

  public void setTariano(Integer tariano) {
    this.tariano = tariano;
  }

  public Integer getTarimes() {
    return tarimes;
  }

  public void setTarimes(Integer tarimes) {
    this.tarimes = tarimes;
  }

  public String getTaricodi() {
    return taricodi;
  }

  public void setTaricodi(String taricodi) {
    this.taricodi = taricodi;
  }

  public Integer getTaricate() {
    return taricate;
  }

  public void setTaricate(Integer taricate) {
    this.taricate = taricate;
  }

  public Integer getTarisuca() {
    return tarisuca;
  }

  public void setTarisuca(Integer tarisuca) {
    this.tarisuca = tarisuca;
  }

  @Override
  public String toString() {
    return "TarifaPk{" +
            "tariano=" + tariano +
            ", tarimes=" + tarimes +
            ", taricodi=" + taricodi +
            ", taricate=" + taricate +
            ", tarisuca=" + tarisuca +
            '}';
  }
}
