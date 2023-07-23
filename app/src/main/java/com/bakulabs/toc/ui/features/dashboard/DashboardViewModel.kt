package com.bakulabs.toc.ui.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakulabs.toc.data.network.dtos.EnvironmentData
import com.bakulabs.toc.ENVIRONMENT_API_URL
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

data class DashboardUiState(
    val isLoading: Boolean = false,
    val environmentData: EnvironmentData? = null
)

class DashboardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    fun refreshEnv() {
        _uiState.value = DashboardUiState(isLoading = true)
        viewModelScope.launch {
            Fuel.get(ENVIRONMENT_API_URL)
                .responseObject<EnvironmentData> { _, _, result ->
                    when (result) {
                        is Result.Success -> {
                            val data = result.get()
                            println(data)
                            _uiState.value = DashboardUiState(environmentData = data)
                        }

                        is Result.Failure -> {
                            val ex = result.getException()
                            println(ex)
                            _uiState.value = DashboardUiState()
                        }
                    }
                }
        }
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