package app.beelabs.com.codebase.base;

import android.content.Context;

import app.beelabs.com.codebase.R;
import app.beelabs.com.codebase.support.util.DeviceUtil;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class BaseDatabase {

        // --- realm db config ---
        protected static Realm realm;
        public static Realm getRealm() {
            return realm;
        }
        public static void setRealm(Realm realm) {
            BaseDatabase.realm = realm;
        }

        public static Realm setupRealm(Context context) {
            if (context == null) return null;
            Realm.init(context);
            RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .name(context.getResources().getString(R.string.database_package_name))
                    .encryptionKey(DeviceUtil.encryptedKey64())
                    .build();
            // Get a Realm instance for this thread
            realm = Realm.getInstance(realmConfig);
            return realm;
        }


        protected static RealmObject saveToRealm(RealmObject object) {
            realm.beginTransaction();
            RealmObject obj = realm.copyToRealm(object);
            realm.commitTransaction();
            return obj;
        }
        protected static RealmResults getCollectionRealm(Class clazz) {
            RealmResults objects = realm.where(clazz).findAll();
            return objects;
        }
        protected static RealmResults getCollectionByKeyRealm(String key, int value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }

        protected static RealmResults getCollectionByKeyRealm(String key, long value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }

        protected static RealmResults getCollectionByKeyRealm(String key, boolean value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }
        protected static RealmResults getCollectionByKeyRealm(String key, String value, Class clazz) {
            RealmResults items = realm.where(clazz)
                    .beginGroup()
                    .equalTo(key, value)
                    .endGroup()
                    .findAll();
            return items;
        }
        protected static void closeRealm() {
            if (realm != null)
                realm.close();
        }
        public static void deleteRealm(Class clazz) {
            try {
                realm.beginTransaction();
                realm.delete(clazz);
                realm.commitTransaction();
            } catch (Exception e) {
            }
        }

}
