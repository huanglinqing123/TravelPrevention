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

package com.hlq.module_test_agency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hlq.appbase.network.HttpErrorDeal
import com.hlq.module_test_agency.respository.AgencyRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：CityDataViewModel
 */
class AgencyViewModel : ViewModel() {

    /**
     * 查询检测机构信息
     * @param cityId 城市id
     */
    fun loadTestAgencyMessage(cityId: String) = flow {
        val data = AgencyRespository().loadTestAgencyMessage(cityId)
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}