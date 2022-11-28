package e2e

import e2e.schemas.ActedIn
import e2e.schemas.Actor
import e2e.schemas.Movie
import uk.gibby.krg.core.NodeParamMap.Companion.minus
import uk.gibby.krg.core.invoke
import org.amshove.kluent.shouldContainSame
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Match.Companion.match
import util.GraphTest


class MoviesExample: GraphTest(){
    @Test
    fun createExample(){
        /**
         * Let's add three nodes that represent actors and then add a node to represent a movie.
         * Note that the graph data structure 'movies' will be automatically created for us as and the nodes are added to it.
         */
        graph.query {
            val (_, _, movie) = create(::Actor{ it[::name] = "Mark Hamill" } - ::ActedIn{ it[::role] = "Luke Skywalker" } -
                    ::Movie{ it[::title] = "Star Wars: Episode V - The Empire Strikes Back"; it[::releaseYear] = 1980 })
            create(::Actor{ it[::name] = "Carrie Fisher" } - ::ActedIn{ it[::role] = "Princess Leia" } - movie)
            create(::Actor{ it[::name] = "Harrison Ford" } -::ActedIn{ it[::role] = "Han Solo" } - movie)
        }
    }
    @Test
    fun matchExample() {
        createExample()
        graph.query {
            val (actor) = match(::Actor - ::ActedIn - ::Movie{it[::title] = "Star Wars: Episode V - The Empire Strikes Back"})
            actor.name
        } shouldContainSame listOf("Mark Hamill", "Carrie Fisher", "Harrison Ford")
    }
}
