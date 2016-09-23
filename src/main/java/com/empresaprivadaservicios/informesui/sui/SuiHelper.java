package com.empresaprivadaservicios.informesui.sui;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SuiHelper {

  public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
  public static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = DecimalFormatSymbols.getInstance(Locale.US);
  public static final DecimalFormat decimalFormat3 = new DecimalFormat("#0.000", DECIMAL_FORMAT_SYMBOLS);
  public static final String SEPARATOR = ",";

  public static String toCsv(SuiAcueducto suiAcueducto) {

    StringBuilder csv = new StringBuilder();

    csv
      .append(suiAcueducto.getSuiAcueductoPk().getC01()).append(SEPARATOR)
      .append(suiAcueducto.getC02()).append(SEPARATOR)
      .append(suiAcueducto.getC03()).append(SEPARATOR)
      .append(suiAcueducto.getC04()).append(SEPARATOR)
      .append(suiAcueducto.getC05()).append(SEPARATOR)
      .append(suiAcueducto.getC06()).append(SEPARATOR)
      .append(suiAcueducto.getC07()).append(SEPARATOR)
      .append(suiAcueducto.getC08()).append(SEPARATOR)
      .append(suiAcueducto.getC09()).append(SEPARATOR)
      .append(suiAcueducto.getC10()).append(SEPARATOR)
      .append(suiAcueducto.getC11()).append(SEPARATOR)
      .append(dateFormat.format(suiAcueducto.getC12())).append(SEPARATOR)
      .append(dateFormat.format(suiAcueducto.getC13())).append(SEPARATOR)
      .append(suiAcueducto.getC14()).append(SEPARATOR)
      .append(suiAcueducto.getC15()).append(SEPARATOR)
      .append(suiAcueducto.getC16() == null ? "" : suiAcueducto.getC16()).append(SEPARATOR)
      .append(suiAcueducto.getC17() == null ? "" : suiAcueducto.getC17()).append(SEPARATOR)
      .append(suiAcueducto.getC18() == null ? 0 : suiAcueducto.getC18()).append(SEPARATOR)
      .append(suiAcueducto.getC19() == null ? 0 : suiAcueducto.getC19()).append(SEPARATOR)
      .append(suiAcueducto.getC20() == null ? 0 : suiAcueducto.getC20()).append(SEPARATOR)
      .append(suiAcueducto.getC21() == null ? 0 : suiAcueducto.getC21()).append(SEPARATOR)
      .append(suiAcueducto.getC22() == null ? 0 : suiAcueducto.getC22()).append(SEPARATOR)
      .append(suiAcueducto.getC23() == null ? 0 : suiAcueducto.getC23()).append(SEPARATOR)
      .append(suiAcueducto.getC24() == null ? 0 : suiAcueducto.getC24()).append(SEPARATOR)
      .append(suiAcueducto.getC25() == null ? 0 : suiAcueducto.getC25()).append(SEPARATOR)
      .append(suiAcueducto.getC26() == null ? 0 : suiAcueducto.getC26()).append(SEPARATOR)
      .append(suiAcueducto.getC27() == null ? 0 : suiAcueducto.getC27()).append(SEPARATOR)
      .append(suiAcueducto.getC28() == null ? 0 : suiAcueducto.getC28()).append(SEPARATOR)
      .append(suiAcueducto.getC29() == null ? 0 : suiAcueducto.getC29()).append(SEPARATOR)
      .append(suiAcueducto.getC30() == null ? 0 : suiAcueducto.getC30()).append(SEPARATOR)
      .append(suiAcueducto.getC31() == null ? 0 : suiAcueducto.getC21()).append(SEPARATOR)
      .append(suiAcueducto.getC32() == null ? 0 : suiAcueducto.getC32()).append(SEPARATOR)
      .append(decimalFormat3.format(suiAcueducto.getC33() == null ? 0 : suiAcueducto.getC33())).append(SEPARATOR)
      .append(decimalFormat3.format(suiAcueducto.getC34() == null ? 0 : suiAcueducto.getC34())).append(SEPARATOR)
      .append(suiAcueducto.getC35() == null ? 0 : suiAcueducto.getC35()).append(SEPARATOR)
      .append(suiAcueducto.getC36() == null ? 0 : suiAcueducto.getC36()).append(SEPARATOR)
      .append(suiAcueducto.getC37() == null ? 0 : suiAcueducto.getC37()).append(SEPARATOR)
      .append(suiAcueducto.getC38() == null ? 0 : suiAcueducto.getC38()).append(SEPARATOR)
      .append(suiAcueducto.getC39() == null ? 0 : suiAcueducto.getC39()).append(SEPARATOR)
      .append(suiAcueducto.getC40() == null ? 0 : suiAcueducto.getC40()).append(SEPARATOR)
      .append(suiAcueducto.getC41() == null ? 0 : suiAcueducto.getC41()).append(SEPARATOR)
      .append(suiAcueducto.getC42() == null ? 0 : suiAcueducto.getC42()).append(SEPARATOR)
      .append(suiAcueducto.getC43() == null ? 0 : suiAcueducto.getC43()).append(SEPARATOR)
      .append(suiAcueducto.getC44() == null ? 0 : suiAcueducto.getC44()).append(SEPARATOR)
      .append(suiAcueducto.getC45() == null ? 0 : suiAcueducto.getC45()).append(SEPARATOR)
      .append(suiAcueducto.getC46() == null ? "" : suiAcueducto.getC46()).append(SEPARATOR)
      .append(suiAcueducto.getC47() == null ? 0 : suiAcueducto.getC47()).append(SEPARATOR)
      .append(suiAcueducto.getC48() == null ? 0 : suiAcueducto.getC48());

    return csv.toString();
  }

  public static boolean isResidencial(SuiCateSuca suiCateSuca) {
    return suiCateSuca.getCasucodi() < 10;
  }

  public static double round(double value, int decimals) {
    double factor = Math.pow(10, decimals);
    return Math.round(value * factor) / factor;
  }

}
