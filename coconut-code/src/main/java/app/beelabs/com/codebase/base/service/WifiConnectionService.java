package app.beelabs.com.codebase.base.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;

public class WifiConnectionService {

    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;
    private static WifiConnectionService instance = null;

    public static WifiConnectionService getInstance() {
        if (instance == null) instance = new WifiConnectionService();
        return instance;
    }

    public void initializeConnection(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @SuppressLint({"MissingPermission", "NewApi"})
    public boolean isConnected() {
        NetworkCapabilities cap = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (cap != null) {
            if (cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) return true;
            else if (cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) return true;
            else if (cap.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) return true;
        }

        return false;
    }
}
