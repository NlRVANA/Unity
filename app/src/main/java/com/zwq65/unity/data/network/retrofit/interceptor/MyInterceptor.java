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

package com.zwq65.unity.data.network.retrofit.interceptor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zwq65.unity.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ================================================
 * 自定义拦截器(log request and response data)
 * <p>
 * Created by NIRVANA on 2017/10/12
 * Contact with <zwq651406441@gmail.com>
 * ================================================
 */
public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        LogUtils.d("request", "request:" + chain.request().url());
        ResponseBody body;
        try {
            body = response.peekBody(1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        String ss = body.string();
        Log.d("retrofitResponse", ss);
        return response;
    }
}