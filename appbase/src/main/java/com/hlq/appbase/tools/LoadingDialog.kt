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

package com.hlq.appbase.tools

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.hlq.appbase.R
import com.hlq.appbase.utils.DeviceUtil


/**
 * @author：HuangLinqing
 * @blog：https://huanglinqing.blog.csdn.net/?t=1
 * @公众号：Android 技术圈
 * @desc： 加载弹窗
 */
class LoadingDialog private constructor(mContext: Context) : Dialog(mContext), LifecycleObserver {

    private lateinit var tv_bootom_desc: TextView
    private lateinit var ll_dialog: LinearLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_dialog_loading)
        tv_bootom_desc = findViewById(R.id.tv_bootom_desc)
        ll_dialog = findViewById(R.id.ll_dialog)
        progressBar = findViewById(R.id.progressBar)
        val dialogWindow = window
        dialogWindow?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    /***
     * 是否可以触摸取消 默认为false
     */
    private var canceledOnTouchOutsideValue = false

    /**
     * 是否可以点击返回 取消 默认为false
     */
    private var canceledOnBack = false

    /**
     * 背景框默认
     */
    private var dialogBackgroundResource = R.drawable.drawable_loading_toast

    /**
     * 底部文字颜色
     */
    private var bootomTextColor = mContext.resources.getColor(R.color.white)

    /**
     * 底部文字大小
     */
    private var bootomTextSize = 15f

    /**
     * 弹窗布局的大小 宽度
     */
    private var dialogWidth = 0f

    /**
     * 弹窗布局的大小 长度
     */
    private var dialogHeight = 0f

    /**
     * progressbar的大小
     */
    private var progressbarSize = 0f

    /**
     * 底部文字描述
     */
    private var bootomDesc: String? = null

    @Override
    override fun show() {
        if (ownerActivity != null && !ownerActivity!!.isFinishing) {
            super.show()
            show(this)
        }
    }

    /**
     * 显示对话框
     */
    @SuppressLint("ResourceAsColor")
    private fun show(loadingDialog: LoadingDialog) {

        if (!TextUtils.isEmpty(bootomDesc)) {
            tv_bootom_desc.text = bootomDesc
            tv_bootom_desc.visibility = View.VISIBLE
        }

        //屏蔽返回键
        if (!canceledOnBack) {
            setOnKeyListener { _, keyCode, _ ->
                keyCode == KeyEvent.KEYCODE_BACK
            }
        }

        setCanceledOnTouchOutside(canceledOnTouchOutsideValue)

        //设置弹窗背景
        ll_dialog.setBackgroundResource(dialogBackgroundResource)

        tv_bootom_desc.setTextColor(bootomTextColor)

        tv_bootom_desc.textSize = bootomTextSize

        if (dialogWidth != 0f) {
            val layoutParams = ll_dialog.layoutParams
            layoutParams.width = DeviceUtil.dip2px(context, dialogWidth)
            layoutParams.height = DeviceUtil.dip2px(context, dialogHeight)
            ll_dialog.layoutParams = layoutParams
        }

        if (progressbarSize != 0f) {
            val layoutParams = progressBar.layoutParams
            layoutParams.width = DeviceUtil.dip2px(context, progressbarSize)
            layoutParams.height = DeviceUtil.dip2px(context, progressbarSize)
            progressBar.layoutParams = layoutParams
        }


    }


    class Builder(context: Context) {

        private var loadingDialog: LoadingDialog = LoadingDialog(context)

        /**
         * 设置底部文字描述
         *
         */
        fun setBootomDesc(bootomDesc: String): Builder {
            loadingDialog.bootomDesc = bootomDesc
            return this
        }

        /**
         * 设置触摸屏幕是否消失
         */
        fun setCanceledOnTouchOutsideValue(canceled: Boolean): Builder {
            loadingDialog.canceledOnBack = canceled
            return this
        }

        /**
         * 设置是否可以点击返回键
         */
        fun setCanceledOnBackValue(onBack: Boolean): Builder {
            loadingDialog.canceledOnBack = onBack
            return this
        }

        /**
         * 设置dialog背景
         */
        fun setDialogBackground(resource: Int): Builder {
            loadingDialog.dialogBackgroundResource = resource
            return this
        }

        /**
         * 设置底部文字颜色
         */
        fun setBootomTextColor(resource: Int): Builder {
            loadingDialog.bootomTextColor = resource
            return this
        }

        /**
         * 设置底部文字大小
         */
        fun setBootomTextSize(size: Float): Builder {
            loadingDialog.bootomTextSize = size
            return this
        }

        /**
         * 设置弹窗的大小
         */
        fun setDialogSize(dialogWidth: Float, dialogHeight: Float): Builder {
            loadingDialog.dialogWidth = dialogWidth
            loadingDialog.dialogHeight = dialogHeight
            return this
        }

        /**
         * 设置progressbar的大小
         */
        fun setProgressbarSize(size: Float): Builder {
            loadingDialog.progressbarSize = size
            return this
        }


        /**
         * 通过Builder类设置完属性后构造对话框的方法
         */
        fun create(): LoadingDialog {
            return loadingDialog
        }


    }

    /**
     * 取消对话框
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestory() {
        if (isShowing) {
            dismiss()
        }
    }

    init {
        if (mContext is Activity) {
            setOwnerActivity(mContext)
        }
        if (mContext is ComponentActivity) {
            mContext.lifecycle.addObserver(this)
        }
    }
}