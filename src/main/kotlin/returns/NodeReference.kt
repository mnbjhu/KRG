package returns

import statements.Searchable

class NodeReference<U: Node<*>>(private val node: U) : Searchable<U> {
    override val ref: String = node.getString()
    override fun getReference(): U {
        return node
    }

    override fun getSearchString(): String {
        return "(${node.getString()})"
    }
}