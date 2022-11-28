package e2e.clauses

import e2e.schemas.FriendsWith
import e2e.schemas.UserNode
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Delete.Companion.delete
import uk.gibby.krg.clauses.Match.Companion.match
import uk.gibby.krg.core.NodeParamMap.Companion.minus
import uk.gibby.krg.core.invoke
import uk.gibby.krg.returns.graph.entities.Node.Companion.minus
import util.GraphTest

class Delete: GraphTest() {

    @BeforeEach
    fun setup(){
        graph.query {
            val alice = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "Williams"; it[::password] = "Password123"})
            val bob = create(::UserNode{ it[::firstName] = "Bob"; it[::surname] = "Johnson"; it[::password] = "Password123"})
            create(alice - ::FriendsWith{ it[::related] = false } - bob)
        }
    }

    @Test
    fun deleteNode(){
        graph.query {
            val bob = match(::UserNode{it[::firstName] = "Bob"})
            delete(bob)
        }
        graph.query { match(::UserNode{it[::firstName] = "Bob"}) }`should be equal to` listOf()
    }
    @Test
    fun deleteRelation(){
        graph.query {
            val (_, relation) = match(::UserNode - ::FriendsWith - ::UserNode)
            delete(relation)
        }
        graph.query {
            val (user) = match(::UserNode - ::FriendsWith - ::UserNode)
            user.firstName
        }`should be equal to` listOf()
    }
}