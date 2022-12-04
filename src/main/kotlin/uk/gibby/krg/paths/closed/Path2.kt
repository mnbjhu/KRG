package uk.gibby.krg.paths.closed

import uk.gibby.krg.returns.empty.EmptyReturn
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.reflect.full.createType
import kotlin.reflect.full.superclasses

data class Path2<out A: Node<*>, out B: Relation<A, C, *>, out C: Node<*>>(val first: A, val firstToSecond: B, val second: C, internal val name: String): EmptyReturn(){
    internal fun getNodeType() = first::class.superclasses.first { second::class.superclasses.contains(it) }.createType()
}

