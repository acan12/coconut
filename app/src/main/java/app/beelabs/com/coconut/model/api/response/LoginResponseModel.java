package app.beelabs.com.coconut.model.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import app.beelabs.com.codebase.base.response.BaseResponse;

/**
 * Created by clappingape on 28/03/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseModel extends BaseResponse {

    private MetaResponse meta;
    private DataResponse data;

    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DataResponse {

        @JsonProperty("response_code")
        private String responseCode;
        @JsonProperty("sales_name")
        private String SalesName;
        @JsonProperty("email")
        private String Email;
        @JsonProperty("description_code")
        private String DescriptionCode;
        @JsonProperty("phone")
        private String Phone;
        @JsonProperty("photo")
        private String Photo;
        @JsonProperty("session_token")
        private String sessionToken;
        private String status;

        private MetaResponse meta;

        public MetaResponse getMeta() {
            return meta;
        }

        public void setMeta(MetaResponse meta) {
            this.meta = meta;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getSalesName() {
            return SalesName;
        }

        public void setSalesName(String SalesName) {
            this.SalesName = SalesName;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getDescriptionCode() {
            return DescriptionCode;
        }

        public void setDescriptionCode(String DesriptionCode) {
            this.DescriptionCode = DesriptionCode;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getSessionToken() {
            return sessionToken;
        }

        public void setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}