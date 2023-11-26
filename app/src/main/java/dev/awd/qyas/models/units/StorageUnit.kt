package dev.awd.qyas.models.units

sealed class StorageUnit(
    override var value: Double,
    override val letter: String,
    override val label: String
) : BaseUnit {
    abstract fun toBytes(): Double
    abstract fun toKilobytes(): Double
    abstract fun toMegabytes(): Double


    class Byte(value: Double) : StorageUnit(value, "B", "Bytes") {
        override fun toBytes(): Double = value
        override fun toKilobytes(): Double = value / 1024
        override fun toMegabytes(): Double = value / (1024 * 1024)
    }

    class Kilobyte(value: Double) : StorageUnit(value, "KB", "Kilobytes") {
        override fun toBytes(): Double = value * 1024
        override fun toKilobytes(): Double = value
        override fun toMegabytes(): Double = value / 1024
    }

    class Megabyte(value: Double) : StorageUnit(value, "MB", "Megabytes") {
        override fun toBytes(): Double = value * (1024 * 1024)
        override fun toKilobytes(): Double = value * 1024
        override fun toMegabytes(): Double = value
    }
}