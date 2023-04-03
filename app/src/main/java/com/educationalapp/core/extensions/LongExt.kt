package com.educationalapp.core.extensions

import kotlin.math.round

fun formattedLikeCount(count: Long): String {
    val k = 1000
    val m = k * 1000

    return when {
        count >= m -> {
            "%dM".format(round((count / m).toDouble()).toLong())
        }
        count >= k -> {
            "%dK".format(round((count / k).toDouble()).toLong())
        }
        else -> {
            "%s".format(count)
        }
    }
}