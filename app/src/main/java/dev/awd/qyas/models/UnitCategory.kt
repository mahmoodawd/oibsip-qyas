package dev.awd.qyas.models

import androidx.annotation.DrawableRes
import dev.awd.qyas.R
import dev.awd.qyas.models.units.AreaUnit
import dev.awd.qyas.models.units.BaseUnit
import dev.awd.qyas.models.units.LengthUnit
import dev.awd.qyas.models.units.PressureUnit
import dev.awd.qyas.models.units.SpeedUnit
import dev.awd.qyas.models.units.StorageUnit
import dev.awd.qyas.models.units.TemperatureUnit
import dev.awd.qyas.models.units.TimeUnit
import dev.awd.qyas.models.units.VolumeUnit
import dev.awd.qyas.models.units.WeightUnit
import dev.awd.qyas.utils.AREA
import dev.awd.qyas.utils.LENGTH
import dev.awd.qyas.utils.PRESSURE
import dev.awd.qyas.utils.SPEED
import dev.awd.qyas.utils.STORAGE
import dev.awd.qyas.utils.TEMPERATURE
import dev.awd.qyas.utils.TIME
import dev.awd.qyas.utils.VOLUME
import dev.awd.qyas.utils.WEIGHT


/**
 * Unit Category to passed between screens
 * contains info about each category like [title], [icon], and [units]: which represents
 * each category units
 */
sealed class UnitCategory(
    val title: String,
    @DrawableRes val icon: Int,
    val units: List<BaseUnit>,
) {
    class Temperature :
        UnitCategory(
            icon = R.drawable.icon_temperature,
            title = TEMPERATURE,
            units = listOf(
                TemperatureUnit.Celsius(0.0),
                TemperatureUnit.Fahrenheit(0.0),
            ),
        )

    class Area :
        UnitCategory(
            icon = R.drawable.icon_area,
            title = AREA,
            units = listOf(
                AreaUnit.SquareFoot(0.0),
                AreaUnit.SquareMeter(0.0),
            ),
        )

    class Weight :
        UnitCategory(
            icon = R.drawable.icon_weight,
            title = WEIGHT,
            units = listOf(
                WeightUnit.Kilogram(0.0),
                WeightUnit.Pound(0.0),
            ),
        )

    class Volume :
        UnitCategory(
            icon = R.drawable.icon_volume,
            title = VOLUME,
            units = listOf(
                VolumeUnit.Gallon(0.0),
                VolumeUnit.Liter(0.0),
            ),
        )

    class Length :
        UnitCategory(
            icon = R.drawable.icon_length,
            title = LENGTH,
            units = listOf(
                LengthUnit.Inch(0.0),
                LengthUnit.Meter(0.0),
                LengthUnit.Kilometer(0.0),
            ),
        )

    class Speed :
        UnitCategory(
            icon = R.drawable.icon_speed,
            title = SPEED,
            units = listOf(
                SpeedUnit.MetersPerSecond(0.0),
                SpeedUnit.MilesPerHour(0.0),
            ),
        )

    class Time :
        UnitCategory(
            icon = R.drawable.icon_time,
            title = TIME,
            units = listOf(
                TimeUnit.Hour(0.0),
                TimeUnit.Minute(0.0),
                TimeUnit.Second(0.0),
            ),
        )

    class Pressure :
        UnitCategory(
            icon = R.drawable.icon_pressure,
            title = PRESSURE,
            units = listOf(
                PressureUnit.PSI(0.0),
                PressureUnit.Pascal(0.0),
            ),
        )

    class Storage :
        UnitCategory(
            icon = R.drawable.icon_storage,
            title = STORAGE,
            units = listOf(
                StorageUnit.Byte(0.0),
                StorageUnit.Kilobyte(0.0),
                StorageUnit.Megabyte(0.0),
            ),
        )

    class Other : UnitCategory(
        icon = R.drawable.ic_launcher_foreground,
        title = "Undefined",
        units = emptyList()
    )
}