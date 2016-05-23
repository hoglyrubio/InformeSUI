package com.empresaprivadaservicios.sui;

public enum SuiEstadoMedidor {

  BUEN_ESTADO(1),
  DANADO(2),
  SIN_MEDIDOR(3);

  private final int estado;

  SuiEstadoMedidor(int estado) {
    this.estado = estado;
  }

  Integer value() {
    return estado;
  }

}
