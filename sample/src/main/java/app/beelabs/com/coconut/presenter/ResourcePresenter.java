package app.beelabs.com.coconut.presenter;

import android.widget.Toast;

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

    public ResourcePresenter(IView iv) {
        this.iView = iv;
    }

    @Override
    public void getSourceRX(String messageLoading, int dialogType) {
        new ResourceDao(this).getSourceRXDAO()
                .subscribe(new RxObserver<ProfileResponseModel>(iView, messageLoading) {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        if (iView instanceof IMainView)
                            ((IMainView) iView).handleSuccess((SourceResponse) o);
                        else if (iView instanceof ISecondView)
                            ((ISecondView) iView).handleDataSource((SourceResponse) o);
                    }
                }.setDialogType(dialogType));
    }

    @Override
    public void uploadFile(String noteVal, String startTimeVal, String endTimeVal,
                           String startDateVal, String endDateVal,
                           String employeeIdVal, File file) {

        new ResourceDao(this, new OnPresenterResponseCallback() {
            @Override
            public void call(BaseResponse br) {
                BaseResponse model = br;
                ((IMainView) iView).handleSuccess(model);
            }
        }).postingUploadFile(noteVal, startTimeVal, endTimeVal,
                startDateVal, endDateVal, employeeIdVal, file);
    }
}
