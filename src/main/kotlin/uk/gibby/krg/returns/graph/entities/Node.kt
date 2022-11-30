package uk.gibby.krg.returns.graph.entities

import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.invoke
import uk.gibby.krg.paths.open.OpenPath2
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.util.NodeReference
import uk.gibby.krg.returns.util.ReturnScope
import kotlin.reflect.KFunction
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

abstract class Node<T>: Entity<T>(){
    override fun parse(value: Any?): T {
        val nodeData = (value as redis.clients.jedis.graph.entities.Node)
        val lastLabel = nodeData.getLabel(nodeData.numberOfLabels-1)
            .replace("_", ".")
        if(lastLabel != this::class.qualifiedName){
            println("${this::class.qualifiedName}: ${this::class.sealedSubclasses}")
            val type = Class.forName(this::class.qualifiedName).kotlin.createType()
            return (createDummy(type) as Node<*>).parse(value) as T
        }
        val map = this::class.memberProperties
            .filter { it.returnType.isSubtypeOf(ReturnValue::class.createType(listOf(KTypeProjection.STAR))) }
            .associate { it.name to nodeData.getProperty(it.name).value }
        return ReturnScope(map).decode()
    }
    companion object{
        inline operator fun <reified A: Node<*>, reified B: Relation<A, C, *>, C: Node<*>>A.minus(relation: RelationParamMap<B>) =
            OpenPath2(NodeReference(this), relation)

        inline operator fun <reified A: Node<*>, reified B: Relation<A, C, *>, C: Node<*>>A.minus(relation: KFunction<B>): OpenPath2<A, B, C> = this - relation{}
    }
}