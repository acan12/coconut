package app.beelabs.com.codebase.base.contract;

import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.response.BaseResponse;

public interface IView {
    BaseActivity getCurrentActivity();

    void handleSuccess(BaseResponse response);
    void handleErrorResponse(BaseResponse response);

    void handleError(String message);

    void handleReconnection();

}
