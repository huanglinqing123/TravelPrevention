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

package com.hlq.module_test_agency.api

import com.hlq.appbase.network.BaseApi
import com.hlq.appbase.network.BaseReqData
import com.hlq.module_test_agency.bean.reqbean.AgencyMessageReqData
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：检测机构api
 */
interface AgencyApi {

    /**
     * 加载检测机构信息
     * @param key 接口key值 无须传
     * @param city_id 城市id，从城市选择页面返回
     */
    @GET("hsjg")
    suspend fun loadTestAgencyMessage(
        @Query("city_id") city_id: String,
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<AgencyMessageReqData>
}