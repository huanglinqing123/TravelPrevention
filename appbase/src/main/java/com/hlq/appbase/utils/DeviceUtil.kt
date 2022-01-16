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
import android.content.res.Resources
import android.util.TypedValue

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：设备相关工具类
 */
object DeviceUtil {

    /**
     * dp转为px
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val r: Resources = context.applicationContext.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dipValue, r.displayMetrics
        ).toInt()
    }
}