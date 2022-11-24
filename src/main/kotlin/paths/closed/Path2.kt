package paths.closed

import core.NodeParamMap
import core.RelationParamMap
import returns.Node
import returns.Relation
import statements.Matchable
import statements.Searchable

data class Path2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(val first: A, val firstToSecond: B, val second: C)

class MatchablePath2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(
    private val first: Searchable<A>,
    private val firstToSecond: RelationParamMap<B>,
    private val second: Searchable<C>, override val ref: String
): Matchable<Path2<A, B, C>> {
    override fun getReference(): Path2<A, B, C> = Path2(first.getReference(), firstToSecond.getReference(), second.getReference())

    override fun getSearchString(): String {
        return "${first.getSearchString()}-${firstToSecond.getMatchString()}->${second.getSearchString()}"
    }
}
