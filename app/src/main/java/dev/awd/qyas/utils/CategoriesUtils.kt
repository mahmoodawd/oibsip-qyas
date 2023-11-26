package dev.awd.qyas.utils

import dev.awd.qyas.models.UnitCategory

fun getCategory(title: String): UnitCategory {
    return when (title) {
        AREA -> UnitCategory.Area()
        TEMPERATURE -> UnitCategory.Temperature()
        WEIGHT -> UnitCategory.Weight()
        VOLUME -> UnitCategory.Volume()
        LENGTH -> UnitCategory.Length()
        SPEED -> UnitCategory.Speed()
        TIME -> UnitCategory.Time()
        PRESSURE -> UnitCategory.Pressure()
        STORAGE -> UnitCategory.Storage()
        else -> UnitCategory.Other()
    }
}