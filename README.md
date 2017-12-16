# coconut
Coconut framework library for android 
- support MVP Architecture
- support Retrofit2 for handle request response API
- support Dagger2 for DI

**How to install :**
1. Add the JitPack repository to your build file
```allprojects {
   		repositories {
   			...
   			maven { url 'https://jitpack.io' }
   		}
   	}
```

2. Add the dependency
```
dependencies {
		compile 'com.github.acan12:coconut:v1.0.4'
	}
```

3. Add Application project class extend BaseApp
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

4. Add Application class into `AndroidManifest.java`

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

5. Integrate code base with Coconut base
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
 - `BaseFragment`
    ```aidl
    
       public class MainFragment extends BaseFragment {
           ...
       }
    ```
    
 - `BaseApp`
    ```aidl
       public class App extends BaseApp {
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


https://jitpack.io/docs/

Version:
- `1.0.5` :added Response as object parameter on ResponseCallback
- `1.0.4` :fix bug for object modular between fragment and activity
- `1.0.0` :launch library on basic requirement


