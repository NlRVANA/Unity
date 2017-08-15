package com.zwq65.unity.ui.login;

import com.zwq65.unity.data.DataManager;
import com.zwq65.unity.ui._base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zwq65 on 2017/06/29.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void login(String account, String password) {
//        if (TextUtils.isEmpty(account)) {
//            getMvpView().onError("请输入账号");
//            return;
//        }
//        if (TextUtils.isEmpty(password)) {
//            getMvpView().onError("请输入密码");
//            return;
//        }
        getMvpView().openMainActivity();
    }

    @Override
    public void register() {

    }

    @Override
    public void forgotPsd() {

    }


    @Override
    public void onDetach() {

    }
}
