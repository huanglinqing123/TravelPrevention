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

package com.hlq.travelprevention

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.hlq.appbase.config.ArouteConfig
import com.hlq.appbase.ui.BaseActivity
import com.hlq.travelprevention.databinding.ActivityMainBinding

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：App主页面入口
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClick()
    }

    /**
     * 监听事件
     */
    private fun initClick() {
        //查询核酸检测机构
        mViewBinding.llAgency.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.AGENCY_MESSAGE)
                //传递String类型的值
                .withString("a", "a")
                //传递布尔类型的值
                .withBoolean("b", false)
                //指定启动模式
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .navigation()
        }
        //查询风险等级地区
        mViewBinding.llRiskLevel.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.RISK_LEVEL)
                .navigation()
        }
        //查询出行政策
        mViewBinding.llTravel.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.TRAVEL_POLICY)
                .navigation()
        }
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(
            layoutInflater
        )
    }
}