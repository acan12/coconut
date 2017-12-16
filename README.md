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


https://jitpack.io/docs/

Version:
- `1.0.4` :fix bug for object modular between fragment and activity
- `1.0.0` :launch library on basic requirement


