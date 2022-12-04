package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.*
import uk.gibby.krg.paths.closed.Path2
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation

class MatchablePath2<out A: Node<*>, out B: Relation<A, C, *>, out C: Node<*>>(
    private val first: Searchable<A>,
    private val firstToSecond: RelationParamMap<B>,
    private val second: Searchable<C>,
    override val ref: String,
): Matchable<Path2<A, B, C>>, Creatable<Path2<A, B, C>> {
    override fun getReference(): Path2<A, B, C> =
        Path2(first.getReference(), firstToSecond.getReference(), second.getReference(), ref)

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}"
    }
}