package dev.awd.qyas.models.units

sealed class AreaUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toSquareMeters(): Double
    abstract fun toSquareFeet(): Double


    class SquareMeter(value: Double) : AreaUnit(value, "m²", "Square Meters") {
        override fun toSquareMeters(): Double = value
        override fun toSquareFeet(): Double = value * 10.7639
    }

    class SquareFoot(value: Double) : AreaUnit(value, "ft²", "Square Feet") {
        override fun toSquareMeters(): Double = value * 0.092903
        override fun toSquareFeet(): Double = value
    }
}