package app.beelabs.com.codebase.support.util;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import app.beelabs.com.codebase.BuildConfig;

public class SecurityUtil {

    @SuppressLint("MissingPermission")
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return false;
    }


    public static boolean isPackageInstalled(String[] packagenames, PackageManager packageManager) {
        for (String packageName : packagenames) {
            try {
                packageManager.getPackageGids(packageName);
            } catch (PackageManager.NameNotFoundException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRooted() {
        String binaryName = "su";

        boolean found = false;
        if (!found) {
            String[] places = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
                    "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
            for (String where : places) {
                if (new File(where + binaryName).exists()) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public static boolean isMockLocationEnabled(Context context) {
        boolean isMockLocation = false;

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AppOpsManager opsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                isMockLocation = (opsManager.checkOp(AppOpsManager.OPSTR_MOCK_LOCATION, android.os.Process.myUid(),
                        BuildConfig.APPLICATION_ID) == AppOpsManager.MODE_ALLOWED);

            } else {
                isMockLocation = !android.provider.Settings.Secure.getString(context.getContentResolver(),
                        "mock_location").equals("0");
            }

        } catch (Exception e) {
            Log.e("", e.getMessage());
            return isMockLocation;
        }

        return isMockLocation;
    }
}