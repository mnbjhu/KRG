package uk.gibby.krg.paths.open

import uk.gibby.krg.core.NodeParamMap
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.paths.matchable.MatchablePath7
import uk.gibby.krg.paths.matchable.MatchablePath8
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import uk.gibby.krg.returns.util.NodeReference
import kotlin.reflect.KFunction

class OpenPath8<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>, F: Relation<E, G, *>, G: Node<*>, H: Relation<G, I, *>, I: Node<*>, J: Relation<I, K, *>, K: Node<*>, L: Relation<K, M, *>, M: Node<*>, N: Relation<M, O, *>, O: Node<*>>(private val prev: MatchablePath7<A, B, C, D, E, F, G, H, I, J, K, L, M>, internal val seventhToEighth: RelationParamMap<N>){operator fun minus(node: NodeParamMap<O>) =
    MatchablePath8(
        prev.first,
        prev.firstToSecond,
        prev.second,
        prev.secondToThird,
        prev.third,
        prev.thirdToFourth,
        prev.fourth,
        prev.fourthToFifth,
        prev.fifth,
        prev.fifthToSixth,
        prev.sixth,
        prev.sixthToSeventh,
        prev.seventh,
        seventhToEighth,
        node,
        prev.ref
    )
    operator fun minus(nodeRef: C) =
        MatchablePath8(
            prev.first,
            prev.firstToSecond,
            prev.second,
            prev.secondToThird,
            prev.third,
            prev.thirdToFourth,
            prev.fourth,
            prev.fourthToFifth,
            prev.fifth,
            prev.fifthToSixth,
            prev.sixth,
            prev.sixthToSeventh,
            prev.seventh,
            seventhToEighth,
            NodeReference(nodeRef),
            prev.ref
        )
    operator fun minus(nodeType: KFunction<C>) =
        MatchablePath8(
            prev.first,
            prev.firstToSecond,
            prev.second,
            prev.secondToThird,
            prev.third,
            prev.thirdToFourth,
            prev.fourth,
            prev.fourthToFifth,
            prev.fifth,
            prev.fifthToSixth,
            prev.sixth,
            prev.sixthToSeventh,
            prev.seventh,
            seventhToEighth,
            NodeParamMap(nodeType.returnType),
            prev.ref
        )
}