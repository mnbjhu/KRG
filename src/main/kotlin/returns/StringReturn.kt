package returns

class StringReturn(value: Box<String>): PrimitiveReturn<String>(value) {
    override fun getPrimitiveString(from: String): String = "'$from'"
    override fun encode(value: String) = StringReturn(Box.WithValue(value))
}