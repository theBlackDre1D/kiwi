package com.g3.kiwi.models

import java.io.Serializable

data class Suggestion(
    val accessibility: Double?,
    val activity: String?,
    val key: String?,
    val link: String?,
    val participants: Int?,
    val price: Int?,
    val type: String?
) : Serializable