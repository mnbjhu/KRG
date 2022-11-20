sealed class PrimitiveReturn<T>(private val value: Box<T>): DataType<T>(){
    protected abstract fun getPrimitiveString(from: T): String
    override fun getStructuredString() = when(value){
        is Box.WithoutValue -> throw Exception("PrimitiveReturn cannot getStructuredString with out value set")
        is Box.WithValue -> getPrimitiveString(value.value)
    }
    override fun parse(value: Any?): T {
        return value as T
    }
    class StringReturn(value: Box<String>): PrimitiveReturn<String>(value) {
        override fun getPrimitiveString(from: String): String = "'$from'"
        override fun encode(value: String) = StringReturn(Box.WithValue(value))
    }
}