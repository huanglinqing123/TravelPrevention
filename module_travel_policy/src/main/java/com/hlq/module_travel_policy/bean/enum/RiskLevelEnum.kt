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

package com.hlq.module_test_agency.bean.enum

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：风险等级枚举
 */
enum class RiskLevelEnum(var riskLevel: String, var riskLevelDesc: String) {
    ENUM_0("0", "未知"),
    ENUM_1("1", "低风险"),
    ENUM_2("2", "中风险"),
    ENUM_3("3", "高风险"),
    ENUM_4("4", "部分地区中风险"),
    ENUM_5("5", "部分地区高风险"),
    ENUM_6("6", "部分地区中高风险");

    companion object {
        fun getRiskLevelDesc(riskLevel: String): String {
            for (c in values()) {
                if (c.riskLevel == riskLevel) {
                    return c.riskLevelDesc
                }
            }
            return ""
        }
    }

}