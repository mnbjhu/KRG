package uk.gibby.krg.returns.util

import uk.gibby.krg.core.Searchable
import uk.gibby.krg.returns.graph.entities.Node

class NodeReference<U: Node<*>>(private val node: U) : Searchable<U> {
    override val ref: String = node.getString()
    override fun getReference(): U {
        return node
    }

    override fun getSearchString(): String {
        return "(${node.getString()})"
    }
}