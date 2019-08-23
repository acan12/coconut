package app.beelabs.com.coconut.model.api;

import app.beelabs.com.coconut.model.api.request.Login2RequestModel;
import app.beelabs.com.coconut.model.api.request.LoginRequestModel;
import app.beelabs.com.coconut.model.api.request.PhoneRequestModel;
import app.beelabs.com.coconut.model.api.response.ArticleResponse;
import app.beelabs.com.coconut.model.api.response.LoginResponseModel;
import app.beelabs.com.coconut.model.api.response.SourceResponse;
import app.beelabs.com.codebase.base.response.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by arysuryawan on 8/18/17.
 */
public interface ApiService {

    //CMS Check App Version
    @Headers({
            "Content-Type:" + "application/json"
    })
    @GET("v1/check/version")
    Call<BaseResponse> checkAppVersion();



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
            "Content-Type: application/json"})
    @GET("articles")
    Call<ArticleResponse> callApiArticles(@Query("source") String sourceId, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);

    @FormUrlEncoded
    @POST("merchant/rest/sfa/historySummary")
    Call<BaseResponse> callApiTestFintech( @Field("phoneNumber") String phone);

    @POST("merchant/rest/sfa/historySummary")
    Call<BaseResponse> callApiTestFintech2( @Body PhoneRequestModel model);
    
    @POST("sfa/auth/login")
    Call<LoginResponseModel> callApiLogin(@Body Login2RequestModel model);
}
