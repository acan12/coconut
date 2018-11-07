package app.beelabs.com.codebase.base.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by arysuryawan on 8/19/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {

    @JsonProperty("meta")
    private DefaultMetaResponse baseMeta;
    @JsonProperty("data")
    private DefaultDataResponse baseData;

    private String status;


    public DefaultMetaResponse getBaseMeta() {
        return baseMeta;
    }

    public void setBaseMeta(DefaultMetaResponse baseMeta) {
        this.baseMeta = baseMeta;
    }

    public DefaultDataResponse getBaseData() {
        return baseData;
    }

    public void setBaseData(DefaultDataResponse baseData) {
        this.baseData = baseData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
