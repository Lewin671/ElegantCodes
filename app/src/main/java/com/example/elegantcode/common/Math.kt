package com.example.elegantcode.common

import kotlin.math.pow

// 添加对Int和Long的pow支持

fun Int.pow(x:Int) = toDouble().pow(x).toInt()

fun Long.pow(x:Int) = toDouble().pow(x).toLong()
