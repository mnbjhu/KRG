package paths.closed

import returns.Node
import returns.Relation

data class Path2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(val first: A, val firstToSecond: B, val second: C)

