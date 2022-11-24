package returns

import kotlin.reflect.KType

class Nullable<T, U: ReturnValue<T>>(private val value: Box<U>, private val inner: KType): ReturnValue<T?>() {
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