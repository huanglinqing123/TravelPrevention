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

package com.hlq.appbase.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：activity基类
 */
abstract class BaseActivity<T : ViewBinding> : FragmentActivity() {

    lateinit var mViewBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)

        val displayMetrics = resources.displayMetrics
        displayMetrics.scaledDensity = displayMetrics.density
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val controller = ViewCompat.getWindowInsetsController(mViewBinding.root)
        controller?.isAppearanceLightNavigationBars = true
        controller?.isAppearanceLightStatusBars = true

    }

    abstract fun getViewBinding(): T
}