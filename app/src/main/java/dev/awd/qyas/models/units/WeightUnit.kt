package dev.awd.qyas.models.units

sealed class WeightUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toKilograms(): Double
    abstract fun toPounds(): Double


    class Kilogram(value: Double) : WeightUnit(value, "kg", "Kilograms") {
        override fun toKilograms(): Double = value
        override fun toPounds(): Double = value / 0.453592
    }

    class Pound(value: Double) : WeightUnit(value, "lb", "Pounds") {
        override fun toKilograms(): Double = value * 0.453592
        override fun toPounds(): Double = value
    }
}