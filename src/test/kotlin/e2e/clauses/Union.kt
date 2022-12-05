package e2e.clauses

import e2e.schemas.ActedIn
import e2e.schemas.Actor
import e2e.schemas.Movie
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Match.Companion.match
import uk.gibby.krg.clauses.Where.Companion.where
import uk.gibby.krg.core.invoke
import uk.gibby.krg.functions.conditions.primitive.string_return.contains
import util.GraphTest

class Union: GraphTest() {
    @BeforeEach
    fun setupTestData(){
        graph.query {
            val (_, _, movie) = create(::Actor{ it[::name] = "Mark Hamill" } - ::ActedIn{ it[::role] = "Luke Skywalker" } -
                    ::Movie{ it[::title] = "Star Wars: Episode V - The Empire Strikes Back"; it[::releaseYear] = 1980 })
            create(::Actor{ it[::name] = "Carrie Fisher" } - ::ActedIn{ it[::role] = "Princess Leia" } - movie)
            create(::Actor{ it[::name] = "Harrison Ford" } - ::ActedIn{ it[::role] = "Han Solo" } - movie)
        }
    }

    @Test
    fun basicTest(){
        graph.queryUnion(
            { match(::Movie).title },
            { match(::Actor).name }
        ) `should contain same` listOf("Carrie Fisher", "Harrison Ford", "Mark Hamill", "Star Wars: Episode V - The Empire Strikes Back")
    }
}