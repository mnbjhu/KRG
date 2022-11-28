package uk.gibby.krg.returns.primitives

/**
 * Long return
 *
 * Represents a Redis Long and can be returned from graph queries
 *
 * @sample [e2e.types.primitive.Long.createLiteral]
 * @sample [e2e.types.primitive.Long.createAttribute]
 * @sample [e2e.types.primitive.Long.matchAttribute]
 */
class LongReturn(value: Long?): PrimitiveReturn<Long>(value) {
    override fun getPrimitiveString(from: Long): String = "$from"
    override fun encode(value: Long) = LongReturn(value)
}