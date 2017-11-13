package app.beelabs.com.codebase.support.util;

import android.os.Build;

/**
 * Created by arysuryawan on 9/29/17.
 */

public class FilterUtil {
    public static final int ANDROID_VERSION_LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;
    public static final String EQUAL_OR_ABOVE = ">=";
    public static final String EQUAL = "==";

    public static boolean isAndroidVersion(int androidVersion, String operator){
        int version = Build.VERSION.SDK_INT;
        boolean result = false;
        switch(operator){
            case ">=":
                result = version >= androidVersion;
                break;
            case "==":
                result = version == androidVersion;
                break;
        }

        return result;
    }
}
