package statements

import core.QueryScope
import returns.EmptyReturn
import returns.ReturnValue
import returns.createInstance

class Set(private val map: SetMap): Statement() {
    override fun getString(): String {
        return map.params.entries.joinToString(prefix = "SET ") { "${it.key.getString()} = ${it.value.getString()}" }
    }
    companion object{
        fun QueryScope.set(setScope: SetMap.() -> Unit): EmptyReturn{
            val map = SetMap()
            map.setScope()
            addStatement(Set(map))
            return EmptyReturn
        }
    }

}
class SetMap(internal val params: MutableMap<ReturnValue<*>, ReturnValue<*>> = mutableMapOf()){
    infix fun <T, U: ReturnValue<T>>U.to(value: T){
        params[this] = encode(value)
    }
    infix fun <T, U: ReturnValue<T>>U.to(value: U){
        params[this] = value
    }

}