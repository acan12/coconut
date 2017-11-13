package app.beelabs.com.coconut.presenter.dao;

import app.beelabs.com.coconut.model.api.Api;
import app.beelabs.com.coconut.ui.base.BaseAppActivity;
import app.beelabs.com.codebase.base.BaseDao;
import retrofit2.Callback;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class ResourceDao extends BaseDao {

    public ResourceDao(BaseAppActivity ac) {
        super(ac);
    }

    public void getSourcesDAO(BaseAppActivity ac, Callback callback) {
        Api.doApiSources(ac, callback);
    }

    public void getArticleDAO(BaseAppActivity ac, Callback callback) {
        Api.doApiArticles(ac, callback);
    }
}
