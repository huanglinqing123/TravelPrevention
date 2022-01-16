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

package com.hlq.module_city.bean.reqbean

import com.hlq.appbase.utils.Cn2SpellUtil
import java.util.*

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：城市清单数据格式
 */
class CityDataReqData {
    private val provinceId: String? = null
    private val province: String? = null
    val citys: List<CitysData>? = null

    class CitysData : Comparable<CitysData> {
        var cityNamePinyin: String? = null
            get() {
                field = city?.let {
                    return Cn2SpellUtil.getPinYin(it)
                }
                return ""
            }
        var firstLetter: String? = null
            get() {
                val letter = Regex("[a-zA-Z]")
                city?.let {
                    field = Cn2SpellUtil.getPinYinFirstLetter(it)?.uppercase(Locale.getDefault())
                    if (!field?.matches(letter)!!) {
                        field = "#"
                    }
                    return field
                }
                return "#"
            }

        val city_id: String? = null
        val city: String? = null
        override fun compareTo(other: CitysData): Int {
            return if (firstLetter.equals("#") && !other.firstLetter.equals("#")) {
                1
            } else if (!firstLetter.equals("#") && other.firstLetter.equals("#")) {
                -1
            } else {
                if (cityNamePinyin != null && other.cityNamePinyin != null) {
                    cityNamePinyin!!.compareTo(other.cityNamePinyin!!, false)
                } else {
                    1
                }

            }
        }
    }
}