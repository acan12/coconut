package app.beelabs.com.codebase.base.request;

import android.support.annotation.Nullable;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.beelabs.com.codebase.support.util.CryptoUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class RequestInterceptor implements Interceptor {
    private static String GENERAL_URL = "https://mle.pede.id/mobile-general";
    private static String USER_AGENT_VALUE = "Android";
    private GeneralBodyRequest mBody = new GeneralBodyRequest();
    private GeneralBodyRequest.Header mHeaders = mBody.new Header();
    private ObjectMapper mapper = new ObjectMapper();
    private MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
    private String mRsaPublicKey;

    public RequestInterceptor(@Nullable String rsaPublicKey) {
        mRsaPublicKey = rsaPublicKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (mRsaPublicKey == null) return chain.proceed(chain.request());

        RequestBody newBody;
        String encryptedStringBody = null;

        Request original = chain.request();
        Request.Builder builder = original.newBuilder();

        // for request
        builder.header("Content-Type", "application/json");
        builder.header("partnerCode", "PEDE");


        // for header inside body
        mHeaders.setContentType("application/json");
        mHeaders.setPartnerCode("PEDE");

        // check non null authorization
        List<String> authorization = original.headers("Authorization");
        if (authorization.size() > 0) {
            builder.header("Authorization", original.header("Authorization"));
            mHeaders.setAuthorization(original.header("Authorization"));
        }

        // check non null user agent
        List<String> userAgent = original.headers("User-Agent");
        if (userAgent.size() > 0) {
            builder.header("User-Agent", original.header("User-Agent"));
            mHeaders.setUserAgent(original.header("User-Agent"));
        } else {
            builder.header("User-Agent", USER_AGENT_VALUE);
            mHeaders.setUserAgent(USER_AGENT_VALUE);
        }

        mBody.setUrl(original.url().toString());
        mBody.setMethod(original.method());
        mBody.setHeaders(mHeaders);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        switch (original.method()) {
            case "POST":
                RequestBody oldBody = original.body();
                Buffer buffer = new Buffer();
                oldBody.writeTo(buffer);
                String jsonString = toJson(buffer.readUtf8());
                Object object = mapper.readValue(jsonString, Object.class);
                mBody.setData(object);
                break;
            case "GET":
                mBody.setData(new Object());
                break;

            default:
                break;
        }

        try {
            encryptedStringBody = generateEncryptedParam(
                    mapper.writeValueAsString(mBody), mRsaPublicKey).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        newBody = RequestBody.create(mediaType, encryptedStringBody);
        Request request = builder.method(original.method(), original.body()).build();

        return chain.proceed(request.newBuilder().url(GENERAL_URL).post(newBody).build());
    }

    private String toJson(String params) {
        try {
            new JSONObject(params);
            return params;
        } catch (JSONException e) {
            JSONObject jsonObject = new JSONObject();
            String[] maps = params.split("&");
            for (String map : maps) {
                String[] kv = map.split("=");
                try {
                    jsonObject.put(kv[0], kv.length == 2 ? kv[1] : "");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            return jsonObject.toString();
        }
    }


    private JSONObject generateEncryptedParam(String jsonBody, String publicKeyRSA) {
        int maxChar = 50;
        ArrayList<byte[]> bytePartials = new ArrayList<>();
        JSONObject jsonParam = new JSONObject();
        try {
            byte[] bytesJSONBody = jsonBody.getBytes("UTF-8");
            Log.d("bytesJSONBody length: ", "" + bytesJSONBody.length);
            if (bytesJSONBody.length > maxChar) {
                int counter = 0;
                do {
                    if (bytesJSONBody.length - counter > maxChar) {
                        bytePartials.add(Arrays.copyOfRange(bytesJSONBody, counter, counter + maxChar));
                    } else {
                        bytePartials.add(Arrays.copyOfRange(bytesJSONBody, counter, bytesJSONBody.length));
                    }
                    counter = counter + maxChar;
                } while (counter < bytesJSONBody.length);
            } else {
                bytePartials.add(bytesJSONBody);
            }

            JSONArray encArray = new JSONArray();
            for (int i = 0; i < bytePartials.size(); i++) {
                encArray.put(CryptoUtil.encryptRSA(bytePartials.get(i), publicKeyRSA));
            }

        } catch (Exception e) {
        }

        return jsonParam;
    }

}
