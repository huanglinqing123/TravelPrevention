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

import com.hlq.appbase.BaseApplication
import com.hlq.appbase.R
import com.hlq.appbase.utils.ToastUtil
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：统一处理异常
 */
object HttpErrorDeal {

    /**
     * 处理 http异常
     * @param error 异常信息
     * @param deal 异常时处理方法
     */
    fun dealHttpError(error: Throwable, deal: (() -> Unit)? = null) {
        when (error) {
            is SocketException -> {
                ToastUtil.shortShow(BaseApplication.context.getString(R.string.server_connection_abnormal))
            }
            is HttpException -> {
                ToastUtil.shortShow(BaseApplication.context.getString(R.string.server_connection_failed))
            }
            is SocketTimeoutException -> {
                ToastUtil.shortShow(BaseApplication.context.getString(R.string.request_timed_out))
            }
            is IOException -> {
                ToastUtil.shortShow(BaseApplication.context.getString(R.string.server_connection_failed))
            }
            is CancellationException -> {
                //协程被取消 这里是正常的 不提示
            }
            else -> {
                error.message?.let {
                    if (it.isNotEmpty()) {
                        ToastUtil.shortShow(it)
                    } else {
                        ToastUtil.shortShow(BaseApplication.context.getString(R.string.null_pointer_exception))
                    }
                }
            }
        }

        if (deal != null) {
            deal()
        }
    }
}