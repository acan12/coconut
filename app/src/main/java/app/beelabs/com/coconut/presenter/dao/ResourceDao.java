package app.beelabs.com.coconut.presenter.dao;

import app.beelabs.com.coconut.model.api.Api;
import app.beelabs.com.codebase.base.BaseActivity;
import app.beelabs.com.codebase.base.BaseDao;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class ResourceDao extends BaseDao {

    public ResourceDao(BaseActivity ac) {
        super(ac);
    }

    public void getSourcesDAO(BaseActivity ac, Callback callback) {
        Api.doApiSources(ac, callback);
    }

    public void getArticleDAO(BaseActivity ac, Callback callback) {
        Api.doApiArticles(ac, callback);
    }
}
