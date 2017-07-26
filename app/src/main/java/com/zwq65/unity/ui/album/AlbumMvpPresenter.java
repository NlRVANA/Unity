package com.zwq65.unity.ui.album;

import com.zwq65.unity.ui.base.MvpPresenter;
import com.zwq65.unity.ui.base.MvpView;

/**
 * Created by zwq65 on 2017/07/19.
 */

public interface AlbumMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void initImages();

    /**
     * @param isRefresh 是否为刷新操作
     */
    void loadImages(Boolean isRefresh);//加载图片资源


}
