package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.*
import uk.gibby.krg.paths.closed.Path9
import uk.gibby.krg.paths.open.OpenPath10
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.reflect.KFunction

class MatchablePath9<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>, F: Relation<E, G, *>, G: Node<*>, H: Relation<G, I, *>, I: Node<*>, J: Relation<I, K, *>, K: Node<*>, L: Relation<K, M, *>, M: Node<*>, N: Relation<M, O, *>, O: Node<*>, P: Relation<O, Q, *>, Q: Node<*>>(internal val first: Searchable<A>, internal val firstToSecond: RelationParamMap<B>, internal val second: Searchable<C>, internal val secondToThird: RelationParamMap<D>, internal val third: Searchable<E>, internal val thirdToFourth: RelationParamMap<F>, internal val fourth: Searchable<G>, internal val fourthToFifth: RelationParamMap<H>, internal val fifth: Searchable<I>, internal val fifthToSixth: RelationParamMap<J>, internal val sixth: Searchable<K>, internal val sixthToSeventh: RelationParamMap<L>, internal val seventh: Searchable<M>, internal val seventhToEighth: RelationParamMap<N>, internal val eighth: Searchable<O>, internal val eighthToNinth: RelationParamMap<P>, internal val ninth: Searchable<Q>, override val ref: String): Matchable<Path9<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q>>,
    Creatable<Path9<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q>> {
    override fun getReference(): Path9<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q> =
        Path9(
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
            ref
        )

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}-${secondToThird.getMatchString()}->${third.getSearchString()}-${thirdToFourth.getMatchString()}->${fourth.getSearchString()}-${fourthToFifth.getMatchString()}->${fifth.getSearchString()}-${fifthToSixth.getMatchString()}->${sixth.getSearchString()}-${sixthToSeventh.getMatchString()}->${seventh.getSearchString()}-${seventhToEighth.getMatchString()}->${eighth.getSearchString()}-${eighthToNinth.getMatchString()}->${ninth.getSearchString()}"    }
    operator fun <NEW_R: Relation<Q, NEW_N, *>, NEW_N: Node<*>, T: RelationParamMap<NEW_R>>minus(relation: T) = OpenPath10(this, relation)
    operator fun <NEW_R: Relation<Q, NEW_N, *>, NEW_N: Node<*>>minus(relation: KFunction<NEW_R>) = this - relation{}
    }