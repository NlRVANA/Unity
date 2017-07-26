package com.zwq65.unity.ui.album;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yalantis.phoenix.PullToRefreshView;
import com.zwq65.unity.R;
import com.zwq65.unity.data.network.retrofit.response.WelfareResponse;
import com.zwq65.unity.ui.base.BaseActivity;
import com.zwq65.unity.ui.custom.recycleview.MyItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumActivity extends BaseActivity implements AlbumMvpView {

    @BindView(R.id.rv_albums)
    RecyclerView rvAlbums;
    @BindView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefresh;

    @Inject
    AlbumMvpPresenter<AlbumMvpView> mPresenter;
    AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    public void initView() {
        rvAlbums.setLayoutManager(new LinearLayoutManager(this));//垂直方向两排
        rvAlbums.setItemAnimator(new DefaultItemAnimator());
        rvAlbums.addItemDecoration(new MyItemDecoration());
        rvAlbums.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == totalItemCount - 1) {
                    //加载更多
                    mPresenter.loadImages(false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        pullToRefresh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        adapter = new AlbumAdapter(this);
        rvAlbums.setAdapter(adapter);
    }

    public void initData() {
        mPresenter.initImages();
    }

    @Override
    public void refreshImages(List<WelfareResponse.Image> imageList) {
        pullToRefresh.setRefreshing(false);//取消下拉加载
        adapter.initImageList();
        adapter.addImageList(imageList);//加载数据
    }

    @Override
    public void loadImages(List<WelfareResponse.Image> imageList) {
        adapter.addImageList(imageList);//加载数据
    }

    @Override
    public void loadError(Throwable t) {
        pullToRefresh.setRefreshing(false);//取消下拉加载
    }

    @Override
    public void noMoreData() {
        onError("没有更多数据了！");
    }
}
