package com.empresaprivadaservicios.informesui.sui;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SuiAcueductoPk implements Serializable {

  private Integer periodo ; // int(11);

  private Integer C01 ; // int(11);

  public SuiAcueductoPk() {

  }

  public SuiAcueductoPk(Integer periodo, Integer C01) {
    this.periodo = periodo;
    this.C01 = C01;
  }

  public Integer getPeriodo() {
    return periodo;
  }

  public void setPeriodo(Integer periodo) {
    this.periodo = periodo;
  }

  public Integer getC01() {
    return C01;
  }

  public void setC01(Integer c01) {
    C01 = c01;
  }

  @Override
  public String toString() {
    return "SuiAcueductoPk{" +
            "periodo=" + periodo +
            ", C01=" + C01 +
            '}';
  }
}
