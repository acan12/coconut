package app.beelabs.com.coconut.model.api;

import android.content.Context;

import java.io.File;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.codebase.base.BaseApi;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */
public class Api extends BaseApi {

    synchronized private static ApiService initApiDomain() {
        getInstance().setApiDomain(IConfig.API_BASE_URL);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }

    synchronized private static ApiService initApiDomain2() {
        getInstance().setApiDomain(IConfig.API_BASE_URL2);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }

    synchronized private static ApiService initApiDomain3() {
        getInstance().setApiDomain(IConfig.API_BASE_URL3);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND);
    }


    synchronized public static void doApiSources(Callback callback) {
        initApiDomain2().callApiSources("en").enqueue((Callback<SourceResponse>) callback);
    }

    synchronized public static void doApiArticles(Context context, Callback callback) {
        initApiDomain().callApiArticles("the-next-web", "latest", "6d362365d5e245faa1fe3253c83c45ac").enqueue((Callback<ArticleResponse>) callback);
    }

    synchronized public static void doTestFin(String phone, Callback callback) {
        initApiDomain().callApiTestFintech(phone).enqueue((Callback<SummaryResponse>) callback);
    }


    synchronized public static void doUploadTimesheetTrx(
            String noteVal,
            String startTimeVal,
            String endTimeVal,
            String startDateVal,
            String endDateVal,
            String employeeIdVal,
            File file) {

        RequestBody note = RequestBody.create(okhttp3.MultipartBody.FORM, noteVal);
        RequestBody startTime = RequestBody.create(okhttp3.MultipartBody.FORM, startTimeVal);
        RequestBody endTime = RequestBody.create(okhttp3.MultipartBody.FORM, endTimeVal);
        RequestBody startDate = RequestBody.create(okhttp3.MultipartBody.FORM, startDateVal);
        RequestBody endDate = RequestBody.create(okhttp3.MultipartBody.FORM, endDateVal);
        RequestBody employeeId = RequestBody.create(okhttp3.MultipartBody.FORM, employeeIdVal);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part fileUpload = MultipartBody.Part.createFormData("upload", file.getName(), requestFile);

        initApiDomain3().callUploadTimesheetTransaction(startTime, endTime, note, startDate, endDate, employeeId,
                fileUpload);

    }

}
