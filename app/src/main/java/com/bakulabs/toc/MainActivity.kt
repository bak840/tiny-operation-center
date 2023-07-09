package com.bakulabs.toc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bakulabs.toc.models.EnvironmentData
import com.bakulabs.toc.ui.theme.TinyOperationCenterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinyOperationCenterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text("My Home", fontWeight = FontWeight.Bold)
            }, actions = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(32.dp)
                )
            })
        },
    ) { contentPadding ->
        // Screen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            EnvironmentWidget()
        }
    }
}

@Composable
fun EnvironmentWidget(
    data: EnvironmentData = EnvironmentData(
        temperature = 28.0f,
        humidity = 62.0f,
        pressure = 100.1f,
        illuminance = 26.0f
    )
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
                    text = "${data.temperature} °C",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "${data.humidity} %",
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
                    text = "${data.pressure} kPa",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "${data.illuminance} lux",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyHomePreview() {
    TinyOperationCenterTheme {
        HomeScreen()
    }
}