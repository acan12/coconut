package app.beelabs.com.codebase.base;

import android.util.Log;

import app.beelabs.com.codebase.base.response.BaseResponse;

public class BasePresenter {
    private static BasePresenter presenter;


    public static BasePresenter getInstance(BasePresenter x) {
        try {
            if (presenter == null) {

                presenter = x ;
                return presenter;
            }
        } catch (Exception e) {
            Log.e("ERRORxx", e.getMessage());
        }

        return null;
    }

    public void call() {
    }

    public void presenterCallback(BasePresenter.OnPresenterResponseCallback presenterResponse){

    }

    public static class OnPresenterResponseCallback {

        public void call(BaseResponse br){

        }

    }

}
