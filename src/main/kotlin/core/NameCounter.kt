package core

object NameCounter {
    private var count = 0
    fun next() = "obj${count++}"
}
