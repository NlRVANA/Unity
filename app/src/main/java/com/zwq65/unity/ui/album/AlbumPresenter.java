package com.zwq65.unity.ui.album;

import com.zwq65.unity.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zwq65 on 2017/07/19.
 */

public class AlbumPresenter<V extends AlbumMvpView> extends BasePresenter<V> implements AlbumMvpPresenter<V> {
    @Inject
    public AlbumPresenter(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }
}