[![](https://jitpack.io/v/acan12/coconut.svg)](https://jitpack.io/#acan12/coconut)

# coconut
_Coconut framework library for android_ 
- support MVP Architecture
- support RX implementation for Retrofit adapter
- support Retrofit2 for handle request response API
- support Dagger2 for DI

**Recomended Directory structure**
 - `Project directory`


     > assets directory path: `src/main/assets/fonts/...`

     ```aidl
        
     < YOUR_NAMESPACE_APPLICATION >
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


## Installation guide :

**1. Add the JitPack repository to your build file**
```allprojects
{
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
		
	    // recommended optional
	    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
        implementation 'io.reactivex.rxjava2:rxjava:2.x.x'
        implementation 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'
        
	}
```

**3. Add Application project class extend BaseApp**
```java
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
```java
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

**5. Integrate Base Code with Coconut Framework**
 - `BaseApi`
 
    ```java
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
    ```java
       public class MainActivity extends BaseActivity {
           ...
       }
    
    ```
 - `BaseFragment` ,_if use fragment_
    ```java
    
       public class MainFragment extends BaseFragment {
           ...
       }
    ```

- `BasePresenter`
  ```java
    
       public class ResourcePresenter extends BasePresenter {
           ...
       }
    ```
    
- `BaseDialog` 
    ```java
       public class MainDialog extends BaseDialog {
          ...  
        
          @Override
          protected void onCreate(Bundle savedInstanceState) {
              ...
          }
       }
          
                  
    ```
    
 - `BaseDao / Interactor`
    ```java
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
    ```java
       public class ArticleResponse extends BaseResponse {
           ...
       }
    ```
    

**6. Implementation loading message within Rx**

- `(Activity / Fragment)`
```aidl
    -> ((ResourcePresenter) BasePresenter.getInstance(this, ResourcePresenter.class)).getSourceRX("Ambil Data");
```
    
- `(Interface UI class)`
```aidl
    -> create self interface extends IView as parent of interface in framework 
```
    
- `(Presenter)`
```aidl
    -> @Override
        public void getSourceRX(String messageLoading) {
            new ResourceDao(this).getSourceRXDAO()
                    .subscribe(new RxObserver<ProfileResponseModel>(iv, messageLoading) {
                        @Override
                        public void onNext(Object o) {
                            super.onNext(o);
                            [Interface UI class].handle[your function name]((SourceResponse) o);
                        }
                    });
        }
```
        
- `(Interface DAO/ Interactor)`
```aidl
    -> create self interface extends IDao as parent of interface in framework
    
    [sample code] ->
    
        private [custom presenter name]Presenter.OnPresenterResponseCallback onPresenterResponseCallback;
        private IResourceDao rdao;
    
        // definition usecase
        public interface I[custom presenter name]Dao extends IDaoPresenter {
    
            ...   
            void getSourceRX(String messageLoading);
    
            ...
    
    
        }
    
        public [custom presenter name]Dao(I[custom presenter name] rdao) {
            this.rdao = rdao;
        }
    
        public [custom presenter name]Dao(I[custom presenter name]Dao rdao, ResourcePresenter.OnPresenterResponseCallback onPresenterResponseCallback) {
            this.rdao = rdao;
            this.onPresenterResponseCallback = onPresenterResponseCallback;
        }
        ...
    
```
    
Change Log Version:
- `2.1.0` :
    * support fragment back stack history
    * handle for memory leaks when activity changes
    * remove setup RootView
    * remove setup CurrentActivity
    * custom interceptor for Retrofit
    
- `2.0.10` :
    * Optimizing to avoid memory leaks 
    * Optimizing RxObserver when use to call Api
    * Support multiple resource directories allocation
    * Full mvp implementation
    
    
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


Git command force pull branch command:
git checkout seotweaks
git merge -s ours master
git checkout master
git merge seotweaks
