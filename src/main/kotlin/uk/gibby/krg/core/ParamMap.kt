package uk.gibby.krg.core


import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.ReturnValue.Companion.createDummy
import uk.gibby.krg.returns.ReturnValue.Companion.createInstance
import uk.gibby.krg.returns.graph.entities.Entity
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.full.createType



sealed class ParamMap<U: Entity<*>>(protected val type: KType){
    val entries: MutableList<Pair<String, ReturnValue<*>>> = mutableListOf()
    operator fun <U: ReturnValue<*>>set(attribute: KProperty<U>, value: U){
        entries.add(attribute.name to value)
    }
    inline operator fun <T, reified U: ReturnValue<T>>set(attribute: KProperty<U>, value: T){
        entries.add(attribute.name to createInstance(attribute.returnType, value))
    }

}

inline operator fun <reified U: Node<*>> KFunction<U>.invoke(nodeBuilder: U.(NodeParamMap<U>) -> Unit): NodeParamMap<U> {
    val type = U::class.createType()
    val dummy = createDummy(type) as U
    return with(dummy){ NodeParamMap<U>(type).also { nodeBuilder(it) } }
}
inline operator fun <reified U: Relation<*, *, *>> KFunction<U>.invoke(relationBuilder: U.(RelationParamMap<U>) -> Unit): RelationParamMap<U> {
    val type = U::class.createType()
    val dummy = createDummy(type) as U
    return with(dummy){ RelationParamMap<U>(type).also { relationBuilder(it) } }
}
