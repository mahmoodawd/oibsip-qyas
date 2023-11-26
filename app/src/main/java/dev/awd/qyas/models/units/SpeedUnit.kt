package dev.awd.qyas.models.units

sealed class SpeedUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toMetersPerSecond(): Double
    abstract fun toMilesPerHour(): Double


    class MetersPerSecond(value: Double) : SpeedUnit(value, "m/s", "Meters/Second") {
        override fun toMetersPerSecond(): Double = value
        override fun toMilesPerHour(): Double = value * 2.23694
    }

    class MilesPerHour(value: Double) : SpeedUnit(value, "mph", "Miles/Hour") {
        override fun toMetersPerSecond(): Double = value * 0.44704
        override fun toMilesPerHour(): Double = value
    }
}