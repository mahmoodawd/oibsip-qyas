package dev.awd.qyas.models.units

sealed class TemperatureUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toCelsius(): Double
    abstract fun toFahrenheit(): Double


    class Celsius(value: Double) : TemperatureUnit(value, "°C", "Celsius") {
        override fun toCelsius(): Double = value
        override fun toFahrenheit(): Double = value * 9 / 5 + 32
    }

    class Fahrenheit(value: Double) : TemperatureUnit(value, "°F", "Fahrenheit") {
        override fun toCelsius(): Double = (value - 32) * 5 / 9
        override fun toFahrenheit(): Double = value
    }
}