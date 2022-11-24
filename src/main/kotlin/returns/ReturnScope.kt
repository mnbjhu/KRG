package returns

class ReturnScope(private val map: Map<ReturnValue<*>, *>) {
    fun <T, U: ReturnValue<T>>U.result(): T{
        return map[this] as T
    }
}