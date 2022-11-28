package uk.gibby.krg.paths.closed

import uk.gibby.krg.returns.empty.EmptyReturn
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation

data class Path2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(val first: A, val firstToSecond: B, val second: C): EmptyReturn()

