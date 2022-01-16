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

package com.hlq.module_travel_policy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hlq.appbase.config.ArouteConfig
import com.hlq.appbase.config.ParametersConfig.CITY_ID
import com.hlq.appbase.config.ParametersConfig.CITY_NAME
import com.hlq.appbase.config.RequsetCodeConfig.REQUSET_CODE_SELECT_FROM_CITY
import com.hlq.appbase.config.RequsetCodeConfig.REQUSET_CODE_SELECT_TO_CITY
import com.hlq.appbase.config.ResultCodeConfig
import com.hlq.appbase.ui.BaseActivity
import com.hlq.appbase.utils.DialogLoadingUtils
import com.hlq.module_travel_policy.databinding.ActivityTravelPolicyBinding
import com.hlq.module_travel_policy.viewmodel.TravelPolicyViewModel

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：出行政策Activity
 */
@Route(path = ArouteConfig.TRAVEL_POLICY)
class TravelPolicyActivity : BaseActivity<ActivityTravelPolicyBinding>() {

    /**
     * viewModel
     */
    private val travelPolicyViewModel by viewModels<TravelPolicyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        mViewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        //选择出发地
        mViewBinding.tvFromCity.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.CITY_DATA)
                .navigation(this, REQUSET_CODE_SELECT_FROM_CITY)
        }
        //选择目的地
        mViewBinding.tvToCity.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.CITY_DATA)
                .navigation(this, REQUSET_CODE_SELECT_TO_CITY)
        }
    }

    var fromCityId: String? = null
    var toCityId: String? = null

    /**
     * 查询数据
     */
    private fun loadData() {
        fromCityId?.let { fromCityId ->
            toCityId?.let { toCityId ->
                DialogLoadingUtils.showLoading(this, "请稍后")
                travelPolicyViewModel.queryTravelPolicy(fromCityId, toCityId)
                    .observe(this, Observer {
                        DialogLoadingUtils.cancel()
                        it?.let {
                            if (it.error_code == 0) {
                                mViewBinding.bean = it.result
                            }
                        }
                    })
            }
        }
    }

    override fun getViewBinding(): ActivityTravelPolicyBinding {
        return ActivityTravelPolicyBinding.inflate(layoutInflater)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUSET_CODE_SELECT_FROM_CITY -> {
                //选择出发地
                if (resultCode == ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS) {
                    fromCityId = data?.getStringExtra(CITY_ID)
                    mViewBinding.tvFromCity.text = data?.getStringExtra(CITY_NAME)
                    loadData()
                }
            }
            REQUSET_CODE_SELECT_TO_CITY -> {
                //选择目的地
                if (resultCode == ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS) {
                    toCityId = data?.getStringExtra(CITY_ID)
                    mViewBinding.tvToCity.text = data?.getStringExtra(CITY_NAME)
                    loadData()
                }
            }
        }
    }
}