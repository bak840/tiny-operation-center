package com.bakulabs.toc.data.network.dtos

data class EnvironmentData(
    val temperature: Float,
    val humidity: Float,
    val pressure: Float,
    val illuminance: Float
)