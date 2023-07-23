package com.bakulabs.toc.ui.features.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakulabs.toc.data.network.dtos.EnvironmentData
import com.bakulabs.toc.ui.theme.TinyOperationCenterTheme

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = viewModel()
) {
    val dashboardUiState by dashboardViewModel.uiState.collectAsState()
    Column() {
        EnvironmentCard(
            data = dashboardUiState.environmentData,
            onRefresh = dashboardViewModel::refreshEnvData
        )
    }
}

@Composable
fun EnvironmentCard(
    data: EnvironmentData?,
    onRefresh: () -> Unit
) {
    ElevatedCard {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Environment:", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "Temperature",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Humidity",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Text(
                    text = if (data != null) "%.2f °C".format(data.temperature) else "- °C",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = if (data != null) "%.2f %%".format(data.humidity) else "- %",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(
                    text = "Pressure",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Illuminance",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row {
                Text(
                    text = if (data != null) "%.2f kPa".format(data.pressure) else "- kPa",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = if (data != null) "%.2f lux".format(data.illuminance) else " lux",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FilledTonalButton(
                onClick = onRefresh,
                modifier = Modifier.align(CenterHorizontally)
            ) {
                Text(text = "Refresh")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EnvironmentCardPreview() {
    TinyOperationCenterTheme {
        EnvironmentCard(
            data = EnvironmentData(
                temperature = 28.0f,
                humidity = 62.0f,
                pressure = 100.1f,
                illuminance = 26.0f
            ), onRefresh = {}
        )
    }
}