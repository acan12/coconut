package app.beelabs.com.codebase.base;

import java.lang.reflect.InvocationTargetException;

import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.LoadingDialogComponent;
import app.beelabs.com.codebase.component.ProgressDialogComponent;

public class BasePresenter implements IPresenter {
    private static BasePresenter presenter;
    private static IView iview;

    public static BasePresenter getInstance(IView iv, BasePresenter bp) {
        iview = iv;
        presenter = bp;

        return presenter;
    }

    public static BasePresenter getInstance(IView iv, Class clazz) {
        iview = iv;
        try {
            synchronized (clazz) {
                presenter = (BasePresenter) Class.forName(clazz.getName()).getConstructor(IView.class).newInstance(iv);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return presenter;
    }

    @Override
    public void call() {

    }

    @Override
    public void done() {
        ProgressDialogComponent.dismissProgressDialog(iview.getCurrentActivity());
        LoadingDialogComponent.closeLoadingDialog(iview.getCurrentActivity(), 0);

    }

    @Override
    public void fail(String message) {
        done();
        iview.handleError(message);
    }

    public static class OnPresenterResponseCallback {

        public void call(BaseResponse br) {

        }

    }

}
