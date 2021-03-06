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

package com.zwq65.unity.utils

import android.widget.Toast

import com.zwq65.unity.App

/**
 * ================================================
 * 防止多次调用弹出toast,统一以[ToastUtils]显示Toast
 *
 * Created by NIRVANA on 2017/09/29
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
object ToastUtils {
    private var toast: Toast? = null

    fun makeText(message: String, duration: Int) {
        if (toast == null) {
            toast = Toast.makeText(App.instance, message, duration)
        } else {
            toast!!.setText(message)
            toast!!.duration = duration
        }
        toast!!.show()
    }
}
