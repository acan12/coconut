package app.beelabs.com.codebase.base.exception;

import java.io.IOException;

public class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No network available, please check your WiFi or Data connection";
    }
}
