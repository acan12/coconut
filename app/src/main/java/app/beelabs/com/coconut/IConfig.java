package app.beelabs.com.coconut;

/**
 * Created by arysuryawan on 8/6/17.
 */

public interface IConfig {
    String API_BASE_URL = "https://fintech-dev.pactindo.com:8443/";
    String API_BASE_URL2 = "https://newsapi.org/v1/";
    String API_BASE_URL3 = "http://api.dev.hrsteps.com/v1/";
    String API_BASE_URLJPREMI = "http://developer.jagoanpremi.com/v1/";


    int KEY_CALLER_API_SOURCE = 1;
    String KEY_CALLER_API_ARTICLE = "call_article";
    String KEY_CALLER_API_ARTICLES = "articles";


    String BASE64_CAMERA_FRONT_KEY = "camera_front64";
    String BASE64_CAMERA_KEY = "camera_back64";
    String BASE64_CAMERA_SELFIE_KEY = "camera_selfie64";
    int REQUEST_CODE_CAMERA_RESULT = 1;

    int KEY_CALLER_API_SUMMARY = 10;
}