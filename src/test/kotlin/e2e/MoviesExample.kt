package e2e

import TestAuth
import core.NodeParamMap.Companion.minus
import core.RedisGraph
import core.invoke
import org.amshove.kluent.shouldContainSame
import org.junit.jupiter.api.Test
import returns.*
import statements.Creatable.Companion.create
import statements.Delete.Companion.delete
import statements.Matchable.Companion.match


class Actor(val name: StringReturn): UnitNode()
class ActedIn(val role: StringReturn): UnitRelation<Actor, Movie>()
class Movie(
    val title: StringReturn,
    val releaseYear: LongReturn
): UnitNode()


class MoviesExample {
    private val moviesGraph = RedisGraph(
        name = "movies_new",
        host = TestAuth.host,
        port = TestAuth.port,
        password = TestAuth.password
    )

    //@BeforeEach
    private fun deleteAll() {
        moviesGraph.query {
            val (movie, actor) = match(::Movie{}, ::Actor{})
            delete(movie, actor)
        }
    }

    @Test
    fun `Movie Examples`() {
        /**
         * First, let's delete the movies graph (if exists).
         * Note that the entire graph data is accessible using a single key.
         */
        deleteAll()
        /**
         * Let's add three nodes that represent actors and then add a node to represent a movie.
         * Note that the graph data structure 'movies' will be automatically created for us as and the nodes are added to it.
         */
        moviesGraph.query {
            val (_, _, movie) = create(::Actor{ it[::name] = "Mark Hamill" } - ::ActedIn{ it[::role] = "Luke Skywalker" } -
                    ::Movie{ it[::title] = "Star Wars: Episode V - The Empire Strikes Back"; it[::releaseYear] = 1980 })
            create(::Actor{ it[::name] = "Carrie Fisher" } - ::ActedIn{ it[::role] = "Princess Leia" } - movie)
            create(::Actor{ it[::name] = "Harrison Ford" } -::ActedIn{ it[::role] = "Han Solo" } - movie)
        }

        moviesGraph.query {
            val (actor) = match(::Actor - ::ActedIn - ::Movie{it[::title] = "Star Wars: Episode V - The Empire Strikes Back"})
            actor.name
        } shouldContainSame listOf("Mark Hamill", "Carrie Fisher", "Harrison Ford")
    }
}
