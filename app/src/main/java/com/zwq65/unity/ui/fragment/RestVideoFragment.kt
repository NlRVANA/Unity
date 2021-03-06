/*
 *    Copyright [2017] [NIRVANA PRIVATE LIMITED]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zwq65.unity.ui.fragment

import android.os.Bundle
import com.zwq65.unity.R
import com.zwq65.unity.data.network.retrofit.response.enity.Video
import com.zwq65.unity.ui._base.BaseRefreshFragment
import com.zwq65.unity.ui._base.adapter.BaseRecyclerViewAdapter
import com.zwq65.unity.ui.activity.WatchActivity
import com.zwq65.unity.ui.adapter.RestVideoAdapter
import com.zwq65.unity.ui.contract.RestVideoContract
import javax.inject.Inject

/**
 * ================================================
 * <p>
 * Created by NIRVANA on 2017/08/15.
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
class RestVideoFragment : BaseRefreshFragment<Video, RestVideoContract.View<Video>, RestVideoContract.Presenter<RestVideoContract.View<Video>>>(), RestVideoContract.View<Video> {

    @Inject
    lateinit var mAdapter: RestVideoAdapter<Video>

    override val layoutId: Int
        get() = R.layout.fragment_rest_video

    /**
     * 获取RecycleView的spanCount
     *
     * @return If orientation is vertical, spanCount is number of columns. If orientation is horizontal, spanCount is number of rows.
     */
    override val spanCount: Int
        get() = 1

    override fun initView() {
        super.initView()
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Video> {
            override fun onClick(t: Video, position: Int) {
                gotoWatchActivity(t)
            }

        })
        mRecyclerView.adapter = mAdapter
    }

    override fun initData(saveInstanceState: Bundle?) {
        super.initData(saveInstanceState)
        initData()
    }

    override fun requestDataRefresh() {
        initData()
    }

    override fun requestDataLoad() {
        mPresenter.loadVideos(false)

    }

    fun initData() {
        mPresenter.init()
    }

    private fun gotoWatchActivity(video: Video) {
        val bundle = Bundle()
        bundle.putParcelable(WatchActivity.VIDEO, video)
        mActivity?.openActivity(WatchActivity::class.java, bundle)
    }

    override fun refreshData(list: List<Video>) {
        super.refreshData(list)
        mAdapter.clearItems()
        mAdapter.addItems(list)
    }

    override fun loadData(list: List<Video>) {
        super.loadData(list)
        mAdapter.addItems(list)
    }
}
