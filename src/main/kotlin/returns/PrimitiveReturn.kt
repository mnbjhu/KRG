package returns

import returns.util.Box

sealed class PrimitiveReturn<T>(private val value: T?): DataType<T>(){
    protected open fun getPrimitiveString(from: T) = "$from"
    override fun getStructuredString() = when(value){
        null -> throw Exception("return_types.PrimitiveReturn cannot getStructuredString with out value set")
        else -> getPrimitiveString(value)
    }
    override fun parse(value: Any?): T {
        return value as T
    }
}
class StringReturn(value: String?): PrimitiveReturn<String>(value) {
    override fun getPrimitiveString(from: String): String = "'$from'"
    override fun encode(value: String) = StringReturn(value)
}
class LongReturn(value: Long?): PrimitiveReturn<Long>(value) {
    override fun getPrimitiveString(from: Long): String = "'$from'"
    override fun encode(value: Long) = LongReturn(value)
}
class DoubleReturn(value: Double?): PrimitiveReturn<Double>(value) {
    override fun getPrimitiveString(from: Double): String = "'$from'"
    override fun encode(value: Double) = DoubleReturn(value)
}
class BooleanReturn(value: Boolean?): PrimitiveReturn<Boolean>(value) {
    override fun getPrimitiveString(from: Boolean): String = "'$from'"
    override fun encode(value: Boolean) = BooleanReturn(value)
}
