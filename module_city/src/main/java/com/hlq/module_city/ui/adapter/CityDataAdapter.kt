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

package com.hlq.module_city.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlq.module_city.bean.reqbean.CityDataReqData
import com.hlq.module_city.databinding.ItemCityDataBinding

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：显示城市数据id
 */
class CityDataAdapter(
    var data: List<CityDataReqData.CitysData>,
    val callback: (Int, CityDataReqData.CitysData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCityDataBinding.inflate(
            LayoutInflater.from(
                parent.context
            )
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataHolder = holder as DataViewHolder
        dataHolder.binding.data = data[position]
        dataHolder.binding.tvFirstLetter.visibility = View.VISIBLE
        if (position >= 1) {
            if (data[position].firstLetter != data[position - 1].firstLetter) {
                dataHolder.binding.tvFirstLetter.visibility = View.VISIBLE
            } else {
                dataHolder.binding.tvFirstLetter.visibility = View.GONE
            }
        }
        dataHolder.binding.llParent.setOnClickListener {
            callback(position, data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal class DataViewHolder(val binding: ItemCityDataBinding) :
        RecyclerView.ViewHolder(binding.root)

}