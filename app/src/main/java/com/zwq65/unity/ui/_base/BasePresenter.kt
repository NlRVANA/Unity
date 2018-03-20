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
package com.zwq65.unity.ui._base

import com.zwq65.unity.data.DataManager

import java.lang.ref.WeakReference

import javax.inject.Inject

/**
 * ================================================
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 * <p>
 * Created by NIRVANA on 2017/09/27
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
open class BasePresenter<V : BaseContract.View> @Inject
constructor(val dataManager: DataManager) : BaseContract.Presenter<V> {
    val TAG = javaClass.simpleName
    /**
     * MvpView接口类型的弱引用
     */
    private var mViewRef: WeakReference<V>? = null

    override val isViewAttached: Boolean
        get() = mViewRef?.get() != null

    val mvpView: V?
        get() = mViewRef?.get()

    override fun onAttach(mvpView: V) {
        mViewRef = WeakReference(mvpView)
    }

    override fun onDetach() {
        mViewRef?.clear()
        mViewRef = null
    }

}