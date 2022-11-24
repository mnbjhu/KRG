package core

import paths.open.OpenPath2
import returns.*
import statements.Matchable
import kotlin.reflect.KClass
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

class NodeParamMap<U: Node<*>>(private val refType: KType, override val ref: String = NameCounter.next()): ParamMap<U>(refType), Matchable<U>{
    override fun getSearchString(): String {
        val paramString = if(entries.isEmpty()) "" else "{${entries.joinToString { "${it.first}:${it.second.getString()}" }}}"
        val className = (type.classifier as KClass<*>).simpleName
        return "($ref:$className$paramString)"
    }
    operator fun <B: Relation<U, C, *>, C: Node<*>, T: RelationParamMap<B>>minus(relation: T): OpenPath2<U, B, C>{
        return OpenPath2(this, relation)
    }
    inline operator fun <reified B: Relation<U, C, *>, C: Node<*>, T: RelationParamMap<B>>minus(relation: KFunction<B>): OpenPath2<U, B, C> = minus(relation{})
    override fun getReference(): U {
        return createReference(refType, ref) as U
    }
    companion object{
        inline operator fun <reified U: Node<*>, reified B: Relation<U, C, *>,C: Node<*>>KFunction<U>.minus(relation: KFunction<B>) = this{} - relation{}
        inline operator fun <reified U: Node<*>, reified B: Relation<U, C, *>,C: Node<*>>KFunction<U>.minus(relation: RelationParamMap<B>) = this{} - relation
    }
}
class RelationParamMap<U: Relation<*, *, *>>(private val refType: KType, private val ref: String = NameCounter.next()): ParamMap<U>(refType){
    fun getMatchString(): String {
        val paramString = if(entries.isEmpty()) "" else "{${entries.joinToString { "${it.first}:${it.second.getString()}" }}}"
        val className = (type.classifier as KClass<*>).simpleName
        return "[$ref:$className$paramString]"
    }
    fun getReference(): U {
        return createReference(refType, ref) as U
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
