package com.empresaprivadaservicios.sui;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InformePk implements Serializable {

  private static final long serialVersionUID = 3888436743737220319L;

  private Integer infoperi; // int(11)
  
  private Integer infocodi; // int(11)

  public InformePk() {
    
  }
  
  public InformePk(Integer infoperi, Integer infocodi) {
    this.infoperi = infoperi;
    this.infocodi = infocodi;
  }
  
  public Integer getInfoperi() {
    return infoperi;
  }

  public void setInfoperi(Integer infoperi) {
    this.infoperi = infoperi;
  }

  public Integer getInfocodi() {
    return infocodi;
  }

  public void setInfocodi(Integer infocodi) {
    this.infocodi = infocodi;
  }

  @Override
  public String toString() {
    return "InformePk [infoperi=" + infoperi + ", infocodi=" + infocodi + "]";
  }

}
