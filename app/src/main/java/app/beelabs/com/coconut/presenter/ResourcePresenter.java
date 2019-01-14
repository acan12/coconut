package app.beelabs.com.coconut.presenter;

import android.content.Context;
import android.util.Log;

import java.io.File;

import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.model.dao.ResourceDao;
import app.beelabs.com.coconut.ui.IMainView;
import app.beelabs.com.coconut.ui.fragment.IMainFragmentView;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.component.LoadingDialogComponent;
import app.beelabs.com.codebase.support.rx.RxObserver;
import io.reactivex.disposables.Disposable;

public class ResourcePresenter extends BasePresenter implements ResourceDao.IResourceDao {

    private IMainView iv;
    private IMainFragmentView ifv;


    public ResourcePresenter(IMainView iv) {
        this.iv = iv;
    }

    public ResourcePresenter(IMainFragmentView ifv) {
        this.ifv = ifv;
    }


    @Override
    public BasePresenter getPresenter() {
        return this;
    }

    @Override
    public Context getContext() {
        return iv.getBaseActivity();
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
    }

    @Override
    public void getProfileRX() {
        new ResourceDao(this).getProfileRxDAO()
                .subscribe(new RxObserver<ProfileResponseModel>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        iv.handleProcessing();
                    }

                    @Override
                    public void onNext(Object o) {
                        iv.handleProfileDone((ProfileResponseModel) o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iv.handleFail(e.getMessage());
                    }

                });

    }


    @Override
    public void getSource() {
        new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                SourceResponse model = (SourceResponse) br;
                iv.handleDataSource(model);
            }
        }).getSourceDAO();
    }

    @Override
    public void getSourceRX() {
        new ResourceDao(this).getSourceRXDAO()
                .subscribe(new RxObserver<ProfileResponseModel>() {
                    @Override
                    public void onNext(Object o) {
                        LoadingDialogComponent.closeDialog(iv.getBaseActivity());
                        iv.handleDataSource((SourceResponse) o);
                    }
                });
        ;
    }


    @Override
    public void uploadFile(String noteVal, String startTimeVal, String endTimeVal,
                           String startDateVal, String endDateVal,
                           String employeeIdVal, File file) {

        new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                BaseResponse model = br;
                iv.handleDataUpload(model);
            }
        }).postingUploadFile(noteVal, startTimeVal, endTimeVal,
                startDateVal, endDateVal, employeeIdVal, file);
    }
}
