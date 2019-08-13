package app.beelabs.com.coconut;

/**
 * Created by arysuryawan on 8/6/17.
 */

public interface IConfig {
    String API_BASE_URL = "https://fintech-dev.pactindo.com:8443/";//"https://newsapi.org/v1/";
    String DEFAULT_LOADING = "Loading";

    int KEY_CALLER_API_SOURCE = 1;
    String KEY_CALLER_API_ARTICLE = "call_article";
    String KEY_CALLER_API_ARTICLES = "articles";


    String BASE64_CAMERA_FRONT_KEY = "camera_front64";
    String BASE64_CAMERA_KEY = "camera_back64";
    String BASE64_CAMERA_SELFIE_KEY = "camera_selfie64";
    int REQUEST_CODE_CAMERA_RESULT = 1;

    String PUBLIC_KEY_RSA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBzN1iRa2lgEV1L7/7Z25rG7HD\n" +
            "lOYCai59x1bC+Knm/UHDKYR9I4MzHDZanxPLASF8RGqBlKSXx2LMkUwuTjVIUpS6\n" +
            "w3ssaDNfnEBPfKEjfjde1Wc4f/FIGQX0KMl334BDBla1vWQgmQ4abCN5vOPXYM3k\n" +
            "D9Q/5ks3vwaCC4ceRwIDAQAB";
}