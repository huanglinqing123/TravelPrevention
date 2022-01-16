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

package com.hlq.appbase.utils

import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：汉语转化为拼音工具
 */
object Cn2SpellUtil {

    private var stringBuilder = StringBuffer()

    /**
     * 获取汉字字符串的首字母，英文字符不变
     * 例如：阿飞→af
     */
    fun getPinYinHeadChar(chines: String): String? {
        stringBuilder.setLength(0)
        val chars = chines.toCharArray()
        val defaultFormat = HanyuPinyinOutputFormat()
        defaultFormat.caseType = HanyuPinyinCaseType.LOWERCASE
        defaultFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
        for (i in chars.indices) {
            if (chars[i] > 128.toChar()) {
                try {
                    stringBuilder.append(
                        PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0][0]
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                stringBuilder.append(chars[i])
            }
        }
        return stringBuilder.toString()
    }

    /**
     * 获取汉字字符串的第一个字母
     */
    fun getPinYinFirstLetter(str: String): String? {
        if (str.isEmpty()){
            return "#"
        }
        stringBuilder.setLength(0)
        val c = str[0]
        val pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c)
        if (pinyinArray != null) {
            stringBuilder.append(pinyinArray[0][0])
        } else {
            stringBuilder.append(c)
        }
        return stringBuilder.toString()
    }

    /**
     * 获取汉字字符串的汉语拼音，英文字符不变
     */
    fun getPinYin(chines: String): String? {
        stringBuilder.setLength(0)
        val nameChar = chines.toCharArray()
        val defaultFormat = HanyuPinyinOutputFormat()
        defaultFormat.caseType = HanyuPinyinCaseType.LOWERCASE
        defaultFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
        for (i in nameChar.indices) {
            if (nameChar[i] > 128.toChar()) {
                try {
                    stringBuilder.append(
                        PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                stringBuilder.append(nameChar[i])
            }
        }
        return stringBuilder.toString()
    }

}