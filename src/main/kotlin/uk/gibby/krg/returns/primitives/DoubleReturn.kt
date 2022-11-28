package uk.gibby.krg.returns.primitives

/**
 * Double return
 *
 * Represents a Redis Double and can be returned from graph queries
 *
 * @sample [e2e.types.primitive.Double.createLiteral]
 * @sample [e2e.types.primitive.Double.createAttribute]
 * @sample [e2e.types.primitive.Double.matchAttribute]
 */

class DoubleReturn(value: Double?): PrimitiveReturn<Double>(value) {
    override fun getPrimitiveString(from: Double): String = "$from"
    override fun encode(value: Double) = DoubleReturn(value)
}