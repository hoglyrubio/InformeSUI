package com.empresaprivadaservicios.sui;

public enum SuiDeterminacionConsumo {

  LEIDO(1),
  PROMEDIO(2);

  private final int determinacion;

  SuiDeterminacionConsumo(int determinacion) {
    this.determinacion = determinacion;
  }

  Integer value() {
    return determinacion;
  }

}
