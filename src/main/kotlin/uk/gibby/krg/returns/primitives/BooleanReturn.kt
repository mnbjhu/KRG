package uk.gibby.krg.returns.primitives

/**
 * Boolean return
 *
 * Represents a Redis Boolean and can be returned from graph queries
 *
 * @sample [e2e.types.primitive.Boolean.createLiteral]
 * @sample [e2e.types.primitive.Boolean.createAttribute]
 * @sample [e2e.types.primitive.Boolean.matchAttribute]
 */
class BooleanReturn(value: Boolean?): PrimitiveReturn<Boolean>(value) {
    override fun getPrimitiveString(from: Boolean): String = "$from"
    override fun encode(value: Boolean) = BooleanReturn(value)
}