package app.beelabs.com.codebase.support.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * Created by arysuryawan on 11/15/16.
 */

public class DeviceUtil {

    public static boolean isAndroidVersion(int androidVersion, String operator) {
        int version = Build.VERSION.SDK_INT;
        boolean result = false;
        switch (operator) {
            case ">=":
                result = version >= androidVersion;
                break;
            case "==":
                result = version == androidVersion;
                break;
        }

        return result;
    }

    public static String getUUID() {

        return UUID.randomUUID().toString();
    }

    // generate random string in 15 chars
    public static String randomString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            char c = chars.charAt(r.nextInt(chars.length()));
            sb.append(c);
        }
        return sb.toString();
    }
}
