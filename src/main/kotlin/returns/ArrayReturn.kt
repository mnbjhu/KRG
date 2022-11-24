package returns

import kotlin.reflect.KType

class ArrayReturn<T, U: ReturnValue<T>>(private val values: Box<List<U>>, private val inner: KType): DataType<List<T>>() {
    override fun getStructuredString(): String {
        return when(values){
            is Box.WithoutValue -> throw Exception("return_types.ArrayReturn cannot getStructuredString with out values set")
            is Box.WithValue -> values.value.joinToString(prefix = "[", postfix = "]") { it.getString() }
        }
    }
    override fun parse(value: Any?): List<T> {
        val dummy = createDummy(inner) as U
        return (value as List<*>).map { dummy.parse(it) }
    }

    override fun encode(value: List<T>): ArrayReturn<T, U> {
        val dummy = createDummy(inner) as U
        return ArrayReturn(Box.WithValue(value.map { dummy.encode(it) as U }), inner)
    }
}