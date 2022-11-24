package returns

import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

abstract class StructReturn<T>: DataType<T>(){
    override fun getStructuredString(): String {
        return this::class.memberProperties
            .filter { it.returnType.isSubtypeOf(ReturnValue::class.createType(listOf(KTypeProjection.STAR))) }
            .joinToString(prefix = "{", postfix = "}") {
                val value = it.call(this) as ReturnValue<*>
                "${it.name}: ${value.getString()}"
            }
    }
    abstract override fun encode(value: T): StructReturn<T>
    override fun parse(value: Any?): T {
        val values = (value as List<*>).iterator()
        val map = this::class.memberProperties.associate {
            val attribute = it.call() as ReturnValue<*>
            attribute to attribute.parse(values.next())
        }
        return ReturnScope(map).decode()
    }
    abstract fun ReturnScope.decode(): T
    operator fun <T, U: ReturnValue<T>>U.get(value: T): U{
        return encode(value) as U
    }
}


