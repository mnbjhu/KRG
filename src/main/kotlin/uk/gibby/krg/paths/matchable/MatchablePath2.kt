package uk.gibby.krg.paths.matchable

import uk.gibby.krg.core.Matchable
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.paths.closed.Path2
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation

class MatchablePath2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(
    private val first: Searchable<A>,
    private val firstToSecond: RelationParamMap<B>,
    private val second: Searchable<C>, override val ref: String
): Matchable<Path2<A, B, C>>, uk.gibby.krg.core.Creatable<Path2<A, B, C>> {
    override fun getReference(): Path2<A, B, C> =
        Path2(first.getReference(), firstToSecond.getReference(), second.getReference())

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}"
    }
}