package app.beelabs.com.coconut.model.api.request;

public class PhoneRequestModel {
    private String phoneNumber;

    public PhoneRequestModel(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
