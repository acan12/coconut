package app.beelabs.com.codebase.base;

import app.beelabs.com.codebase.base.response.BaseResponse;
import retrofit2.Response;

public interface IDao {

    BasePresenter getPresenter();
    void onApiResponseCallback(BaseResponse br, int responseCode, Response response);
    void onApiFailureCallback(String message);
}
