/*
 *
 *  * Copyright (C)  HuangLinqing, TravelPrevention Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.hlq.appbase.network

import NetWorkUtil
import android.widget.Toast
import com.hlq.appbase.BaseApplication
import com.hlq.appbase.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：创建retrofitSevice
 */
object RetrofirServiceBuilder {

    /**
     * 初始化缓存个数
     */
    private const val INIT_SIZE = 5

    /**
     * 存储创建的对象
     */
    var httpHashMap = object : LinkedHashMap<String, Any>(INIT_SIZE, 1f, true) {
        override fun removeEldestEntry(eldest: Map.Entry<String, Any>): Boolean {
            return size > INIT_SIZE
        }
    }

    /**
     * 创建Service实例
     * @param clazz 实例
     * @param baseApi baseurl
     */
    fun <T> createService(
        clazz: Class<T>,
        baseApi: String = BaseApi.MAIN_API
    ): T? {
        //网络未连接 情况处理
        if (!NetWorkUtil.isConnected(BaseApplication.context)) {
            Toast.makeText(
                BaseApplication.context,
                BaseApplication.context.getString(R.string.network_disconnect), Toast.LENGTH_SHORT
            ).show()
            return null
        }

        //若缓存中有取出缓存
        if (httpHashMap.containsKey(clazz.name)) {
            return httpHashMap[clazz.name] as T
        }

        //添加日志拦截器
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                HttpLoggingInterceptor.Logger.DEFAULT.log(message)
            }
        })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseApi)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(clazz)
        httpHashMap[clazz.name] = service as Any
        return retrofit.create(clazz)
    }
}