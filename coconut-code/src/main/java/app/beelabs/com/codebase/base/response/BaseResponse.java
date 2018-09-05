package app.beelabs.com.codebase.base.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by arysuryawan on 8/19/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {

    private BaseMetaResponse meta;
    private BaseDataResponse data;


    public BaseMetaResponse getMeta() {
        return meta;
    }

    public void setMeta(BaseMetaResponse meta) {
        this.meta = meta;
    }

    public BaseDataResponse getData() {
        return data;
    }

    public void setData(BaseDataResponse data) {
        this.data = data;
    }

}
