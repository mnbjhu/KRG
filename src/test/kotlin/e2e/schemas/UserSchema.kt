package e2e.schemas

import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.UnitRelation
import uk.gibby.krg.returns.primitives.BooleanReturn
import uk.gibby.krg.returns.primitives.StringReturn
import uk.gibby.krg.returns.util.ReturnScope

class UserNode(
    val firstName: StringReturn,
    val surname: StringReturn,
    val password: StringReturn,
): Node<User>() {
    override fun ReturnScope.decode() = User(
        ::firstName.result(),
        ::surname.result(),
        ::password.result()
    )
}

data class User(val firstName: String, val surname: String, val password: String)
class FriendsWith(val related: BooleanReturn): UnitRelation<UserNode, UserNode>()
