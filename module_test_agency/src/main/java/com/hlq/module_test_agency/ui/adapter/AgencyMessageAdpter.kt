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

package com.hlq.module_test_agency.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlq.module_test_agency.bean.reqbean.AgencyMessageReqData
import com.hlq.module_test_agency.databinding.ItemAgencymessageBinding

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：显示核酸检测机构信息
 */
class AgencyMessageAdpter(
    var data: List<AgencyMessageReqData.Data>,
    val callback: (Int, AgencyMessageReqData.Data) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemAgencymessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataHolder = holder as DataViewHolder
        dataHolder.binding.bean = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal class DataViewHolder(val binding: ItemAgencymessageBinding) :
        RecyclerView.ViewHolder(binding.root)

}