package app.beelabs.com.codebase.support.rx;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import app.beelabs.com.codebase.base.BaseDialog;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.base.exception.NoConnectivityException;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.dialog.CoconutAlertNoConnectionDialog;
import app.beelabs.com.codebase.component.dialog.ProgressDialogComponent;
import app.beelabs.com.codebase.component.dialog.SpinKitLoadingDialogComponent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class RxObserver<P extends BaseResponse> implements Observer {
    private IView iv;
    private String messageLoading;
    private long timeMilis;
    private int dialogType;
    private static BaseDialog dialogNoconnection;

    public interface DialogTypeEnum {
        int DEFAULT = 0;
        int SPINKIT = 1;
    }

    public RxObserver(IView iv) {
        this.iv = iv;
    }

    public RxObserver(IView iv, String messageLoading) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMilis = 0;
    }

    public RxObserver(IView iv, String messageLoading, long timeMilis) {
        this.iv = iv;
        this.messageLoading = messageLoading;
        this.timeMilis = timeMilis;
    }

    public RxObserver setDialogType(int dialogType) {
        this.dialogType = dialogType;
        return this;
    }

    @Override
    public void onSubscribe(Disposable d) {
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
        SpinKitLoadingDialogComponent.dismissProgressDialog(iv.getCurrentActivity(), timeMilis);
        if (messageLoading != null) {
            switch (dialogType) {
                case DialogTypeEnum.DEFAULT:
                    ProgressDialogComponent.showProgressDialog(iv.getCurrentActivity(), messageLoading, false);
                    break;

                case DialogTypeEnum.SPINKIT:
                    SpinKitLoadingDialogComponent.showProgressDialog(iv.getCurrentActivity(), messageLoading, timeMilis);
                    break;
            }
        }
    }

    @Override
    public void onNext(Object o) {
        SpinKitLoadingDialogComponent.dismissProgressDialog(iv.getCurrentActivity(), timeMilis);
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
    }

    @Override
    public void onError(Throwable e) {
        ProgressDialogComponent.dismissProgressDialog(iv.getCurrentActivity());
        SpinKitLoadingDialogComponent.dismissProgressDialog(iv.getCurrentActivity(), timeMilis);

        if (e instanceof NoConnectivityException) {
            if (dialogNoconnection != null)
                if (dialogNoconnection.isShowing()) dialogNoconnection.dismiss();

            dialogNoconnection = new CoconutAlertNoConnectionDialog(iv.getCurrentActivity());
            dialogNoconnection.show();
            return;
        }

    }

    public Object tryParsingErrorResponse(Throwable e) {
        try {
            HttpException error = (HttpException) e;
            String message = "Error network connection";
            if (!error.message().isEmpty()) message = error.message();
            String jsonResponse =
                    "{\"meta\":{\"status\":false,\"code\":${error.code()},\"message\":\"${message}\"},\"data\":null}";
            ObjectMapper objMapper = new ObjectMapper();
            objMapper.readValue(jsonResponse, Object.class);
            ResponseBody responseBody = error.response().errorBody();

            objMapper.readValue(responseBody.string(), Object.class);
            return objMapper;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }


    @Override
    public void onComplete() {
    }
}
