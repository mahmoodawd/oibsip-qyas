package dev.awd.qyas.screens.calculator

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.awd.qyas.models.units.AreaUnit
import dev.awd.qyas.models.units.LengthUnit
import dev.awd.qyas.models.units.PressureUnit
import dev.awd.qyas.models.units.SpeedUnit
import dev.awd.qyas.models.units.StorageUnit
import dev.awd.qyas.models.units.TemperatureUnit
import dev.awd.qyas.models.units.TimeUnit
import dev.awd.qyas.models.UnitCategory
import dev.awd.qyas.models.units.VolumeUnit
import dev.awd.qyas.models.units.WeightUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CalculatorViewModel(private val category: UnitCategory) : ViewModel() {

    var sourceUnit = MutableStateFlow(category.units.first())
        private set

    var targetUnit = MutableStateFlow(category.units.last())
        private set

    var unitList = MutableStateFlow(category.units.map { it.label })
        private set
    var categoryTitle = MutableStateFlow(category.title)
        private set
    var categoryIcon = MutableStateFlow(category.icon)
        private set


    private val sourceValue = MutableStateFlow(sourceUnit.value.value)

    private val _calculationResult = MutableStateFlow(sourceValue.value)
    val calculationResult =
        combine(sourceValue, sourceUnit, targetUnit) { sourceValue, sourceUnit, targetUnit ->
            sourceUnit.value = sourceValue
            when (sourceUnit) {
                is TemperatureUnit -> {
                    when (targetUnit as TemperatureUnit) {
                        is TemperatureUnit.Celsius -> sourceUnit.toCelsius()

                        is TemperatureUnit.Fahrenheit -> sourceUnit.toFahrenheit()
                    }
                }

                is AreaUnit -> {
                    when (targetUnit as AreaUnit) {
                        is AreaUnit.SquareMeter -> sourceUnit.toSquareMeters()
                        is AreaUnit.SquareFoot -> sourceUnit.toSquareFeet()
                    }
                }

                is LengthUnit -> {
                    when (targetUnit as LengthUnit) {
                        is LengthUnit.Kilometer -> sourceUnit.toKilometers()
                        is LengthUnit.Meter -> sourceUnit.toMeters()
                        is LengthUnit.Inch -> sourceUnit.toInches()
                    }
                }

                is StorageUnit -> {
                    when (targetUnit as StorageUnit) {
                        is StorageUnit.Byte -> sourceUnit.toBytes()
                        is StorageUnit.Kilobyte -> sourceUnit.toKilobytes()
                        is StorageUnit.Megabyte -> sourceUnit.toMegabytes()

                    }
                }

                is VolumeUnit -> {
                    when (targetUnit as VolumeUnit) {
                        is VolumeUnit.Liter -> sourceUnit.toLiters()
                        is VolumeUnit.Gallon -> sourceUnit.toGallons()
                    }
                }

                is TimeUnit -> {
                    when (targetUnit as TimeUnit) {
                        is TimeUnit.Second -> sourceUnit.toSeconds()
                        is TimeUnit.Minute -> sourceUnit.toMinutes()
                        is TimeUnit.Hour -> sourceUnit.toHours()
                    }
                }

                is PressureUnit -> {
                    when (targetUnit as PressureUnit) {
                        is PressureUnit.PSI -> sourceUnit.toPSI()
                        is PressureUnit.Pascal -> sourceUnit.toPascals()
                    }
                }

                is SpeedUnit -> {
                    when (targetUnit as SpeedUnit) {
                        is SpeedUnit.MetersPerSecond -> sourceUnit.toMetersPerSecond()
                        is SpeedUnit.MilesPerHour -> sourceUnit.toMilesPerHour()
                    }
                }

                is WeightUnit -> {
                    when (targetUnit as WeightUnit) {
                        is WeightUnit.Kilogram -> sourceUnit.toKilograms()
                        is WeightUnit.Pound -> sourceUnit.toPounds()
                    }
                }

            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _calculationResult.value
        )


    fun onSourceValueChanged(text: String) {
        val value = if (text.isDigitsOnly() && text.isNotBlank()) text.toDouble() else 0.0

        Log.i(TAG, "onSourceValueChanged: $value")
        sourceValue.value = value
    }

    fun onSourceUnitChanged(unitLabel: String) {
        val unit = category.units.first {
            it.label == unitLabel
        }
        sourceUnit.update { unit }
        Log.i(TAG, "onSourceUnitChanged: ${sourceUnit.value.label}")
    }

    fun onTargetUnitChanged(unitLabel: String) {
        val unit = category.units.first {
            it.label == unitLabel
        }
        targetUnit.update { unit }
        Log.i(TAG, "onTargetUnitChanged: ${targetUnit.value.label}")
    }


    companion object {
        private const val TAG = "CalculatorViewModel"
    }
}