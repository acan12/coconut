package app.beelabs.com.coconut.model.dao;

import java.io.File;

import app.beelabs.com.coconut.model.api.Api;
import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.coconut.presenter.ResourcePresenter;
import app.beelabs.com.codebase.base.BaseDao;
import app.beelabs.com.codebase.base.IDaoPresenter;
import app.beelabs.com.codebase.base.response.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static app.beelabs.com.coconut.IConfig.KEY_CALLER_API_SOURCE;
import static app.beelabs.com.coconut.IConfig.KEY_CALLER_API_SUMMARY;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class ResourceDao extends BaseDao {

    private ResourcePresenter.OnPresenterResponseCallback onPresenterResponseCallback;
    private IResourceDao rdao;

    // definition usecase
    public interface IResourceDao extends IDaoPresenter {

        void postPhoneNumber(String phone);

        void getProfileRX();

        void getSource();

        void getSourceRX(String messageLoading);

        void uploadFile(String noteVal,
                        String startTimeVal,
                        String endTimeVal,
                        String startDateVal,
                        String endDateVal,
                        String employeeIdVal,
                        File file);


    }

    public ResourceDao(IResourceDao rdao) {
        this.rdao = rdao;
    }

    public ResourceDao(IResourceDao rdao, ResourcePresenter.OnPresenterResponseCallback onPresenterResponseCallback) {
        this.rdao = rdao;
        this.onPresenterResponseCallback = onPresenterResponseCallback;
    }


    public Observable<ProfileResponseModel> getProfileRxDAO() {
        return Api.doApiProfile().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public void getSourceDAO() {
        Api.doApiSources(BaseDao.getInstance(this, rdao.getPresenter(), KEY_CALLER_API_SOURCE).callback);
    }

    public Observable<SourceResponse> getSourceRXDAO() {
        return Api.doApiRXSources().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


//    public void getArticleDAO(Context context, Callback callback) {
//        Database db = Database.initDatabase(context);
//        db.saveToRealm()
//    }

    public Observable<SummaryResponse> postPhoneNumber(String phone) {
        return Api.doRXTestFin(phone).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void postingUploadFile(String noteVal,
                                  String startTimeVal,
                                  String endTimeVal,
                                  String startDateVal,
                                  String endDateVal,
                                  String employeeIdVal,
                                  File file) {
        Api.doUploadTimesheetTrx(noteVal, startTimeVal, endTimeVal, startDateVal, endDateVal, employeeIdVal, file);
    }


    @Override
    public void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {
        if (response.isSuccessful()) {
            if (responseCode == KEY_CALLER_API_SOURCE) {
                SourceResponse model = (SourceResponse) br;
                if (model.getStatus().equals("ok")) {
                    onPresenterResponseCallback.call(model);

                }
            } else if (responseCode == KEY_CALLER_API_SUMMARY) {
                SummaryResponse model = (SummaryResponse) br;
                if (model.getDescriptionCode().equals("Success")) {
                    onPresenterResponseCallback.call(model);
                }
            }
        }
    }

}
