package paths.matchable

import core.RelationParamMap
import paths.closed.Path2
import returns.Node
import returns.Relation
import statements.Creatable
import statements.Matchable
import statements.Searchable

class MatchablePath2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(
    private val first: Searchable<A>,
    private val firstToSecond: RelationParamMap<B>,
    private val second: Searchable<C>, override val ref: String
): Matchable<Path2<A, B, C>>, Creatable<Path2<A, B, C>> {
    override fun getReference(): Path2<A, B, C> =
        Path2(first.getReference(), firstToSecond.getReference(), second.getReference())

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}"
    }
}