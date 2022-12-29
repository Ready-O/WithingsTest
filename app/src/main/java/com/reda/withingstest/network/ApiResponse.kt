package com.reda.withingstest.network

import com.reda.withingstest.model.Image

data class ApiResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<Image>
)
