package uk.gibby.krg.returns.primitives

/**
 * String return
 *
 * Represents a Redis String and can be returned from graph queries
 *
 * @sample [e2e.types.primitive.String.createLiteral]
 * @sample [e2e.types.primitive.String.createAttribute]
 * @sample [e2e.types.primitive.String.matchAttribute]
 */
class StringReturn(value: String?): PrimitiveReturn<String>(value) {
    override fun getPrimitiveString(from: String): String = "'${from
        .replace("\\", "\\\\")
        .replace("'", "\\'")}'"

    override fun encode(value: String) = StringReturn(value)
}