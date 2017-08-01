package com.zwq65.unity;

import android.app.Application;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.zwq65.unity.di.component.ApplicationComponent;
import com.zwq65.unity.di.component.DaggerApplicationComponent;
import com.zwq65.unity.di.module.ApplicationModule;
import com.zwq65.unity.utils.ToastUtils;

/**
 * Created by zwq65 on 2017/06/28.
 * Unity
 */

public class UnityApp extends Application {

    private ApplicationComponent mApplicationComponent;
    private static UnityApp unityApp;

    public static UnityApp getInstance() {
        return unityApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        unityApp = this;
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
        Stetho.initializeWithDefaults(this);//初始化Stetho(可以在chrome中方便地查看app数据库等信息，release时注释掉就ok了)
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public static void showShortToast(String msg) {
        ToastUtils.makeText(msg, Toast.LENGTH_SHORT);
    }
}

