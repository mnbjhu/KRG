package returns
sealed class ReturnValueType{
    object Instance: ReturnValueType()
    object ParserOnly: ReturnValueType()
    class Reference(val ref: String): ReturnValueType()
}