package uk.gibby.krg.paths.open

import uk.gibby.krg.core.NodeParamMap
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.core.invoke
import uk.gibby.krg.paths.matchable.MatchablePath3
import uk.gibby.krg.paths.matchable.MatchablePath4
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import uk.gibby.krg.returns.util.NodeReference
import kotlin.reflect.KFunction

class OpenPath4<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>, F: Relation<E, G, *>, G: Node<*>>(private val prev: MatchablePath3<A, B, C, D, E>, internal val thirdToFourth: RelationParamMap<F>){operator fun minus(node: NodeParamMap<G>) =
    MatchablePath4(
        prev.first,
        prev.firstToSecond,
        prev.second,
        prev.secondToThird,
        prev.third,
        thirdToFourth,
        node,
        prev.ref
    )
    operator fun minus(nodeRef: C) =
        MatchablePath4(
            prev.first,
            prev.firstToSecond,
            prev.second,
            prev.secondToThird,
            prev.third,
            thirdToFourth,
            NodeReference(nodeRef),
            prev.ref
        )
    operator fun minus(nodeType: KFunction<C>) =
        MatchablePath4(
            prev.first,
            prev.firstToSecond,
            prev.second,
            prev.secondToThird,
            prev.third,
            thirdToFourth,
            NodeParamMap(nodeType.returnType),
            prev.ref
        )
}