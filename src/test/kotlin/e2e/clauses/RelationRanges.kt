package e2e.clauses

import e2e.schemas.FriendsWith
import e2e.schemas.UserNode
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Match.Companion.match
import uk.gibby.krg.core.NodeParamMap.Companion.minus
import uk.gibby.krg.core.STAR
import util.GraphTest
import uk.gibby.krg.core.invoke
import uk.gibby.krg.returns.graph.entities.Node.Companion.minus

class RelationRanges: GraphTest() {
    @BeforeEach
    fun setup(){
        graph.query {
            val alice = create(::UserNode{ it[::firstName] = "Alice"; it[::surname] = "Williams"; it[::password] = "Password123"})
            val bob = create(::UserNode{ it[::firstName] = "Bob"; it[::surname] = "Johnson"; it[::password] = "Password123"})
            val charlie = create(::UserNode{ it[::firstName] = "Charlie"; it[::surname] = "Smith"; it[::password] = "Password123"})
            val dan = create(::UserNode{ it[::firstName] = "Dan"; it[::surname] = "Jones"; it[::password] = "Password123"})
            create(alice - ::FriendsWith{ it[::related] = false } - bob)
            create(bob - ::FriendsWith{ it[::related] = false } - charlie)
            create(charlie - ::FriendsWith{ it[::related] = false } - dan)
        }
    }

    @Test
    fun getFriends(){
        graph.query {
            val (first) = match(::UserNode - ::FriendsWith - ::UserNode)
            first.firstName
        }.size `should be equal to` 3
    }

    @Test
    fun getFriendsOfFriendsAndFriends(){
        graph.query {
            val (first) = match(::UserNode - (::FriendsWith)(1..2) - ::UserNode)
            first.firstName
        }.size `should be equal to` 5
    }

    @Test
    fun getAll(){
        graph.query {
            val (first) = match(::UserNode - (::FriendsWith)(STAR) - ::UserNode)
            first.firstName
        }.size `should be equal to` 6
    }
}