package app.beelabs.com.coconut.model.db;

import android.content.Context;

import app.beelabs.com.codebase.base.BaseDatabase;

public class UserDB extends BaseDatabase {

    public void loadUser(Context context){
        setupRealm(context);
    }
}
