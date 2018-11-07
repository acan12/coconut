package app.beelabs.com.coconut.model.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.beelabs.com.codebase.base.response.DefaultDataResponse;
import app.beelabs.com.codebase.base.response.DefaultMetaResponse;
import app.beelabs.com.codebase.base.response.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileResponseModel extends BaseResponse {

    private DefaultMetaResponse meta;
    private DataResponseModel data;

    public DefaultMetaResponse getMeta() {
        return meta;
    }

    public void setMeta(DefaultMetaResponse meta) {
        this.meta = meta;
    }

    public DataResponseModel getData() {
        return data;
    }

    public void setData(DataResponseModel data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DataResponseModel {
        private int id;
        private String full_name;
        private String email;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
