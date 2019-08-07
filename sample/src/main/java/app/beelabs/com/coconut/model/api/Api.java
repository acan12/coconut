package app.beelabs.com.coconut.model.api;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.beelabs.com.coconut.App;
import app.beelabs.com.coconut.BuildConfig;
import app.beelabs.com.coconut.IConfig;
import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.codebase.base.BaseApi;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;


/**
 * Created by arysuryawan on 8/18/17.
 */
public class Api extends BaseApi {

    private static Map<String, String> initHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("Token", "eyJhbGciOiJIUzUxMiJ9.eyJkYXRhIjoxLCJlbWFpbCI6InJhbmlhQGNsYXBwaW5nYXBlLmNvbSIsInBsYXRmb3JtIjoid2Vic2l0ZSIsImtleSI6IjdYdk0xYmJuc3I3V0VjbU9ubFNjTnpvcElnMm5MQStjbld5SDc0Z1oiLCJ0aW1lc3RhbXAiOiIyMDE4LTExLTA2IDExOjI0OjQwICswNzAwIn0.wSPAcZJV8VBUSG8DAp_laovF7dFDhLxVJGQZmmDs3PsEz6SBn7FE2qF7k1UoY5Qq30wqjTDZAho1a55Yy2Fctg");
        map.put("Platform", "website");
        map.put("Cache-Control", "no-store");
        map.put("Content-Type", "application/json");

        return map;
    }


    synchronized private static ApiService initApiDomain() {
        return (ApiService) getInstance()
                .setupApiDomain(IConfig.API_BASE_URL, App.getAppComponent(),
                        ApiService.class,
                        true,
                        app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND, BuildConfig.IS_DEBUG, true);

    }

    synchronized private static ApiService initApiDomain2() {
        getInstance().setApiDomain(IConfig.API_BASE_URL2);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND, BuildConfig.IS_DEBUG);
    }

    synchronized private static ApiService initApiDomain3() {
        getInstance().setApiDomain(IConfig.API_BASE_URL3);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND, BuildConfig.IS_DEBUG);
    }

    synchronized private static ApiService initApiDomainJPremi() {
        getInstance().setApiDomain(IConfig.API_BASE_URLJPREMI);
        return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class, true, app.beelabs.com.codebase.IConfig.TIMEOUT_SHORT_INSECOND, BuildConfig.IS_DEBUG);
    }


    synchronized public static Observable<ProfileResponseModel> doApiProfile() {
        return initApiDomainJPremi().callApiRXProfile(initHeader());
    }


    synchronized public static void doApiSources(Callback callback) {
        initApiDomain2().callApiSources("en").enqueue((Callback<SourceResponse>) callback);
    }

    synchronized public static Observable<SourceResponse> doApiRXSources() {
        return initApiDomain2().callApiRXSources("en");
    }


    synchronized public static void doApiArticles(Context context, Callback callback) {
        initApiDomain().callApiArticles("the-next-web", "latest",
                "6d362365d5e245faa1fe3253c83c45ac").enqueue((Callback<ArticleResponse>) callback);
    }

    synchronized public static Observable<SummaryResponse> doRXTestFin(String phone) {
        return initApiDomain().callApiRXTestFintech(phone);
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
