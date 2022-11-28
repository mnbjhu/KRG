package uk.gibby.krg.returns.generic

import uk.gibby.krg.returns.DataType
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.util.ReturnScope
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

abstract class StructReturn<T>: DataType<T>(){
    override fun getStructuredString(): String {
        return this::class.memberProperties
            .filter { it.returnType.isSubtypeOf(ReturnValue::class.createType(listOf(KTypeProjection.STAR))) }
            .joinToString(prefix = "[", postfix = "]") {
                val value = it.call(this) as ReturnValue<*>
                value.getString()
            }
    }
    abstract override fun encode(value: T): StructReturn<T>
    override fun parse(value: Any?): T {
        val values = (value as List<*>).iterator()
        val map = this::class.memberProperties
            .filter { it.returnType.isSubtypeOf(ReturnValue::class.createType(listOf(KTypeProjection.STAR))) }
            .associate { it.name to values.next() }
        return ReturnScope(map).decode()
    }
    abstract fun ReturnScope.decode(): T
    operator fun <T, U: ReturnValue<T>>U.get(value: T): U{
        return encode(value) as U
    }
}


