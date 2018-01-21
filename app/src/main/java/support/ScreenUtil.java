package support;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by arysuryawan on 1/16/18.
 */

public class ScreenUtil {

    public static int getScreenWidth(Activity x) {
        DisplayMetrics metrics = new DisplayMetrics();
        x.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity x) {
        DisplayMetrics metrics = new DisplayMetrics();
        x.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
