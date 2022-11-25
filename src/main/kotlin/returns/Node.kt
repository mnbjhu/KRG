package returns

import core.RelationParamMap
import core.invoke
import paths.open.OpenPath2
import returns.util.NodeReference
import kotlin.reflect.KFunction

abstract class Node<T>: Entity<T>(){
    companion object{
        inline operator fun <reified A: Node<*>, reified B: Relation<A, C, *>, C: Node<*>>A.minus(relation: RelationParamMap<B>) =
            OpenPath2(NodeReference(this), relation)

        inline operator fun <reified A: Node<*>, reified B: Relation<A, C, *>, C: Node<*>>A.minus(relation: KFunction<B>): OpenPath2<A, B, C> = this - relation.invoke {  }
    }
}