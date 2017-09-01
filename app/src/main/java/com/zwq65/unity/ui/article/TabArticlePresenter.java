package com.zwq65.unity.ui.article;

import com.zwq65.unity.data.DataManager;
import com.zwq65.unity.data.network.retrofit.callback.ApiErrorCallBack;
import com.zwq65.unity.data.network.retrofit.callback.ApiSubscriberCallBack;
import com.zwq65.unity.data.network.retrofit.response.enity.ArticleWithImage;
import com.zwq65.unity.ui._base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zwq65 on 2017/08/31
 */

public class TabArticlePresenter<V extends TabArticleContract.ITabArticleView> extends BasePresenter<V>
        implements TabArticleContract.ITabArticlePresenter<V> {
    private TabArticleFragment.Type type;
    private int page;
    private boolean isLoading;
    private boolean isRefresh;

    @Inject
    public TabArticlePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void setType(TabArticleFragment.Type type) {
        this.type = type;
    }

    @Override
    public void init() {
        page = 1;
        isLoading = false;
        loadDatas(true);
    }

    @Override
    public void loadDatas(Boolean isRefresh) {
        if (isLoading) return;
        isLoading = true;
        this.isRefresh = isRefresh;
        switch (type) {
            case Android:
                getCompositeDisposable().add(getDataManager().getAndroidArticles(page, getApiSubscriberCallBack(),
                        getApiErrorCallBack()));
                break;
            case Ios:
                getCompositeDisposable().add(getDataManager().getIosArticles(page, getApiSubscriberCallBack(),
                        getApiErrorCallBack()));
                break;
            case Qianduan:
                getCompositeDisposable().add(getDataManager().getQianduanArticles(page, getApiSubscriberCallBack(),
                        getApiErrorCallBack()));
                break;
        }
    }

    private ApiSubscriberCallBack<List<ArticleWithImage>> getApiSubscriberCallBack() {
        return new ApiSubscriberCallBack<List<ArticleWithImage>>() {
            @Override
            public void onSuccess(List<ArticleWithImage> data) {
                if (data != null) {
                    page++;
                    if (isRefresh) {
                        getMvpView().refreshData(data);
                    } else {
                        getMvpView().showData(data);
                    }
                } else {
                    getMvpView().noMoreData();
                }
                isLoading = false;
            }
        };
    }

    private ApiErrorCallBack<Throwable> getApiErrorCallBack() {
        return new ApiErrorCallBack<Throwable>() {
            @Override
            public void onFailure(Throwable t) {
                isLoading = false;
            }
        };
    }

}
