package net.angrycode.wehub;

import android.app.Application;

import net.angrycode.wehub.bean.DaoMaster;
import net.angrycode.wehub.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lancelot on 2016/11/13.
 */

public class WeCodeApp extends Application {

    private static WeCodeApp sInstance;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "repos-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static WeCodeApp get() {
        return sInstance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
