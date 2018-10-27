package app.beelabs.com.codebase.support.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import app.beelabs.com.codebase.IConfig;

public class DateUtil {

    public static Date convertToDate(long epoch) {
        return new Date((epoch) * 1000L);
    }

    public static long convertToEpoch(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() / 1000) + 86400;
    }

    public static int getAge(Date date) {
        if (date == null) {
            return 0;
        }

        Calendar calDob = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        calDob.setTime(date);
        calNow.setTime(new Date());

        int difInMonths = calNow.get(Calendar.MONTH) - calDob.get(Calendar.MONTH);
        int age = calNow.get(Calendar.YEAR) - calDob.get(Calendar.YEAR);
        return (difInMonths < 0) ? age - 1 : age;
    }

    public static String formatStringInBahasa(Date date) {
        return formatString(date, "dd MMMM yyyy" );
    }

    private static String formatString(Date date, String pattern) {

        SimpleDateFormat form = new SimpleDateFormat(pattern);
        String format = form.format(date);

        String dateString = "";
        for (String month : IConfig.monthLabels) {
            String[] labels = month.split("_");
            dateString = format.replaceAll(labels[0], labels[1]);
            if (!dateString.equals(format)) break;
        }

        return dateString;
    }
}
