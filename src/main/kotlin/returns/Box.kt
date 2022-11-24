package returns

sealed class Box<out T>{
    class WithValue<T>(val value: T): Box<T>()
    object WithoutValue: Box<Nothing>()
}