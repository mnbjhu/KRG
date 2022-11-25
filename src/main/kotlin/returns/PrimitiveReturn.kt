package returns

import returns.util.Box

sealed class PrimitiveReturn<T>(private val value: Box<T>): DataType<T>(){
    protected open fun getPrimitiveString(from: T) = "$from"
    override fun getStructuredString() = when(value){
        is Box.WithoutValue -> throw Exception("return_types.PrimitiveReturn cannot getStructuredString with out value set")
        is Box.WithValue -> getPrimitiveString(value.value)
    }
    override fun parse(value: Any?): T {
        return value as T
    }
}
class StringReturn(value: Box<String>): PrimitiveReturn<String>(value) {
    override fun getPrimitiveString(from: String): String = "'$from'"
    override fun encode(value: String) = StringReturn(Box.WithValue(value))
}
class BooleanReturn(value: Box<Boolean>): PrimitiveReturn<Boolean>(value) {
    override fun encode(value: Boolean) = BooleanReturn(Box.WithValue(value))
}
class DoubleReturn(value: Box<Double>): PrimitiveReturn<Double>(value) {
    override fun encode(value: Double) = DoubleReturn(Box.WithValue(value))
}
class LongReturn(value: Box<Long>): PrimitiveReturn<Long>(value) {
    override fun encode(value: Long) = LongReturn(Box.WithValue(value))
}

sealed class GeoLocation{

}