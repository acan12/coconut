package app.beelabs.com.coconut.model.api;

import java.util.Map;

import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.model.api.response.ProfileResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.coconut.model.api.response.SummaryResponse;
import app.beelabs.com.codebase.base.response.BaseResponse;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by arysuryawan on 8/18/17.
 */
public interface ApiService {


    @GET("auth/profile")
    Observable<ProfileResponseModel> callApiRXProfile(@HeaderMap Map<String, String> headers);


    @Headers({
            "Cache-Control: no-cache",
            "Cache-Control: no-store",
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("sources")
    Call<SourceResponse> callApiSources(@Query("language") String language);

    @Headers({
            "Cache-Control: no-cache",
            "Cache-Control: no-store",
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("sources")
    Observable<SourceResponse> callApiRXSources(@Query("language") String language);


    @Headers({
            "Cache-Control: no-cache",
            "Cache-Control: no-store",
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("articles")
    Call<ArticleResponse> callApiArticles(@Query("source") String sourceId, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

    @FormUrlEncoded
    @POST("merchant/rest/sfa/historySummary")
    Observable<SummaryResponse> callApiRXTestFintech(@Field("phoneNumber") String phone);


    @Multipart
    @POST("transactions/timesheet")
    Call<BaseResponse> callUploadTimesheetTransaction(
            @Part("start_time") RequestBody startTime,
            @Part("end_time") RequestBody endTime,
            @Part("note") RequestBody note,
            @Part("start_date") RequestBody startDate,
            @Part("end_date") RequestBody endDate,
            @Part("employee_id") RequestBody employeeId,
            @Part MultipartBody.Part file
    );
}
