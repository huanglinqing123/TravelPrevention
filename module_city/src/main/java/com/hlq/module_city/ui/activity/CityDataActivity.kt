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

package com.hlq.module_city.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.hlq.appbase.config.ArouteConfig.CITY_DATA
import com.hlq.appbase.config.ParametersConfig
import com.hlq.appbase.config.ResultCodeConfig
import com.hlq.appbase.ui.BaseActivity
import com.hlq.appbase.utils.DialogLoadingUtils
import com.hlq.module_city.R
import com.hlq.module_city.bean.reqbean.CityDataReqData
import com.hlq.module_city.databinding.ActivityCityDataBinding
import com.hlq.module_city.ui.adapter.CityDataAdapter
import com.hlq.module_city.ui.view.IndexView
import com.hlq.module_city.viewmodel.CityDataViewModel

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：城市数据Activity
 */

@Route(path = CITY_DATA)
class CityDataActivity : BaseActivity<ActivityCityDataBinding>() {

    /**
     * viewModel
     */
    private val cityDataViewModel by viewModels<CityDataViewModel>()

    /**
     * 城市数据
     */
    val cityList = mutableListOf<CityDataReqData.CitysData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        initView()
        loadData()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        mViewBinding.toolbar.setOnClickListener {
            finish()
        }
        //索引滚动
        mViewBinding.indexView.setOnIndexChangeListener(object : IndexView.OnIndexChangeListener {
            override fun onIndexChange(word: String?) {
                mViewBinding.tvIndex.visibility = View.VISIBLE
                mViewBinding.tvIndex.text = word
                word?.let {
                    cityList.let { it ->
                        it.forEachIndexed { position, data ->
                            if (word == data.firstLetter) {
                                mViewBinding.rvData.scrollToPosition(position)
                                return
                            }
                        }
                    }
                }
            }

            override fun uplift() {
                mViewBinding.tvIndex.visibility = View.GONE
            }

        })
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager

    }

    /**
     * 加载数据
     */
    private fun loadData() {
        DialogLoadingUtils.showLoading(this, getString(R.string.wait_please))
        cityDataViewModel.loadCityData().observe(this, {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    //请求成功
                    it.result?.let { list ->
                        for (i in list.indices) {
                            val data = list[i].citys
                            data?.let { data ->
                                for (index in data.indices) {
                                    cityList.add(data[index])
                                }
                            }
                        }
                        cityList.sort()
                        val cityDataAdpter = CityDataAdapter(cityList,
                            callback = { position, data ->
                                //选择数据回调
                                val intent = Intent()
                                intent.putExtra(ParametersConfig.CITY_ID, data.city_id)
                                intent.putExtra(ParametersConfig.CITY_NAME, data.city)
                                setResult(
                                    ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS,
                                    intent
                                )
                                finish()
                            })
                        mViewBinding.rvData.adapter = cityDataAdpter

                    }
                }
            }
        })
    }

    override fun getViewBinding(): ActivityCityDataBinding {
        return ActivityCityDataBinding.inflate(layoutInflater)
    }


}