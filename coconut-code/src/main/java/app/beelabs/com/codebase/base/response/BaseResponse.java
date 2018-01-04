package app.beelabs.com.codebase.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by arysuryawan on 8/19/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
