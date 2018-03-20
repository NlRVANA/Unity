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

package com.zwq65.unity.ui.activity

import android.content.Intent
import android.content.res.Configuration
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatDelegate
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.zwq65.unity.R
import com.zwq65.unity.ui._base.BaseDaggerActivity
import com.zwq65.unity.ui._base.BaseFragment
import com.zwq65.unity.ui.contract.MainContract
import com.zwq65.unity.ui.fragment.AlbumFragment
import com.zwq65.unity.ui.fragment.ArticleFragment
import com.zwq65.unity.ui.fragment.RestVideoFragment
import com.zwq65.unity.ui.fragment.TestFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.drawer_left.*
import java.util.concurrent.TimeUnit

/**
 * ================================================
 * 首页[BaseDaggerActivity]
 *
 * Created by NIRVANA on 2017/06/29.
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
class MainActivity : BaseDaggerActivity<MainContract.View, MainContract.Presenter<MainContract.View>>(), MainContract.View, View.OnClickListener {

    private var disposable: Disposable? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    private var firstClick: Long = 0

    override fun initBaseTooBar(): Boolean {
        return true
    }

    override fun dealIntent(intent: Intent) {

    }

    override fun initView() {
        //将drawerLayout、toolBar绑定
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout?.addDrawerListener(toggle)
        toggle.syncState()
        gotoFragment(AlbumFragment())
        addToolbarDoubleClick()
        iv_avatar.setOnClickListener(this)
        tv_account_name.setOnClickListener(this)
        ll_welfare.setOnClickListener(this)
        ll_news.setOnClickListener(this)
        ll_video.setOnClickListener(this)
        ll_setting.setOnClickListener(this)
        ll_out.setOnClickListener(this)
        fab.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun onBackPressed() {
        if (drawer_layout?.isDrawerOpen(GravityCompat.START)!!) {
            drawer_layout?.closeDrawer(GravityCompat.START)
        } else {
            //双击退出app
            if (System.currentTimeMillis() - firstClick > DELAY_TIME_FINISH) {
                firstClick = System.currentTimeMillis()
                showMessage(R.string.str_finish_if_press_again)
            } else {
                super.onBackPressed()
            }
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        drawer_layout?.closeDrawer(GravityCompat.START)
        when (v?.id) {
            R.id.iv_avatar, R.id.tv_account_name ->
                //个人中心
                openActivity(AccountActivity::class.java)
            R.id.ll_welfare ->
                //福利图集
                gotoFragment(AlbumFragment())
            R.id.ll_news ->
                //技术文章
                gotoFragment(ArticleFragment())
            R.id.ll_video ->
                //休息视频
                gotoFragment(RestVideoFragment())
            R.id.ll_setting ->
                gotoFragment(TestFragment())
            R.id.ll_out -> onBackPressed()
            R.id.fab -> setDayNightMode()
            else -> {
            }
        }
    }

    /**
     * 设置白天/黑夜主题
     * TODO: 2017/10/24 有bug,找机会修复(｡◕ˇ∀ˇ◕)
     */
    private fun setDayNightMode() {
        //获取应用当前的主题
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES ->
                //当前为夜间模式，切换为日间模式
                mPresenter.setNightMode(false)
            Configuration.UI_MODE_NIGHT_NO -> mPresenter.setNightMode(true)
            else -> {
            }
        }
        setDayNightMode(mPresenter.nightMode!!)
        window.setWindowAnimations(R.style.WindowAnimationFadeInOut)
        recreate()
    }

    /**
     * 设置app主题模式
     *
     * @param nightMode 是否夜间
     */
    private fun setDayNightMode(nightMode: Boolean) {
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    /**
     * 添加toolBar双击监听事件
     */
    private fun addToolbarDoubleClick() {
        //buffer: 定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值
        //判断500ms内，如果接受到2次的点击事件，则视为用户双击操作
        if (toolbar != null) {
            disposable = RxView.clicks(toolbar!!).buffer(500, TimeUnit.MILLISECONDS, 2).subscribe { objects ->
                val clickNum = 2
                if (objects.size == clickNum) {
                    val fragmentManager = supportFragmentManager
                    val fragmentList = fragmentManager.fragments
                    if (fragmentList != null && fragmentList.size > 0) {
                        fragmentList
                                .filter { it.isVisible }
                                .filterIsInstance<BaseFragment<*, *>>()
                                .forEach { it.onToolbarClick() }
                    }
                }
            }
        }
    }

    private fun gotoFragment(fragment: BaseFragment<*, *>) {
        switchFragment(R.id.fl_main, fragment, fragment.javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    companion object {

        /**
         * 退出app延时时间
         */
        const val DELAY_TIME_FINISH = 2000
    }
}