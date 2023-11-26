package dev.awd.qyas.models.units

sealed class PressureUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toPascals(): Double
    abstract fun toPSI(): Double


    class Pascal(value: Double) : PressureUnit(value, "Pa", "Pascals") {
        override fun toPascals(): Double = value
        override fun toPSI(): Double = value * 0.000145037737732
    }

    class PSI(value: Double) : PressureUnit(value, "psi", "Pounds per Square Inch") {
        override fun toPascals(): Double = value / 0.000145037737732
        override fun toPSI(): Double = value
    }
}