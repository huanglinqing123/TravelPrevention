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

package com.hlq.appbase.config

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：路由管理配置文件
 */
object ArouteConfig {

    /**
     * 城市数据路由地址
     */
    const val CITY_DATA = "/city/citydata"

    /**
     * 检测机构信息
     */
    const val AGENCY_MESSAGE = "/agency/message"

    /**
     * 风险等级查询
     */
    const val RISK_LEVEL = "/risk/level"

    /**
     * 出行政策查询
     */
    const val TRAVEL_POLICY = "/travel/policy"
}