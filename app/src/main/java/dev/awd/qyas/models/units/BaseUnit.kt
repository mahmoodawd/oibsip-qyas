package dev.awd.qyas.models.units

/**
 * A consistent interface for unit classes within different categories
 */
sealed interface BaseUnit {
    var value: Double
    val letter: String
    val label: String
}