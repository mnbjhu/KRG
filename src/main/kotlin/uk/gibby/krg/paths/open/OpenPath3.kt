package uk.gibby.krg.paths.open

import uk.gibby.krg.core.NodeParamMap
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.paths.matchable.MatchablePath2
import uk.gibby.krg.paths.matchable.MatchablePath3
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import uk.gibby.krg.returns.util.NodeReference
import kotlin.reflect.KFunction

class OpenPath3<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>>(private val prev: MatchablePath2<A, B, C>, internal val secondToThird: RelationParamMap<D>){operator fun minus(node: NodeParamMap<E>) =
    MatchablePath3(prev.first, prev.firstToSecond, prev.second, secondToThird, node, prev.ref)
    operator fun minus(nodeRef: C) =
        MatchablePath3(prev.first, prev.firstToSecond, prev.second, secondToThird, NodeReference(nodeRef), prev.ref)
    operator fun minus(nodeType: KFunction<C>) =
        MatchablePath3(
            prev.first,
            prev.firstToSecond,
            prev.second,
            secondToThird,
            NodeParamMap(nodeType.returnType),
            prev.ref
        )
}