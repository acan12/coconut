package app.beelabs.com.coconut.presenter;

import android.content.Context;

import java.io.File;

import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.model.dao.ResourceDao;
import app.beelabs.com.coconut.ui.IMainView;
import app.beelabs.com.coconut.ui.fragment.IMainFragmentView;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.support.rx.RxObserver;
import io.reactivex.disposables.Disposable;

public class ResourcePresenter extends BasePresenter implements ResourceDao.IResourceDao {

    private IView iv;

    public ResourcePresenter(IView iv) {
        this.iv = iv;
    }


    @Override
    public BasePresenter getPresenter() {
        return this;
    }

    @Override
    public Context getContext() {
        return iv.getCurrentActivity();
    }

    @Override
    public void postPhoneNumber(String phone) {
        new ResourceDao(this).postPhoneNumber(phone)
                .subscribe(new RxObserver<SummaryResponse>(iv, "Ambil Data Summary..."){
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);

                    }

                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        ((IMainFragmentView)iv).handleDataSummary((SummaryResponse) o);
                    }
                });

    }

    @Override
    public void getProfileRX() {
        new ResourceDao(this).getProfileRxDAO()
                .subscribe(new RxObserver<ProfileResponseModel>(iv, null) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        ((IMainView)iv).handleProcessing();
                    }

                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        ((IMainView)iv).handleProfileDone((ProfileResponseModel) o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ((IMainView)iv).handleError(e.getMessage());
                    }

                });

    }


    @Override
    public void getSource() {
        new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                SourceResponse model = (SourceResponse) br;
                ((IMainView)iv).handleDataSource(model);
            }
        }).getSourceDAO();
    }

    @Override
    public void getSourceRX(String messageLoading) {
        new ResourceDao(this).getSourceRXDAO()
                .subscribe(new RxObserver<ProfileResponseModel>(iv, messageLoading) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        ((IMainView)iv).handleDataSource((SourceResponse) o);
                    }
                });
    }

    @Override
    public void uploadFile(String noteVal, String startTimeVal, String endTimeVal,
                           String startDateVal, String endDateVal,
                           String employeeIdVal, File file) {

        new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                BaseResponse model = br;
                ((IMainView)iv).handleDataUpload(model);
            }
        }).postingUploadFile(noteVal, startTimeVal, endTimeVal,
                startDateVal, endDateVal, employeeIdVal, file);
    }
}
