package uk.gibby.krg.returns.generic


import uk.gibby.krg.returns.NotNull
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.util.Box
import kotlin.reflect.KType

/**
 * Nullable return
 *
 * Represents a Redis nullable value and can be returned from graph queries
 *
 * @sample [e2e.types.primitive.String.createNullLiteral]
 * @sample [e2e.types.Array.createNullLiteral]
 */
class Nullable<T, U: NotNull<T>>(private val value: Box<U>, private val inner: KType): ReturnValue<T?>() {
    private val dummy = createDummy(inner) as U
    override fun getStructuredString() = when(value){
        is Box.WithoutValue -> "NULL"
        is Box.WithValue -> value.value.getString()
    }
    override fun parse(value: Any?): T? {
        return if (value != null) dummy.parse(value)
        else null
    }
    override fun encode(value: T?): Nullable<T, U> {
        return if (value == null) Nullable(Box.WithoutValue, inner)
        else Nullable(Box.WithValue(dummy.encode(value) as U), inner)
    }
}