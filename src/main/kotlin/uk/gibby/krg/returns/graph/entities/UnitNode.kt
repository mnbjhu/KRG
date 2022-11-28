package uk.gibby.krg.returns.graph.entities


import uk.gibby.krg.returns.util.ReturnScope

abstract class UnitNode: Node<Unit>() {
    override fun ReturnScope.decode() = Unit
}