package uk.gibby.krg.paths.open

import uk.gibby.krg.core.NodeParamMap
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.core.invoke
import uk.gibby.krg.paths.matchable.MatchablePath8
import uk.gibby.krg.paths.matchable.MatchablePath9
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import uk.gibby.krg.returns.util.NodeReference
import kotlin.reflect.KFunction

class OpenPath9<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>, F: Relation<E, G, *>, G: Node<*>, H: Relation<G, I, *>, I: Node<*>, J: Relation<I, K, *>, K: Node<*>, L: Relation<K, M, *>, M: Node<*>, N: Relation<M, O, *>, O: Node<*>, P: Relation<O, Q, *>, Q: Node<*>>(private val prev: MatchablePath8<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O>, internal val eighthToNinth: RelationParamMap<P>) {
    operator fun minus(node: NodeParamMap<Q>) =
        MatchablePath9(
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
            prev.seventhToEighth,
            prev.eighth,
            eighthToNinth,
            node,
            prev.ref
        )

    operator fun minus(nodeRef: C) =
        MatchablePath9(
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
            prev.seventhToEighth,
            prev.eighth,
            eighthToNinth,
            NodeReference(nodeRef),
            prev.ref
        )

    operator fun minus(nodeType: KFunction<C>) =
        MatchablePath9(
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
            prev.seventhToEighth,
            prev.eighth,
            eighthToNinth,
            NodeParamMap(nodeType.returnType),
            prev.ref
        )
}