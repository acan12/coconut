package app.beelabs.com.codebase.base;

import android.content.Context;
import android.util.Log;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.base.response.BaseResponse;
import app.beelabs.com.codebase.support.util.CryptoUtil;
import app.beelabs.com.codebase.support.util.DeviceUtil;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by arysuryawan on 8/19/17.
 */

public class BaseDao implements IDao {

    private static BaseDao base;
    private int callbackKey;

    private IDao dao;
    private BasePresenter bp;
    private String key;

    public BaseDao() {
    }

    public BaseDao(IDao dao) {
        this.dao = dao;

    }

    private BaseDao(IDao dao, BasePresenter bp, int keyCallback) {
        this.dao = dao;
        this.bp = bp;
        this.callbackKey = keyCallback;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static BaseDao getInstance(IDao current, BasePresenter bp, int key) {
        if (base == null || base.getKey() == null || !base.getKey().equals(key))
            base = new BaseDao(current, bp, key);

        return base;
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onApiResponseCallback(BaseResponse br, int responseCode, Response response) {

    }

    @Override
    public void onApiFailureCallback(String message) {
        // --- default callback if not defined on child class --
//        try {
//            Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
//            Log.e("Message:", message);
//
//
//            if (((BaseActivity) iView).getContentView() != null)
//                showSnackbar(((BaseActivity) iView).getContentView(), getResources().getString(R.string.coconut_internet_fail_message), Snackbar.LENGTH_INDEFINITE).show();
//        } catch (Exception e) {
//            Log.e("", e.getMessage());
//        }

    }


    public void onResponseCallback(Response response, IDao dao, int responseCode) {
        bp.done();
        dao.onApiResponseCallback((BaseResponse) response.body(), responseCode, response);
    }


    public void onFailureCallback(Throwable t, IDao dao) {
        bp.fail(t.getMessage());
        dao.onApiFailureCallback(t.getMessage());
    }


    public Callback callback = new Callback() {

        @Override
        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
            if (dao == null) return;
            if (dao instanceof BaseDao)
                base.onResponseCallback(response, dao, callbackKey);
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            if (dao != null && dao instanceof BaseDao)
                base.onFailureCallback(t, dao);
        }
    };


    // database
    public static class Database {

        // --- realm db config ---
        private static Realm realm;
        private static Database db;

        public Database(Realm realm) {
            this.realm = realm;
        }

        public static Database initDatabase(Context context) {
            if(realm == null) db = buildRealm(context);
            return db;
        }

        private static Database buildRealm(Context context) {
            if (context == null) return null;
            Realm.init(context);
            RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .name(context.getResources().getString(R.string.database_package_name))
                    .encryptionKey(CryptoUtil.encryptedKey64())
                    .build();
            // Get a Realm instance for this thread
            realm = Realm.getInstance(realmConfig);

            return new Database(realm);
        }


        public RealmObject saveToRealm(RealmObject object) {
            realm.beginTransaction();
            RealmObject obj = realm.copyToRealm(object);
            realm.commitTransaction();
            return obj;
        }

        public void doTransactionWithRealm(TransactionCallback callback){
            realm.beginTransaction();
            callback.call();
            realm.commitTransaction();
        }

        public RealmResults getCollectionRealm(Class clazz) {
            RealmResults objects = realm.where(clazz).findAll();
            return objects;
        }
        public RealmResults getCollectionByKeyRealm(String key, int value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }

        public RealmResults getCollectionByKeyRealm(String key, long value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }

        public RealmResults getCollectionByKeyRealm(String key, boolean value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }
        public RealmResults getCollectionByKeyRealm(String key, String value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }
        public void closeRealm() {
            if (realm != null)
                realm.close();
        }
        public void deleteRealm(Class clazz) {
            try {
                realm.beginTransaction();
                realm.delete(clazz);
                realm.commitTransaction();
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
        }

        public static class TransactionCallback {

            public void call(){

            }
        }
    }

}
