package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.Creatable
import uk.gibby.krg.core.Matchable
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.paths.closed.Path10
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation

class MatchablePath10<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>, F: Relation<E, G, *>, G: Node<*>, H: Relation<G, I, *>, I: Node<*>, J: Relation<I, K, *>, K: Node<*>, L: Relation<K, M, *>, M: Node<*>, N: Relation<M, O, *>, O: Node<*>, P: Relation<O, Q, *>, Q: Node<*>, R: Relation<Q, S, *>, S: Node<*>>(internal val first: Searchable<A>, internal val firstToSecond: RelationParamMap<B>, internal val second: Searchable<C>, internal val secondToThird: RelationParamMap<D>, internal val third: Searchable<E>, internal val thirdToFourth: RelationParamMap<F>, internal val fourth: Searchable<G>, internal val fourthToFifth: RelationParamMap<H>, internal val fifth: Searchable<I>, internal val fifthToSixth: RelationParamMap<J>, internal val sixth: Searchable<K>, internal val sixthToSeventh: RelationParamMap<L>, internal val seventh: Searchable<M>, internal val seventhToEighth: RelationParamMap<N>, internal val eighth: Searchable<O>, internal val eighthToNinth: RelationParamMap<P>, internal val ninth: Searchable<Q>, internal val ninthToTenth: RelationParamMap<R>, internal val tenth: Searchable<S>, override val ref: String): Matchable<Path10<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S>>,
    Creatable<Path10<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S>> {
    override fun getReference(): Path10<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S> =
        Path10(
            first.getReference(),
            firstToSecond.getReference(),
            second.getReference(),
            secondToThird.getReference(),
            third.getReference(),
            thirdToFourth.getReference(),
            fourth.getReference(),
            fourthToFifth.getReference(),
            fifth.getReference(),
            fifthToSixth.getReference(),
            sixth.getReference(),
            sixthToSeventh.getReference(),
            seventh.getReference(),
            seventhToEighth.getReference(),
            eighth.getReference(),
            eighthToNinth.getReference(),
            ninth.getReference(),
            ninthToTenth.getReference(),
            tenth.getReference(),
            ref
        )

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}-${secondToThird.getMatchString()}->${third.getSearchString()}-${thirdToFourth.getMatchString()}->${fourth.getSearchString()}-${fourthToFifth.getMatchString()}->${fifth.getSearchString()}-${fifthToSixth.getMatchString()}->${sixth.getSearchString()}-${sixthToSeventh.getMatchString()}->${seventh.getSearchString()}-${seventhToEighth.getMatchString()}->${eighth.getSearchString()}-${eighthToNinth.getMatchString()}->${ninth.getSearchString()}-${ninthToTenth.getMatchString()}->${tenth.getSearchString()}"    }
}