package returns

object EmptyReturnInstance: EmptyReturn()
abstract class EmptyReturn: ReturnValue<Unit>() {
    override fun getStructuredString(): String {
        throw Exception("Return is empty")
    }

    override fun parse(value: Any?) {
        throw Exception("Return is empty")
    }

    override fun encode(value: Unit): ReturnValue<Unit> {
        throw Exception("Return is empty")
    }
}