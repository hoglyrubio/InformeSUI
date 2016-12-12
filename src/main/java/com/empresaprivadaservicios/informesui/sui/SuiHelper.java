package com.empresaprivadaservicios.informesui.sui;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SuiHelper {

  public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
  public static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance(Locale.US);
  public static final DecimalFormat decimalFormat3 = new DecimalFormat("#0.000", DECIMAL_FORMAT_SYMBOLS);
  public static final String SEPARATOR = ",";

  public static String toCsv(SuiAcueducto sui) {

    StringBuilder csv = new StringBuilder();

    csv
      .append(sui.getSuiAcueductoPk().getC01()).append(SEPARATOR)
      .append(sui.getC02()).append(SEPARATOR)
      .append(sui.getC03()).append(SEPARATOR)
      .append(sui.getC04()).append(SEPARATOR)
      .append(sui.getC05()).append(SEPARATOR)
      .append(sui.getC06()).append(SEPARATOR)
      .append(sui.getC07()).append(SEPARATOR)
      .append(sui.getC08()).append(SEPARATOR)
      .append(sui.getC09()).append(SEPARATOR)
      .append(sui.getC10()).append(SEPARATOR)
      .append(sui.getC11()).append(SEPARATOR)
      .append(dateFormat.format(sui.getC12())).append(SEPARATOR)
      .append(dateFormat.format(sui.getC13())).append(SEPARATOR)
      .append(sui.getC14()).append(SEPARATOR)
      .append(sui.getC15()).append(SEPARATOR)
      .append(sui.getC16() == null ? "" : sui.getC16()).append(SEPARATOR)
      .append(sui.getC17() == null ? "" : sui.getC17()).append(SEPARATOR)
      .append(sui.getC18() == null ? 0 : sui.getC18()).append(SEPARATOR)
      .append(sui.getC19() == null ? 0 : sui.getC19()).append(SEPARATOR)
      .append(sui.getC20() == null ? 0 : sui.getC20()).append(SEPARATOR)
      .append(sui.getC21() == null ? 0 : sui.getC21()).append(SEPARATOR)
      .append(sui.getC22() == null ? 0 : sui.getC22()).append(SEPARATOR)
      .append(sui.getC23() == null ? 0 : sui.getC23()).append(SEPARATOR)
      .append(sui.getC24() == null ? 0 : sui.getC24()).append(SEPARATOR)
      .append(sui.getC25() == null ? 0 : sui.getC25()).append(SEPARATOR)
      .append(sui.getC26() == null ? 0 : sui.getC26()).append(SEPARATOR)
      .append(sui.getC27() == null ? 0 : sui.getC27()).append(SEPARATOR)
      .append(sui.getC28() == null ? 0 : sui.getC28()).append(SEPARATOR)
      .append(sui.getC29() == null ? 0 : sui.getC29()).append(SEPARATOR)
      .append(sui.getC30() == null ? 0 : sui.getC30()).append(SEPARATOR)
      .append(sui.getC31() == null ? 0 : sui.getC31()).append(SEPARATOR)
      .append(sui.getC32() == null ? 0 : sui.getC32()).append(SEPARATOR)
      .append(decimalFormat3.format(sui.getC33() == null ? 0 : sui.getC33())).append(SEPARATOR)
      .append(decimalFormat3.format(sui.getC34() == null ? 0 : sui.getC34())).append(SEPARATOR)
      .append(sui.getC35() == null ? 0 : sui.getC35()).append(SEPARATOR)
      .append(sui.getC36() == null ? 0 : sui.getC36()).append(SEPARATOR)
      .append(sui.getC37() == null ? 0 : sui.getC37()).append(SEPARATOR)
      .append(sui.getC38() == null ? 0 : sui.getC38()).append(SEPARATOR)
      .append(sui.getC39() == null ? 0 : sui.getC39()).append(SEPARATOR)
      .append(sui.getC40() == null ? 0 : sui.getC40()).append(SEPARATOR)
      .append(sui.getC41() == null ? 0 : sui.getC41()).append(SEPARATOR)
      .append(sui.getC42() == null ? 0 : sui.getC42()).append(SEPARATOR)
      .append(sui.getC43() == null ? 0 : sui.getC43()).append(SEPARATOR)
      .append(sui.getC44() == null ? 0 : sui.getC44()).append(SEPARATOR)
      .append(sui.getC45() == null ? 0 : sui.getC45()).append(SEPARATOR)
      .append(sui.getC46() == null ? "" : sui.getC46()).append(SEPARATOR)
      .append(sui.getC47() == null ? 0 : sui.getC47()).append(SEPARATOR)
      .append(sui.getC48() == null ? 0 : sui.getC48());

    return csv.toString();
  }

  public static boolean isResidencial(SuiCateSuca suiCateSuca) {
    return suiCateSuca.getCasucodi() < 10;
  }

  public static boolean isPositive(BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) > 0;
  }

  public static boolean isNegative(BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) < 0;
  }

  public static boolean isZero(BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) == 0;
  }

  public static boolean isNonZero(BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) != 0;
  }

  public static String toCsv(SuiAlcantarillado sui) {

    StringBuilder csv = new StringBuilder();

    csv
      .append(sui.getSuiAlcantarilladoPk().getC01()).append(SEPARATOR)
      .append(sui.getC02()).append(SEPARATOR)
      .append(sui.getC03()).append(SEPARATOR)
      .append(sui.getC04()).append(SEPARATOR)
      .append(sui.getC05()).append(SEPARATOR)
      .append(sui.getC06()).append(SEPARATOR)
      .append(sui.getC07()).append(SEPARATOR)
      .append(sui.getC08()).append(SEPARATOR)
      .append(sui.getC09()).append(SEPARATOR)
      .append(sui.getC10()).append(SEPARATOR)
      .append(sui.getC11()).append(SEPARATOR)
      .append(dateFormat.format(sui.getC12())).append(SEPARATOR)
      .append(dateFormat.format(sui.getC13())).append(SEPARATOR)
      .append(sui.getC14()).append(SEPARATOR)
      .append(sui.getC15()).append(SEPARATOR)
      .append(sui.getC16() == null ? "" : sui.getC16()).append(SEPARATOR)
      .append(sui.getC17() == null ? "" : sui.getC17()).append(SEPARATOR)
      .append(sui.getC18() == null ? 0 : sui.getC18()).append(SEPARATOR)
      .append(sui.getC19() == null ? 0 : sui.getC19()).append(SEPARATOR)
      .append(sui.getC20() == null ? 0 : sui.getC20()).append(SEPARATOR)
      .append(sui.getC21() == null ? 0 : sui.getC21()).append(SEPARATOR)
      .append(sui.getC22() == null ? 0 : sui.getC22()).append(SEPARATOR)
      .append(sui.getC23() == null ? 0 : sui.getC23()).append(SEPARATOR)
      .append(sui.getC24() == null ? 0 : sui.getC24()).append(SEPARATOR)
      .append(sui.getC25() == null ? 0 : sui.getC25()).append(SEPARATOR)
      .append(sui.getC26() == null ? 0 : sui.getC26()).append(SEPARATOR)
      .append(sui.getC27() == null ? 0 : sui.getC27()).append(SEPARATOR)
      .append(sui.getC28() == null ? 0 : sui.getC28()).append(SEPARATOR)
      .append(sui.getC29() == null ? 0 : sui.getC29()).append(SEPARATOR)
      .append(decimalFormat3.format(sui.getC30() == null ? 0 : sui.getC30())).append(SEPARATOR)
      .append(decimalFormat3.format(sui.getC31() == null ? 0 : sui.getC31())).append(SEPARATOR)
      .append(sui.getC32() == null ? 0 : sui.getC32()).append(SEPARATOR)
      .append(sui.getC33() == null ? 0 : sui.getC33()).append(SEPARATOR)
      .append(sui.getC34() == null ? 0 : sui.getC34()).append(SEPARATOR)
      .append(sui.getC35() == null ? 0 : sui.getC35()).append(SEPARATOR)
      .append(sui.getC36() == null ? 0 : sui.getC36()).append(SEPARATOR)
      .append(sui.getC37() == null ? 0 : sui.getC37()).append(SEPARATOR)
      .append(sui.getC38() == null ? 0 : sui.getC38()).append(SEPARATOR)
      .append(sui.getC39() == null ? "" : sui.getC39()).append(SEPARATOR)
      .append(sui.getC40() == null ? 0 : sui.getC40()).append(SEPARATOR)
      .append(sui.getC41() == null ? 0 : sui.getC41());

    return csv.toString();
  }

}
