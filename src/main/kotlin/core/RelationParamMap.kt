package core

import returns.Relation
import returns.ReturnValue.Companion.createReference
import kotlin.reflect.KClass
import kotlin.reflect.KType

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