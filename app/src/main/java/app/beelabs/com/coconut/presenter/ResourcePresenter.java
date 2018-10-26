package app.beelabs.com.coconut.presenter;

import android.util.Log;

import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.model.dao.ResourceDao;
import app.beelabs.com.coconut.ui.IMainView;
import app.beelabs.com.coconut.ui.fragment.IMainFragmentView;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;

public class ResourcePresenter extends BasePresenter implements ResourceDao.IResourceDao {

    private IMainView iv;
    private IMainFragmentView ifv;


    public ResourcePresenter(IMainView iv) {
        this.iv = iv;
    }

    public ResourcePresenter(IMainFragmentView ifv) {
        this.ifv = ifv;
    }


    //    public IMainView getView() {
//        return this.iv;
//    }

    @Override
    public BasePresenter getPresenter() {
        return this;
    }

    @Override
    public void postPhoneNumber(String phone) {
        Log.d("", "");
        (new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                SummaryResponse model = (SummaryResponse) br;
                ifv.handleDataSummary(model);

            }
        })).postPhoneNumber(phone);
//        (new ResourceDao((IPresenter) this)).postPhoneNumber(phone);
    }

    @Override
    public void getSource() {
        (new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                SourceResponse model = (SourceResponse) br;
                iv.handleDataSource(model);
            }
        })).getSourceDAO();
    }

}
