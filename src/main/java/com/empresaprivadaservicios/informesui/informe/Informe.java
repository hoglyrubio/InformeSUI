package com.empresaprivadaservicios.informesui.informe;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="informe")
public class Informe {
  
  @EmbeddedId
  private InformePk informePk;
  
  private Integer infoesta; // int(11)
  
  private String infonomb; // varchar(50)
  
  private String infodire; // varchar(50)
  
  private String infozona; // varchar(10)
  
  private String infosuca; // varchar(1)
  
  private Integer infolean; // int(11)
  
  private Integer infoleac; // int(11)
  
  private Integer infocons; // int(11)
  
  private String infocate; // varchar(2)

  private Double infoinan; // decimal(15,2)
  
  private Double infoinag; // decimal(15,2)
  
  private Double infoinal; // decimal(15,2)
  
  private Double infoinva; // decimal(15,2)
  
  private Double inforeag; // decimal(15,2)
  
  private Double inforeal; // decimal(15,2)
  
  private Double inforeva; // decimal(15,2)
  
  private Double infocafi; // decimal(15,2)
  
  private Double infocbas; // decimal(15,2)
  
  private Double infoccom; // decimal(15,2)
  
  private Double infocsun; // decimal(15,2)
  
  private Double infoalca; // decimal(15,2)
  
  private Double infomedi; // decimal(15,2)
  
  private Double infosure; // decimal(15,2)
  
  private Double infotanq; // decimal(15,2)
  
  private Double infoacom; // decimal(15,2)
  
  private Double infootca; // decimal(15,2)
  
  private Integer inforeci; // int(11)
  
  private Integer infonuat; // int(15)
  
  private Double infovaim; // decimal(15,2)
  
  private Double infoserv; // decimal(15,2)
  
  private Double infoajus; // decimal(15,2)
  
  private Double infovapa; // decimal(15,2)
  
  private Double infovano; // decimal(15,2)
  
  public Informe() {
    
  }
  
  public Informe(Integer infoperi, Integer infocodi) {
    InformePk pk = new InformePk(infoperi, infocodi);
    setInformePk(pk);
  }
  
  public InformePk getInformePk() {
    return informePk;
  }
  
  public void setInformePk(InformePk informePk) {
    this.informePk = informePk;
  }
  
  public Integer getInfoesta() {
    return infoesta;
  }
  
  public void setInfoesta(Integer infoesta) {
    this.infoesta = infoesta;
  }
  
  public String getInfonomb() {
    return infonomb;
  }
  
  public void setInfonomb(String infonomb) {
    this.infonomb = infonomb;
  }
  
  public String getInfodire() {
    return infodire;
  }
  
  public void setInfodire(String infodire) {
    this.infodire = infodire;
  }
  
  public String getInfozona() {
    return infozona;
  }
  
  public void setInfozona(String infozona) {
    this.infozona = infozona;
  }
  
  public String getInfosuca() {
    return infosuca;
  }
  
  public void setInfosuca(String infosuca) {
    this.infosuca = infosuca;
  }
  
  public Integer getInfolean() {
    return infolean;
  }
  
  public void setInfolean(Integer infolean) {
    this.infolean = infolean;
  }
  
  public Integer getInfoleac() {
    return infoleac;
  }
  
  public void setInfoleac(Integer infoleac) {
    this.infoleac = infoleac;
  }
  
  public Integer getInfocons() {
    return infocons;
  }
  
  public void setInfocons(Integer infocons) {
    this.infocons = infocons;
  }
  
  public String getInfocate() {
    return infocate;
  }
  
  public void setInfocate(String infocate) {
    this.infocate = infocate;
  }
  
  public Double getInfoinan() {
    return infoinan;
  }
  
  public void setInfoinan(Double infoinan) {
    this.infoinan = infoinan;
  }
  
  public Double getInfoinag() {
    return infoinag;
  }
  
  public void setInfoinag(Double infoinag) {
    this.infoinag = infoinag;
  }
  
  public Double getInfoinal() {
    return infoinal;
  }
  
  public void setInfoinal(Double infoinal) {
    this.infoinal = infoinal;
  }
  
  public Double getInfoinva() {
    return infoinva;
  }
  
  public void setInfoinva(Double infoinva) {
    this.infoinva = infoinva;
  }
  
  public Double getInforeag() {
    return inforeag;
  }
  
  public void setInforeag(Double inforeag) {
    this.inforeag = inforeag;
  }
  
  public Double getInforeal() {
    return inforeal;
  }
  
  public void setInforeal(Double inforeal) {
    this.inforeal = inforeal;
  }
  
  public Double getInforeva() {
    return inforeva;
  }
  
  public void setInforeva(Double inforeva) {
    this.inforeva = inforeva;
  }
  
  public Double getInfocafi() {
    return infocafi;
  }
  
  public void setInfocafi(Double infocafi) {
    this.infocafi = infocafi;
  }
  
  public Double getInfocbas() {
    return infocbas;
  }
  
  public void setInfocbas(Double infocbas) {
    this.infocbas = infocbas;
  }
  
  public Double getInfoccom() {
    return infoccom;
  }
  
  public void setInfoccom(Double infoccom) {
    this.infoccom = infoccom;
  }
  
  public Double getInfocsun() {
    return infocsun;
  }
  
  public void setInfocsun(Double infocsun) {
    this.infocsun = infocsun;
  }
  
  public Double getInfoalca() {
    return infoalca;
  }
  
  public void setInfoalca(Double infoalca) {
    this.infoalca = infoalca;
  }
  
  public Double getInfomedi() {
    return infomedi;
  }
  
  public void setInfomedi(Double infomedi) {
    this.infomedi = infomedi;
  }
  
  public Double getInfosure() {
    return infosure;
  }
  
  public void setInfosure(Double infosure) {
    this.infosure = infosure;
  }
  
  public Double getInfotanq() {
    return infotanq;
  }
  
  public void setInfotanq(Double infotanq) {
    this.infotanq = infotanq;
  }
  
  public Double getInfoacom() {
    return infoacom;
  }
  
  public void setInfoacom(Double infoacom) {
    this.infoacom = infoacom;
  }
  
  public Double getInfootca() {
    return infootca;
  }
  
  public void setInfootca(Double infootca) {
    this.infootca = infootca;
  }
  
  public Integer getInforeci() {
    return inforeci;
  }
  
  public void setInforeci(Integer inforeci) {
    this.inforeci = inforeci;
  }
  
  public Integer getInfonuat() {
    return infonuat;
  }
  
  public void setInfonuat(Integer infonuat) {
    this.infonuat = infonuat;
  }
  
  public Double getInfovaim() {
    return infovaim;
  }
  
  public void setInfovaim(Double infovaim) {
    this.infovaim = infovaim;
  }
  
  public Double getInfoserv() {
    return infoserv;
  }
  
  public void setInfoserv(Double infoserv) {
    this.infoserv = infoserv;
  }
  
  public Double getInfoajus() {
    return infoajus;
  }
  
  public void setInfoajus(Double infoajus) {
    this.infoajus = infoajus;
  }
  
  public Double getInfovapa() {
    return infovapa;
  }
  
  public void setInfovapa(Double infovapa) {
    this.infovapa = infovapa;
  }
  
  public Double getInfovano() {
    return infovano;
  }
  
  public void setInfovano(Double infovano) {
    this.infovano = infovano;
  }

  @Override
  public String toString() {
    return "Informe [informePk=" + informePk + ", infoesta=" + infoesta + ", infonomb=" + infonomb + ", infodire="
        + infodire + ", infozona=" + infozona + ", infosuca=" + infosuca + ", infolean=" + infolean + ", infoleac="
        + infoleac + ", infocons=" + infocons + ", infocate=" + infocate + ", infoinan=" + infoinan + ", infoinag="
        + infoinag + ", infoinal=" + infoinal + ", infoinva=" + infoinva + ", inforeag=" + inforeag + ", inforeal="
        + inforeal + ", inforeva=" + inforeva + ", infocafi=" + infocafi + ", infocbas=" + infocbas + ", infoccom="
        + infoccom + ", infocsun=" + infocsun + ", infoalca=" + infoalca + ", infomedi=" + infomedi + ", infosure="
        + infosure + ", infotanq=" + infotanq + ", infoacom=" + infoacom + ", infootca=" + infootca + ", inforeci="
        + inforeci + ", infonuat=" + infonuat + ", infovaim=" + infovaim + ", infoserv=" + infoserv + ", infoajus="
        + infoajus + ", infovapa=" + infovapa + ", infovano=" + infovano + "]";
  }

}
