package returns

import returns.util.ReturnScope

abstract class UnitNode: Node<Unit>() {
    override fun ReturnScope.decode() = Unit
}