package app.beelabs.com.codebase.base;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import app.beelabs.com.codebase.base.response.BaseResponse;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;

class ApiHelper {
    public static OkHttpClient.Builder allowUntrustedSSL(OkHttpClient.Builder httpClient) {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            httpClient.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return httpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BaseResponse parsingErrorResponse(Throwable e)  {
        try {
            HttpException error  = (HttpException) e;
            String message = "";
            if(error.message() != null) message = error.message();
            String jsonResponse = "{\"meta\":{\"status\":false,\"code\":${error.code()},\"message\":\"${message}\"},\"data\":null}";
            jsonResponse = error.response().errorBody().string();
            Object objectMapper = new ObjectMapper().readValue(jsonResponse, Object.class);

            return (BaseResponse) objectMapper;
        } catch (Exception exception) {
            return null;
        }
    }

}
