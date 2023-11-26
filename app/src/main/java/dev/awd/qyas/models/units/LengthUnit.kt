package dev.awd.qyas.models.units

sealed class LengthUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toMeters(): Double
    abstract fun toKilometers(): Double
    abstract fun toInches(): Double


    class Meter(value: Double) : LengthUnit(value, "m", "Meters") {
        override fun toMeters(): Double = value
        override fun toKilometers(): Double = value / 1000
        override fun toInches(): Double = value / 0.0254
    }

    class Kilometer(value: Double) : LengthUnit(value, "km", "Kilometers") {
        override fun toMeters(): Double = value * 1000
        override fun toKilometers(): Double = value
        override fun toInches(): Double = value * 1000 / 0.0254
    }

    class Inch(value: Double) : LengthUnit(value, "in", "Inches") {
        override fun toMeters(): Double = value * 0.0254
        override fun toKilometers(): Double = value * 0.0254 / 1000
        override fun toInches(): Double = value
    }
}