# coconut
_Coconut framework library for android_ 
- support MVP Architecture
- support Retrofit2 for handle request response API
- support Dagger2 for DI

_Extention fitur:_

**1. add snackbar if internet loss connection**
```
    <string name="snackbar_internet_fail_message">Internet loss connection</string>
        <string name="snackbar_reply_action_label">Reply</string>
```
**2. Using custom font**
```aidl
    setupDefaultFont("fonts/SF-Pro-Display-Black.otf"); ...App.java
    
    <style name="CustomFontType" parent="android:TextAppearance">
        <!-- Custom Attr-->
        <item name="fontPath">fonts/custom_font.ttf</item>
    </style>
```




**Installation guide :**

**1. Add the JitPack repository to your build file**
```allprojects {
   		repositories {
   			...
   			maven { url 'https://jitpack.io' }
   		}
   	}
```

**2. Add the dependency**
```
dependencies {
		implementation 'com.github.acan12:coconut:v1.0.4'
	}
```

**3. Add Application project class extend BaseApp**
```
    public class App extends BaseApp {
        private static Context context;
    
        @Override
        public void onCreate() {
            super.onCreate();
            context = getApplicationContext();
            setupBuilder(DaggerAppComponent.builder(), this);
        }
    
        public static Context getContext(){
            return context;
        }
    
        public static AppComponent getAppComponent() {
            if(context == null) return null;
            return getComponent();
        }
    
        ...
    }

```

**4. Add Application class into `AndroidManifest.java`**

```
   ...
   <application
           android:name=".App"
           android:allowBackup="true"
           android:icon="@mipmap/ic_launcher"
           android:label="@string/app_name"
           android:supportsRtl="true"
           android:theme="@style/AppTheme">
  ...         

```

**5. Integrate code with Coconut Framework**
 - `BaseApi`
 
    ```aidl
        public class Api extends BaseApi {
        
            synchronized private static ApiService initApiDomain(Context context) {
                setApiDomain(IConfig.API_BASE_URL);
                return (ApiService) setupApi(App.getAppComponent(), ApiService.class);
            }
        
            synchronized public static void doApiSources(Context context, Callback callback) {
                initApiDomain(context).callApiSources("en").enqueue((Callback<SourceResponse>) callback);
            }
    
    ```
 - `BaseActivity`
    ```aidl
       public class MainActivity extends BaseActivity {
           ...
       }
    
    ```
 - `BaseFragment` ,_if you use fragment_
    ```aidl
    
       public class MainFragment extends BaseFragment {
           ...
       }
    ```
    
 - `BaseDao`
    ```
        public class ResourceDao extends BaseDao {
        
            public ResourceDao(BaseActivity ac) {
                super(ac);
            }
            
            public void getSourcesDAO(BaseActivity ac, Callback callback) {
                 Api.doApiSources(ac, callback);
            }
            ...
    ```
    
 - `BaseResponse`
    ```aidl
       public class ArticleResponse extends BaseResponse {
           ...
       }
    ```

https://jitpack.io/

Version:
- `1.0.11` :
    * implement font framework supported by calligraphy for custom font.
    * Show Snackbar for reply connection if got lost in the middle of process runtime.
    * support style for dialog full screen.
    * support style for custom font.
    
- `1.0.5` :added Response as object parameter on ResponseCallback
- `1.0.4` :fix bug for object modular between fragment and activity
- `1.0.0` :launch library on basic requirement


