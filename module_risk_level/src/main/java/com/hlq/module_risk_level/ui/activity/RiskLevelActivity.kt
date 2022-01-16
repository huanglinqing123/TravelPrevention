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

package com.hlq.module_risk_level.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.hlq.appbase.config.ArouteConfig.RISK_LEVEL
import com.hlq.appbase.ui.BaseActivity
import com.hlq.appbase.utils.DialogLoadingUtils
import com.hlq.module_risk_level.bean.enum.DataTypeEnum
import com.hlq.module_risk_level.bean.reqbean.RiskLevelDetailBean
import com.hlq.module_risk_level.databinding.ActivityRiskLevelBinding
import com.hlq.module_risk_level.ui.adapter.RiskLevelMessageAdpter
import com.hlq.module_risk_level.viewmodel.RiskLevelViewModel

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：风险等级查询Activity
 */
@Route(path = RISK_LEVEL)
class RiskLevelActivity : BaseActivity<ActivityRiskLevelBinding>() {

    /**
     * viewModel
     */
    private val riskLevelViewModel by viewModels<RiskLevelViewModel>()

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
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager
        loadData()
    }

    /**
     * 查询数据
     */
    private fun loadData() {
        DialogLoadingUtils.showLoading(this, "请稍后")
        riskLevelViewModel.loadRiskLevelMessage().observe(this, Observer {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    mViewBinding.bean = it.result
                    val list = mutableListOf<RiskLevelDetailBean>()
                    val highTitle = RiskLevelDetailBean()
                    highTitle.dataType = DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal
                    list.add(highTitle)
                    it.result?.high_list?.let {
                        list.addAll(it)
                    }
                    val middleTitle = RiskLevelDetailBean()
                    middleTitle.dataType = DataTypeEnum.DATA_IS_MIDDLE_TITLE.ordinal
                    list.add(middleTitle)
                    it.result?.middle_list?.let { middle ->
                        list.addAll(middle)
                    }
                    val riskLevelAdapter = RiskLevelMessageAdpter(list)
                    mViewBinding.rvData.adapter = riskLevelAdapter
                }
            }
        })
    }

    override fun getViewBinding(): ActivityRiskLevelBinding {
        return ActivityRiskLevelBinding.inflate(layoutInflater)
    }
}