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

//版本号管理
object Versions {
    const val compileSdkVersion = 30
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
    const val lifecycle_version = "2.3.1"
    const val fragment_ktx_version = "1.3.6"
    const val picasso_version = "2.71828"
    const val retrofit_version = "2.9.0"
    const val logging_interceptor_version = "4.7.2"
    const val coroutines_version = "1.4.1"
    const val arouter_api_version = "1.5.0"
    const val arouter_compiler_version = "1.2.2"
}

//三方库管理
object Libs {
    const val livedata_ktx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
    const val picasso = "com.squareup.picasso:${Versions.picasso_version}"
    const val retofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val arouter_api = "com.alibaba:arouter-api:${Versions.arouter_api_version}"
    const val arouter_compiler = "com.alibaba:arouter-compiler:${Versions.arouter_compiler_version}"
}