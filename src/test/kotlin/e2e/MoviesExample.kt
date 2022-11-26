import core.RedisGraph
import core.invoke
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain`
import org.junit.jupiter.api.Test
import returns.*
import statements.Creatable.Companion.create
import statements.Delete.Companion.delete
import statements.Matchable.Companion.match
import kotlin.concurrent.timer


class Actor(val name: StringReturn): UnitNode()
class ActedIn(val role: StringReturn): UnitRelation<Actor, Movie>()
class Movie(
    val title: StringReturn,
    val releaseYear: LongReturn
): UnitNode()


class MoviesExample {
    private val moviesGraph = RedisGraph(
        name = "movies",
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
    }

}
