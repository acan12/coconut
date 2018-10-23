package app.beelabs.com.codebase.base;

import android.util.Log;

import app.beelabs.com.codebase.base.response.BaseResponse;

public class BasePresenter implements IPresenter{
    private static BasePresenter presenter;
    private IView iview;


    public static BasePresenter getInstance(BasePresenter bp) {
        try {
            if (presenter == null) {

                presenter = bp ;
                return presenter;
            }
        } catch (Exception e) {
            Log.e("ERRORxx", e.getMessage());
        }

        return null;
    }

    @Override
    public void call() {

    }

    @Override
    public void done() {

    }



    public static class OnPresenterResponseCallback {

        public void call(BaseResponse br){

        }

    }

}
