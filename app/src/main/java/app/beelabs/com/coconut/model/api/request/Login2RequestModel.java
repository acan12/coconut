package app.beelabs.com.coconut.model.api.request;

/**
 * Created by clappingape on 21/03/18.
 */

public class Login2RequestModel {

    private String url;
    private String method;
    private DataRequestModel data;


    public Login2RequestModel(String url, String method, DataRequestModel data) {
        this.url = url;
        this.method = method;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public DataRequestModel getData() {
        return data;
    }

    public void setData(DataRequestModel data) {
        this.data = data;
    }

    public static class DataRequestModel {

        private String phoneNumber;
        private String pin;
        private String deviceId;
        private String deviceToken;



        public DataRequestModel(String phoneNumber, String pin, String deviceId, String deviceToken) {
            this.phoneNumber = phoneNumber;
            this.pin = pin;
            this.deviceId = deviceId;
            this.deviceToken = deviceToken;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

    }
}
