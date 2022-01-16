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

package com.hlq.appbase.utils

import android.content.Context
import com.hlq.appbase.expand.defaultDialogView
import com.hlq.appbase.tools.LoadingDialog

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：加载对话框工具类
 */
object DialogLoadingUtils {

    private var dialog: LoadingDialog? = null

    /**
     * 显示等待框
     */
    fun showLoading(context: Context, mes: String) {

        dialog?.apply {
            if (isShowing) {
                cancel()
            }
        }

        dialog = LoadingDialog.Builder(context)
            .defaultDialogView()
            .setBootomDesc(mes)
            .create()
        dialog!!.show()
    }


    /**
     * 取消
     */
    fun cancel() {
        dialog?.cancel()
    }
}