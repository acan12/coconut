package app.beelabs.com.codebase.support.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtil {

    public static String convertIDRCurrencyFormat(Double amount) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(amount).replace("Rp", "Rp ");
    }

}
