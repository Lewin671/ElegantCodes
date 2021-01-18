package com.example.elegantcode.android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 创建1个retrofit对象，实现该retrofit对象的创建service函数
 * 需要自己填写BASE_URL，默认converter是Gson converter
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.github.com/"
    private val retrofit by lazy {
        // 测试只加载一次
        // println("lazy函数创建retrofit")
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}