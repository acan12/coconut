package app.beelabs.com.coconut.presenter;

import android.util.Log;

import app.beelabs.com.coconut.model.dao.ResourceDao;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.IPresenter;
import retrofit2.Response;

public class ResourcePresenter extends BasePresenter implements ResourceDao.IResourceDao {


    @Override
    public void postPhoneNumber(String phone, IPresenter iView, int apiKey) {
        Log.d("", "");
        (new ResourceDao()).postPhoneNumber(phone, iView, apiKey);
    }

    @Override
    public void callSource(IPresenter iView, int apiKey) {
        (new ResourceDao()).getSource(iView, apiKey);
    }

    @Override
    public void responseHandler(IPresenter presenter, int responseApiCode, Response response) {
        super.responseHandler(presenter, responseApiCode, response);
    }
}
