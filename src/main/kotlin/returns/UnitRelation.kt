package returns

import returns.util.ReturnScope

abstract class UnitRelation<A: Node<*>, B: Node<*>>: Relation<A, B, Unit>() {
    override fun ReturnScope.decode() = Unit
}