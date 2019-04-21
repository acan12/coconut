# coconut
_Coconut framework library for android_ 
- support MVP Architecture
- support RX implementation for Retrofit adapter
- support Retrofit2 for handle request response API
- support Dagger2 for DI

**Recomended Directory structure**
 - `Project directory`
 
     ```aidl
     < your namespace application >
        |
        |-- App.java
        |-- IConfig.java
        |
        |--- model
        |       |
        |       |-- api
        |       |    |
        |       |    |-- request
        |       |    |-- response
        |       |    |-- Api.java
        |       |    |-- ApiService.java
        |       | 
        |       |--- db
        |       |--- dao (interactor)
        |       
        |     
        |--- presenter
        |      |
        |      |-- <xxx>Presenter.java
        |  
        |--- ui
             |
             |-- activities
             |-- fragment
             |-- component
             |-- adapter
           
     
     
          
     ```


`Extention feature:`

**1. add snackbar if internet loss connection**
```
    <string name="snackbar_internet_fail_message">Internet loss connection</string>
    <string name="snackbar_reply_action_label">Reply</string>
```
**2. Using custom font**
```aidl
    App.java:
        setupDefaultFont("fonts/SF-Pro-Display-Black.otf");
    
    styles.xml:
        <style name="CustomFontType" parent="android:TextAppearance">
            <!-- Custom Attr-->
            <item name="fontPath">fonts/custom_font.ttf</item>
        </style>
```

**3. Custom timeout http request**
```aidl
    // TIMEOUT_SHORT_INSECOND (default)= 15 Seconds
    // TIMEOUT_LONG_INSECOND = 120 Seconds
    getInstance().setupApi(App.getAppComponent(), ApiService.class, false, app.beelabs.com.codebase.IConfig.TIMEOUT_LONG_INSECOND);

```

**4. Loading dialog layout**
```aidl
    LoadingDialogComponent dialog = new LoadingDialogComponent(this, R.style.CoconutDialogFullScreen);
    dialog.show();
    
    // custom style color background , update color hex in color.xml
    <color name="colorCoconut_background_dialog">#CCFFFFFF</color> 
    <color name="colorCoconut_text_dialog">#fff</color>
    <color name="colorCoconut_iconloading_dialog">#ababab</color>
    
```

**5. use custom loading for api call**
```aidl

    showApiCustomProgressDialog(App.getAppComponent(), BasePresenter.getInstance(this, new ResourcePresenter(this) {
        @Override
        public void call() {
            getSourceRX();
        }
    }), "Updating...");
```



## Installation guide :

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
		implementation 'com.github.acan12:coconut:v1.0.11'
		implementation 'com.google.dagger:dagger:2.4'        
		annotationProcessor "com.google.dagger:dagger-compiler:2.4"
		implementation 'uk.co.chrisjenx:calligraphy:2.3.0'
		implementation 'com.github.ybq:Android-SpinKit:1.2.0'
		...
        
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
            
            // optional setup custom font path,
            // make sure put font file under main/assets/fonts/
            setupDefaultFont("fonts/SF-Pro-Display-Black.otf");  
            
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
           android:name=".<AppClassName>"
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
        
            synchronized private static ApiService initApiDomain() {
                getInstance().setApiDomain(IConfig.API_BASE_URL);
                return (ApiService) getInstance().setupApi(App.getAppComponent(), ApiService.class);
                //return setupApi(App.getAppComponent(), ApiService.class, true); -- if set allow untrusted SSL calling
            }
        
            synchronized public static void doApiSources(Callback callback) {
                initApiDomain().callApiSources("en").enqueue((Callback<SourceResponse>) callback);
            }
    
    ```
 - `BaseActivity`
    ```aidl
       public class MainActivity extends BaseActivity {
           ...
       }
    
    ```
 - `BaseFragment` ,_if use fragment_
    ```aidl
    
       public class MainFragment extends BaseFragment {
           ...
       }
    ```
    
- `BaseDialog` 
    ```aidl
       public class MainDialog extends BaseDialog {
          ...  
        
          @Override
          protected void onCreate(Bundle savedInstanceState) {
              ...
          }
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
            
            private Database db;
            
            public void saveUser(BaseActivity ac){
                db = Database.initDatabase(context); -- setup database configuration
                db.saveToRealm()
            }
    ```
    
 - `BaseResponse`
    ```aidl
       public class ArticleResponse extends BaseResponse {
           ...
       }
    ```
    
**6. Implementation RxTimer**
```
    RxTimer.doTimer(long delay, boolean repeat, RxTimer callback)
```

**7. Implementation loading message within Rx**
```
    new ResourceDao(this).getSourceRXDAO()
                .subscribe(new RxObserver<ProfileResponseModel>(iv, "Update data...") {
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        iv.handleDataSource((SourceResponse) o);
                    }
                });
```

**8. Prevent from being Rooted, FakeGPS, shareApp**
    
**[mvp coconut 2]**
    * support multiple resource directories
    * full mvp implementation
    * integrate Retrofit with RXObserver
    
Version:
- `2.0.9` :
    * RX implementation for timer
    * support RXTimer for timer action base on RX
    * support preventation from fakeGPS, rooted, appshare
    
- `2.0.7` :
    * add new custom loading component
    * fix issue retrofit reference in race condition
     
- `2.0.5` :
    * Using mvp framework version 2
    * fix issue for Api management in base code
    * improve dialog custom style
    
- `1.0.14` :
    * fix base fragment has same behaviour with base activity when call api
    * implement option allow untrusted SSL call on HTTPRequest
     
- `1.0.11` :
    * implement font framework supported by calligraphy for custom font.
    * Show Snackbar for reply connection if got lost in the middle of process runtime.
    * support style for dialog full screen.
    * support style for custom font.
    
- `1.0.5` :added Response as object parameter on ResponseCallback
- `1.0.4` :fix bug for object modular between fragment and activity
- `1.0.0` :launch library on basic requirement


