package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.*
import uk.gibby.krg.paths.closed.Path3
import uk.gibby.krg.paths.open.OpenPath4
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.invoke
import kotlin.reflect.KFunction

class MatchablePath3<A: Node<*>, B: Relation<A, C, *>, C: Node<*>, D: Relation<C, E, *>, E: Node<*>>(internal val first: Searchable<A>, internal val firstToSecond: RelationParamMap<B>, internal val second: Searchable<C>, internal val secondToThird: RelationParamMap<D>, internal val third: Searchable<E>, override val ref: String): Matchable<Path3<A, B, C, D, E>>, Creatable<Path3<A, B, C, D, E>>{
    override fun getReference(): Path3<A, B, C, D, E> =
        Path3(first.getReference(), firstToSecond.getReference(), second.getReference(), secondToThird.getReference(), third.getReference(), ref)

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}-${secondToThird.getMatchString()}->${third.getSearchString()}"
    }
    operator fun <NEW_R: Relation<E, NEW_N, *>, NEW_N: Node<*>, T: RelationParamMap<NEW_R>>minus(relation: T) = OpenPath4(this, relation)
    operator fun <NEW_R: Relation<E, NEW_N, *>, NEW_N: Node<*>>minus(relation: KFunction<NEW_R>) = this - relation{}
}