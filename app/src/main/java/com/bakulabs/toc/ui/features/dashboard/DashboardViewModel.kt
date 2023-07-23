package com.bakulabs.toc.ui.features.dashboard

import androidx.lifecycle.ViewModel
import com.bakulabs.toc.data.network.dtos.EnvironmentData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class DashboardUiState(
    val environmentData: EnvironmentData? = null
)

class DashboardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        refreshEnvData()
    }

    fun refreshEnvData() {
        _uiState.value = DashboardUiState(environmentData = generateRandomEnvData())
    }

    private fun generateRandomEnvData(): EnvironmentData {
        return EnvironmentData(
            temperature = Random.nextFloat() * 30,
            humidity = Random.nextFloat() * 100,
            illuminance = Random.nextInt(500).toFloat(),
            pressure = Random.nextInt(1000).toFloat()
        )
    }
}