package com.empresaprivadaservicios.informesui.sui;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SuiCateSucaPk implements Serializable {

  private String casucate;

  private String casusuca;

  public SuiCateSucaPk() {

  }

  public SuiCateSucaPk(String casucate, String casusuca) {
    this.casucate = casucate;
    this.casusuca = casusuca;
  }

  public String getCasucate() {
    return casucate;
  }

  public void setCasucate(String casucate) {
    this.casucate = casucate;
  }

  public String getCasusuca() {
    return casusuca;
  }

  public void setCasusuca(String casusuca) {
    this.casusuca = casusuca;
  }

  @Override
  public String toString() {
    return "SuiCateSucaPk{" +
            "casucate='" + casucate + '\'' +
            ", casusuca='" + casusuca + '\'' +
            '}';
  }
}
