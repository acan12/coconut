package app.beelabs.com.coconut.model.dao;

import app.beelabs.com.coconut.model.api.Api;
import app.beelabs.com.codebase.base.BaseDao;
import app.beelabs.com.codebase.base.IDao;
import app.beelabs.com.codebase.base.IPresenter;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class ResourceDao extends BaseDao {

    public interface IResourceDao extends IDao {
        void postPhoneNumber(String phone, IPresenter iView, int apiKey);

        void callSource(IPresenter iView, int apiKey);

    }


    public ResourceDao() {
    }

    public void getSource(IPresenter iView, int apiKey) {
        Api.doApiSources(iView, BaseDao.getInstance(iView, apiKey).callback);
    }

//    public void getArticleDAO(IPresenter iView, Callback callback) {
//        Api.doApiArticles(iView.getBaseActivity(), callback);
//    }

    public void postPhoneNumber(String phone, IPresenter iView, int apiKey){
        Api.doTestFin(phone, iView, BaseDao.getInstance(iView, apiKey).callback);
    }
}
