package e2e

import e2e.schemas.FriendsWith
import e2e.schemas.UserNode
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.core.invoke
import uk.gibby.krg.returns.graph.entities.Node.Companion.minus
import util.GraphTest

class Paths: GraphTest() {
    @Test
    fun createLongPath(){
        graph.query {
            val alice = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val bob = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val charlie = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val dennis = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val evan = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val fred = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val georgie = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            val helen = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "A"; it[::password] = "Password123"})
            create(alice - ::FriendsWith - bob - ::FriendsWith{} - charlie - ::FriendsWith - dennis)
        }
    }
}