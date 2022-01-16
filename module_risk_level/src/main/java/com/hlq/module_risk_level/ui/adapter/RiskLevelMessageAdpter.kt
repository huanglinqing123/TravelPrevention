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

package com.hlq.module_risk_level.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlq.module_risk_level.R
import com.hlq.module_risk_level.bean.enum.DataTypeEnum
import com.hlq.module_risk_level.bean.reqbean.RiskLevelDetailBean
import com.hlq.module_risk_level.databinding.ItemErrorBinding
import com.hlq.module_risk_level.databinding.ItemRisklevelMessageBinding
import com.hlq.module_risk_level.databinding.ItemTitleBinding

/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc：显示核酸检测机构信息
 */
class RiskLevelMessageAdpter(
    var data: List<RiskLevelDetailBean>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataTypeEnum.DATA_IS_RISKLEVEL.ordinal -> {
                val binding = ItemRisklevelMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return DataViewHolder(binding)
            }
            DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal,
            DataTypeEnum.DATA_IS_MIDDLE_TITLE.ordinal -> {
                val binding = ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return DataTitleHolder(binding)
            }
            else -> {
                //处理未知类型
                val binding = ItemErrorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return UnknowDataType(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.binding.bean = data[position]
            }
            is DataTitleHolder -> {
                if (data[position].dataType == DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal) {
                    holder.img.setImageResource(R.mipmap.img_high)
                    holder.title.text = "高风险地区信息"
                } else {
                    holder.img.setImageResource(R.mipmap.img_middle)
                    holder.title.text = "中风险地区信息"
                }

            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].dataType
    }

    internal class DataViewHolder(val binding: ItemRisklevelMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    internal class DataTitleHolder(val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var img = binding.imgSrc
        var title = binding.tvTitle
    }

    internal class UnknowDataType(val binding: ItemErrorBinding) :
        RecyclerView.ViewHolder(binding.root)

}