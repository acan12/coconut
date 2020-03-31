package app.beelabs.com.coconut.presenter;

import java.io.File;

import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.model.dao.ResourceDao;
import app.beelabs.com.coconut.ui.IMainView;
import app.beelabs.com.coconut.ui.ISecondView;
import app.beelabs.com.coconut.ui.fragment.IMainFragmentView;
import app.beelabs.com.codebase.base.BasePresenter;
import app.beelabs.com.codebase.base.contract.IView;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.support.rx.RxObserver;
import io.reactivex.disposables.Disposable;

public class ResourcePresenter extends BasePresenter implements ResourceDao.IResourceDao {

    private IView iView;
//    private IMainView imv;
//
//    private IMainFragmentView ifv;

    public ResourcePresenter(IView iv) {
        this.iView = iv;
//        this.imv = (iv instanceof IMainView) ? (IMainView) iv : null;
//        this.ifv = (iv instanceof IMainFragmentView) ? (IMainFragmentView) iv : null;
    }


    @Override
    public BasePresenter getPresenter() {
        return this;
    }


    @Override
    public void postPhoneNumber(String phone) {
        new ResourceDao(this).postPhoneNumber(phone)
                .subscribe(new RxObserver<SummaryResponse>(iView, "Ambil Data Summary...", 10000) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);

                    }

                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        if (iView instanceof IMainFragmentView)
                            ((IMainFragmentView) iView).handleDataSummary((SummaryResponse) o);
                    }
                });

    }

    @Override
    public void getProfileRX() {
        new ResourceDao(this).getProfileRxDAO()
                .subscribe(new RxObserver<ProfileResponseModel>(iView, null, 500) {

                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        ((IMainView) iView).handleProcessing();
                    }

                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        ((IMainView) iView).handleProfileDone((ProfileResponseModel) o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        iView.handleError(e.getMessage());
                    }

                });

    }

    @Override
    public void getSource() {
        new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                SourceResponse model = (SourceResponse) br;
                ((IMainView) iView).handleDataSource(model);
            }
        }).getSourceDAO();
    }

    @Override
    public void getSourceRX(String messageLoading) {
        new ResourceDao(this).getSourceRXDAO()
                .subscribe(new RxObserver<ProfileResponseModel>(iView, messageLoading) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        if (iView instanceof IMainView)
                            ((IMainView) iView).handleDataSource((SourceResponse) o);
                        else if (iView instanceof ISecondView)
                            ((ISecondView) iView).handleDataSource((SourceResponse) o);
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
                ((IMainView) iView).handleDataUpload(model);
            }
        }).postingUploadFile(noteVal, startTimeVal, endTimeVal,
                startDateVal, endDateVal, employeeIdVal, file);
    }
}
