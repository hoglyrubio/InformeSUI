package com.empresaprivadaservicios.informesui.informe;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;

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

  private BigDecimal infoinan; // decimal(15,2)
  
  private BigDecimal infoinag; // decimal(15,2)
  
  private BigDecimal infoinal; // decimal(15,2)
  
  private BigDecimal infoinva; // decimal(15,2)
  
  private BigDecimal inforeag; // decimal(15,2)
  
  private BigDecimal inforeal; // decimal(15,2)
  
  private BigDecimal inforeva; // decimal(15,2)
  
  private BigDecimal infocafi; // decimal(15,2)
  
  private BigDecimal infocbas; // decimal(15,2)
  
  private BigDecimal infoccom; // decimal(15,2)
  
  private BigDecimal infocsun; // decimal(15,2)
  
  private BigDecimal infoalca; // decimal(15,2)
  
  private BigDecimal infomedi; // decimal(15,2)
  
  private BigDecimal infosure; // decimal(15,2)
  
  private BigDecimal infotanq; // decimal(15,2)
  
  private BigDecimal infoacom; // decimal(15,2)
  
  private BigDecimal infootca; // decimal(15,2)
  
  private Integer inforeci; // int(11)
  
  private Integer infonuat; // int(15)
  
  private BigDecimal infovaim; // decimal(15,2)
  
  private BigDecimal infoserv; // decimal(15,2)
  
  private BigDecimal infoajus; // decimal(15,2)
  
  private BigDecimal infovapa; // decimal(15,2)
  
  private BigDecimal infovano; // decimal(15,2)
  
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
  
  public BigDecimal getInfoinan() {
    return infoinan;
  }
  
  public void setInfoinan(BigDecimal infoinan) {
    this.infoinan = infoinan;
  }
  
  public BigDecimal getInfoinag() {
    return infoinag;
  }
  
  public void setInfoinag(BigDecimal infoinag) {
    this.infoinag = infoinag;
  }
  
  public BigDecimal getInfoinal() {
    return infoinal;
  }
  
  public void setInfoinal(BigDecimal infoinal) {
    this.infoinal = infoinal;
  }
  
  public BigDecimal getInfoinva() {
    return infoinva;
  }
  
  public void setInfoinva(BigDecimal infoinva) {
    this.infoinva = infoinva;
  }
  
  public BigDecimal getInforeag() {
    return inforeag;
  }
  
  public void setInforeag(BigDecimal inforeag) {
    this.inforeag = inforeag;
  }
  
  public BigDecimal getInforeal() {
    return inforeal;
  }
  
  public void setInforeal(BigDecimal inforeal) {
    this.inforeal = inforeal;
  }
  
  public BigDecimal getInforeva() {
    return inforeva;
  }
  
  public void setInforeva(BigDecimal inforeva) {
    this.inforeva = inforeva;
  }
  
  public BigDecimal getInfocafi() {
    return infocafi;
  }
  
  public void setInfocafi(BigDecimal infocafi) {
    this.infocafi = infocafi;
  }
  
  public BigDecimal getInfocbas() {
    return infocbas;
  }
  
  public void setInfocbas(BigDecimal infocbas) {
    this.infocbas = infocbas;
  }
  
  public BigDecimal getInfoccom() {
    return infoccom;
  }
  
  public void setInfoccom(BigDecimal infoccom) {
    this.infoccom = infoccom;
  }
  
  public BigDecimal getInfocsun() {
    return infocsun;
  }
  
  public void setInfocsun(BigDecimal infocsun) {
    this.infocsun = infocsun;
  }
  
  public BigDecimal getInfoalca() {
    return infoalca;
  }
  
  public void setInfoalca(BigDecimal infoalca) {
    this.infoalca = infoalca;
  }
  
  public BigDecimal getInfomedi() {
    return infomedi;
  }
  
  public void setInfomedi(BigDecimal infomedi) {
    this.infomedi = infomedi;
  }
  
  public BigDecimal getInfosure() {
    return infosure;
  }
  
  public void setInfosure(BigDecimal infosure) {
    this.infosure = infosure;
  }
  
  public BigDecimal getInfotanq() {
    return infotanq;
  }
  
  public void setInfotanq(BigDecimal infotanq) {
    this.infotanq = infotanq;
  }
  
  public BigDecimal getInfoacom() {
    return infoacom;
  }
  
  public void setInfoacom(BigDecimal infoacom) {
    this.infoacom = infoacom;
  }
  
  public BigDecimal getInfootca() {
    return infootca;
  }
  
  public void setInfootca(BigDecimal infootca) {
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
  
  public BigDecimal getInfovaim() {
    return infovaim;
  }
  
  public void setInfovaim(BigDecimal infovaim) {
    this.infovaim = infovaim;
  }
  
  public BigDecimal getInfoserv() {
    return infoserv;
  }
  
  public void setInfoserv(BigDecimal infoserv) {
    this.infoserv = infoserv;
  }
  
  public BigDecimal getInfoajus() {
    return infoajus;
  }
  
  public void setInfoajus(BigDecimal infoajus) {
    this.infoajus = infoajus;
  }
  
  public BigDecimal getInfovapa() {
    return infovapa;
  }
  
  public void setInfovapa(BigDecimal infovapa) {
    this.infovapa = infovapa;
  }
  
  public BigDecimal getInfovano() {
    return infovano;
  }
  
  public void setInfovano(BigDecimal infovano) {
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
