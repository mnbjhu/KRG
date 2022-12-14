package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.*
import uk.gibby.krg.paths.closed.Path7
import uk.gibby.krg.paths.open.OpenPath7
import uk.gibby.krg.paths.open.OpenPath8
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.reflect.KFunction

class MatchablePath7<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>, F: Relation<E, G, *>, G: Node<*>, H: Relation<G, I, *>, I: Node<*>, J: Relation<I, K, *>, K: Node<*>, L: Relation<K, M, *>, M: Node<*>>(internal val first: Searchable<A>, internal val firstToSecond: RelationParamMap<B>, internal val second: Searchable<C>, internal val secondToThird: RelationParamMap<D>, internal val third: Searchable<E>, internal val thirdToFourth: RelationParamMap<F>, internal val fourth: Searchable<G>, internal val fourthToFifth: RelationParamMap<H>, internal val fifth: Searchable<I>, internal val fifthToSixth: RelationParamMap<J>, internal val sixth: Searchable<K>, internal val sixthToSeventh: RelationParamMap<L>, internal val seventh: Searchable<M>, override val ref: String): Matchable<Path7<A, B, C, D, E, F, G, H, I, J, K, L, M>>,
    Creatable<Path7<A, B, C, D, E, F, G, H, I, J, K, L, M>> {
    override fun getReference(): Path7<A, B, C, D, E, F, G, H, I, J, K, L, M> =
        Path7(
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
            ref
        )

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}-${secondToThird.getMatchString()}->${third.getSearchString()}-${thirdToFourth.getMatchString()}->${fourth.getSearchString()}-${fourthToFifth.getMatchString()}->${fifth.getSearchString()}-${fifthToSixth.getMatchString()}->${sixth.getSearchString()}-${sixthToSeventh.getMatchString()}->${seventh.getSearchString()}"    }
    operator fun <NEW_R: Relation<M, NEW_N, *>, NEW_N: Node<*>, T: RelationParamMap<NEW_R>>minus(relation: T) = OpenPath8(this, relation)
    operator fun <NEW_R: Relation<M, NEW_N, *>, NEW_N: Node<*>>minus(relation: KFunction<NEW_R>) = this - relation{}
    }