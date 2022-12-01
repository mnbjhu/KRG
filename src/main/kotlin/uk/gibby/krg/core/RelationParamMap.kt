package uk.gibby.krg.core

import uk.gibby.krg.returns.ReturnValue.Companion.createReference
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.reflect.KClass
import kotlin.reflect.KType

class RelationParamMap<U: Relation<*, *, *>>(
    private val refType: KType,
    private val range: IntRange = 1..1,
    private val ref: String = NameCounter.next()
): ParamMap<U>(refType){
    fun getMatchString(): String {
        val paramString = if(entries.isEmpty()) "" else "{${entries.joinToString { "${it.first}:${it.second.getString()}" }}}"
        val className = (type.classifier as KClass<*>).simpleName
        val rangeString = when(range){
            1..1 -> ""
            STAR -> "*"
            else -> "*$range"
        }
        return "[$ref:$className$rangeString$paramString]"
    }
    fun getReference(): U {
        return createReference(refType, ref) as U
    }
}
val STAR = 1..Int.MAX_VALUE