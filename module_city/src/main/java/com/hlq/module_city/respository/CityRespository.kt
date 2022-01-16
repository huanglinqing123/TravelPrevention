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

package com.hlq.module_city.respository

import com.hlq.appbase.network.BaseReqData
import com.hlq.appbase.network.RetrofirServiceBuilder
import com.hlq.module_city.api.CityApi
import com.hlq.module_city.bean.reqbean.CityDataReqData

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：数据仓库层
 */
class CityRespository {

    //创建service实例
    private var netWork = RetrofirServiceBuilder.createService(
        CityApi::class.java
    )

    /**
     * 加载城市清单
     */
    suspend fun loadCityData(): BaseReqData<List<CityDataReqData>>? {
        netWork?.let {
            return it.loadCityData()
        }
        return null
    }
}