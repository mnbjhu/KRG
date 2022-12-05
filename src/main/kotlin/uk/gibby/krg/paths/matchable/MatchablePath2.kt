package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.*
import uk.gibby.krg.paths.closed.*
import uk.gibby.krg.paths.open.OpenPath3
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import kotlin.reflect.KFunction

class MatchablePath2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(
    internal val first: Searchable<A>,
    internal val firstToSecond: RelationParamMap<B>,
    internal val second: Searchable<C>,
    override val ref: String,
): Matchable<Path2<A, B, C>>, Creatable<Path2<A, B, C>> {
    override fun getReference(): Path2<A, B, C> =
        Path2(first.getReference(), firstToSecond.getReference(), second.getReference(), ref)

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}"
    }
    operator fun <NEW_R: Relation<C, NEW_N, *>, NEW_N: Node<*>, T: RelationParamMap<NEW_R>>minus(relation: T) = OpenPath3(this, relation)
    operator fun <NEW_R: Relation<C, NEW_N, *>, NEW_N: Node<*>>minus(relation: KFunction<NEW_R>) = this - relation{}

}

