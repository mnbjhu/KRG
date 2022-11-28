package uk.gibby.krg.returns.util

import uk.gibby.krg.returns.ReturnValue
import kotlin.reflect.KProperty

class ReturnScope(private val map: Map<String, *>) {
    fun <T, U: ReturnValue<T>>KProperty<U>.result(): T{
        return map[name] as T
    }
}