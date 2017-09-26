package com.zwq65.unity.ui.account.tabs.local;

import com.zwq65.unity.ui._base.MvpPresenter;
import com.zwq65.unity.ui._base.MvpView;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * ================================================
 * <p>
 * Created by NIRVANA on 2017/09/26
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
public interface TabLocalContract {
    interface View extends MvpView {

    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        Observable<List<File>> getLocalPictures();
    }
}