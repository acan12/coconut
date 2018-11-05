package app.beelabs.com.coconut.presenter;

import android.util.Log;

import java.io.File;
import java.util.List;

import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.model.dao.ResourceDao;
import app.beelabs.com.coconut.ui.IMainView;
import app.beelabs.com.coconut.ui.fragment.IMainFragmentView;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import io.reactivex.Observable;

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

    @Override
    public Observable<SourceResponse> getSourceRX() {
        return (new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                SourceResponse model = (SourceResponse) br;
                iv.handleDataSource(model);
            }
        })).getSourceRXDAO();
    }


    @Override
    public void uploadFile(String noteVal, String startTimeVal, String endTimeVal,
                           String startDateVal, String endDateVal,
                           String employeeIdVal, File file) {

        (new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                BaseResponse model = br;
                iv.handleDataUpload(model);
            }
        })).postingUploadFile(noteVal, startTimeVal, endTimeVal,
                startDateVal, endDateVal, employeeIdVal, file);
    }
}
