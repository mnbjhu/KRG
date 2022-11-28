package uk.gibby.krg.returns.graph.entities

import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.invoke
import uk.gibby.krg.paths.open.OpenPath2
import uk.gibby.krg.returns.util.NodeReference
import kotlin.reflect.KFunction

abstract class Node<T>: Entity<T>(){
    companion object{
        inline operator fun <reified A: Node<*>, reified B: Relation<A, C, *>, C: Node<*>>A.minus(relation: RelationParamMap<B>) =
            OpenPath2(NodeReference(this), relation)

        inline operator fun <reified A: Node<*>, reified B: Relation<A, C, *>, C: Node<*>>A.minus(relation: KFunction<B>): OpenPath2<A, B, C> = this - relation{}
    }
}