package com.empresaprivadaservicios.informesui.sui;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name="suialc2010")
public class SuiAlcantarillado {

  @EmbeddedId
  private SuiAlcantarilladoPk suiAlcantarilladoPk;
  private Integer C02; // int(11);
  private String C03; // varchar(2);
  private String C04; // varchar(3);
  private String C05; // varchar(2);
  private String C06; // varchar(2);
  private String C07; // varchar(4);
  private String C08; // varchar(4);
  private String C09; // varchar(3);
  private String C10; // varchar(130);
  private Integer C11; // int(11);
  private Date C12; // date;
  private Date C13; // date;
  private Integer C14; // int(11);
  private Integer C15; // int(11);
  private Integer C16; // int(11);
  private Integer C17; // int(11);
  private Integer C18; // int(11);
  private Integer C19; // int(11);
  private Integer C20; // int(11);
  private BigDecimal C21; // numeric(15,4);
  private BigDecimal C22; // numeric(15,4);
  private BigDecimal C23; // numeric(15,4);
  private BigDecimal C24; // numeric(15,4);
  private BigDecimal C25; // numeric(15,4);
  private BigDecimal C26; // numeric(15,4);
  private BigDecimal C27; // numeric(15,4);
  private BigDecimal C28; // numeric(15,4);
  private BigDecimal C29; // numeric(15,4);
  private BigDecimal C30; // numeric(15,4);
  private BigDecimal C31; // numeric(15,4);
  private BigDecimal C32; // numeric(15,4);
  private BigDecimal C33; // numeric(15,4);
  private Integer C34; // int(11);
  private BigDecimal C35; // numeric(15,4);
  private BigDecimal C36; // numeric(15,4);
  private BigDecimal C37; // numeric(15,4);
  private Integer C38; // int(11);
  private Integer C39; // int(11);
  private BigDecimal C40; // numeric(15,4);
  private BigDecimal C41; // numeric(15,4);

  public SuiAlcantarillado() {
  }

  public SuiAlcantarillado(Integer periodo, Integer C01) {
    SuiAlcantarilladoPk pk = new SuiAlcantarilladoPk(periodo, C01);
    this.suiAlcantarilladoPk = pk;
  }

  public SuiAlcantarilladoPk getSuiAlcantarilladoPk() {
    return suiAlcantarilladoPk;
  }

  public void setSuiAlcantarilladoPk(SuiAlcantarilladoPk suiAlcantarilladoPk) {
    this.suiAlcantarilladoPk = suiAlcantarilladoPk;
  }

  public Integer getC02() {
    return C02;
  }

  public void setC02(Integer c02) {
    C02 = c02;
  }

  public String getC03() {
    return C03;
  }

  public void setC03(String c03) {
    C03 = c03;
  }

  public String getC04() {
    return C04;
  }

  public void setC04(String c04) {
    C04 = c04;
  }

  public String getC05() {
    return C05;
  }

  public void setC05(String c05) {
    C05 = c05;
  }

  public String getC06() {
    return C06;
  }

  public void setC06(String c06) {
    C06 = c06;
  }

  public String getC07() {
    return C07;
  }

  public void setC07(String c07) {
    C07 = c07;
  }

  public String getC08() {
    return C08;
  }

  public void setC08(String c08) {
    C08 = c08;
  }

  public String getC09() {
    return C09;
  }

  public void setC09(String c09) {
    C09 = c09;
  }

  public String getC10() {
    return C10;
  }

  public void setC10(String c10) {
    C10 = c10;
  }

  public Integer getC11() {
    return C11;
  }

  public void setC11(Integer c11) {
    C11 = c11;
  }

  public Date getC12() {
    return C12;
  }

  public void setC12(Date c12) {
    C12 = c12;
  }

  public Date getC13() {
    return C13;
  }

  public void setC13(Date c13) {
    C13 = c13;
  }

  public Integer getC14() {
    return C14;
  }

  public void setC14(Integer c14) {
    C14 = c14;
  }

  public Integer getC15() {
    return C15;
  }

  public void setC15(Integer c15) {
    C15 = c15;
  }

  public Integer getC16() {
    return C16;
  }

  public void setC16(Integer c16) {
    C16 = c16;
  }

  public Integer getC17() {
    return C17;
  }

  public void setC17(Integer c17) {
    C17 = c17;
  }

  public Integer getC18() {
    return C18;
  }

  public void setC18(Integer c18) {
    C18 = c18;
  }

  public Integer getC19() {
    return C19;
  }

  public void setC19(Integer c19) {
    C19 = c19;
  }

  public Integer getC20() {
    return C20;
  }

  public void setC20(Integer c20) {
    C20 = c20;
  }

  public BigDecimal getC21() {
    return C21;
  }

  public void setC21(BigDecimal c21) {
    C21 = c21;
  }

  public BigDecimal getC22() {
    return C22;
  }

  public void setC22(BigDecimal c22) {
    C22 = c22;
  }

  public BigDecimal getC23() {
    return C23;
  }

  public void setC23(BigDecimal c23) {
    C23 = c23;
  }

  public BigDecimal getC24() {
    return C24;
  }

  public void setC24(BigDecimal c24) {
    C24 = c24;
  }

  public BigDecimal getC25() {
    return C25;
  }

  public void setC25(BigDecimal c25) {
    C25 = c25;
  }

  public BigDecimal getC26() {
    return C26;
  }

  public void setC26(BigDecimal c26) {
    C26 = c26;
  }

  public BigDecimal getC27() {
    return C27;
  }

  public void setC27(BigDecimal c27) {
    C27 = c27;
  }

  public BigDecimal getC28() {
    return C28;
  }

  public void setC28(BigDecimal c28) {
    C28 = c28;
  }

  public BigDecimal getC29() {
    return C29;
  }

  public void setC29(BigDecimal c29) {
    C29 = c29;
  }

  public BigDecimal getC30() {
    return C30;
  }

  public void setC30(BigDecimal c30) {
    C30 = c30;
  }

  public BigDecimal getC31() {
    return C31;
  }

  public void setC31(BigDecimal c31) {
    C31 = c31;
  }

  public BigDecimal getC32() {
    return C32;
  }

  public void setC32(BigDecimal c32) {
    C32 = c32;
  }

  public BigDecimal getC33() {
    return C33;
  }

  public void setC33(BigDecimal c33) {
    C33 = c33;
  }

  public Integer getC34() {
    return C34;
  }

  public void setC34(Integer c34) {
    C34 = c34;
  }

  public BigDecimal getC35() {
    return C35;
  }

  public void setC35(BigDecimal c35) {
    C35 = c35;
  }

  public BigDecimal getC36() {
    return C36;
  }

  public void setC36(BigDecimal c36) {
    C36 = c36;
  }

  public BigDecimal getC37() {
    return C37;
  }

  public void setC37(BigDecimal c37) {
    C37 = c37;
  }

  public Integer getC38() {
    return C38;
  }

  public void setC38(Integer c38) {
    C38 = c38;
  }

  public Integer getC39() {
    return C39;
  }

  public void setC39(Integer c39) {
    C39 = c39;
  }

  public BigDecimal getC40() {
    return C40;
  }

  public void setC40(BigDecimal c40) {
    C40 = c40;
  }

  public BigDecimal getC41() {
    return C41;
  }

  public void setC41(BigDecimal c41) {
    C41 = c41;
  }
}
