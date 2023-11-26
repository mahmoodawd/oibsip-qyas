package dev.awd.qyas.models.units

sealed class VolumeUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toLiters(): Double
    abstract fun toGallons(): Double


    class Liter(value: Double) : VolumeUnit(value, "L", "Liters") {
        override fun toLiters(): Double = value
        override fun toGallons(): Double = value * 0.264172
    }

    class Gallon(value: Double) : VolumeUnit(value, "gal", "Gallons") {
        override fun toLiters(): Double = value * 3.78541
        override fun toGallons(): Double = value
    }
}