package dev.awd.qyas

import android.os.Bundle
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.awd.qyas.models.UnitCategory
import dev.awd.qyas.screens.CategoriesScreen
import dev.awd.qyas.screens.calculator.CalculatorScreen
import dev.awd.qyas.ui.theme.QyasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QyasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var displayCalculator by rememberSaveable {
                        mutableStateOf(false)
                    }
                    var category: UnitCategory by remember {
                        mutableStateOf(UnitCategory.Temperature())
                    }

                    if (displayCalculator) {
                        CalculatorScreen(
                            category = category,
                            onBackPressed = {
                                displayCalculator = false
                            })
                    } else
                        CategoriesScreen(onCategoryClick = {
                            category = it
                            displayCalculator = true
                        })
                }
            }
        }
    }

}