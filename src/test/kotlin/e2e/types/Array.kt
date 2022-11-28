package e2e.types

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Match.Companion.match
import uk.gibby.krg.core.array
import uk.gibby.krg.core.invoke
import uk.gibby.krg.core.nullable
import uk.gibby.krg.core.of
import uk.gibby.krg.returns.generic.ArrayReturn
import uk.gibby.krg.returns.graph.entities.UnitNode
import uk.gibby.krg.returns.primitives.StringReturn
import util.GraphTest

class Array: GraphTest() {
    class ArrayNode(val innerArray: ArrayReturn<String, StringReturn>): UnitNode()
    @Test
    fun createLiteral(){
        graph.query { array(::StringReturn) of listOf("Test1", "Test2", "Test3") } `should be equal to`
                listOf(listOf("Test1", "Test2", "Test3"))
    }

    @Test
    fun createNullLiteral(){
        graph.query { nullable(array(::StringReturn)) of null } `should be equal to` listOf(null)
    }

    @Test
    fun createNullableArrayLiteral(){
        graph.query { array(nullable(::StringReturn)) of listOf(null, "Something") } `should be equal to` listOf(listOf(null, "Something"))
    }

    @Test
    fun createAttribute(){
        graph.query {
            val newNode = create(::ArrayNode{ it[::innerArray] = listOf("Test1", "Test2", "Test3") })
            newNode.innerArray
        } `should be equal to` listOf(listOf("Test1", "Test2", "Test3"))
    }

    @Test
    fun matchAttribute(){
        createAttribute()
        graph.query {
            val newNode = match(::ArrayNode{ it[::innerArray] = listOf("Test1", "Test2", "Test3") })
            newNode.innerArray
        } `should be equal to` listOf(listOf("Test1", "Test2", "Test3"))
    }

    @Test
    fun testEdgeCases(){
        val edgeCases = listOf("null", "NULL", "String with 'quotes'", "String with \\'escaped quotes\\'")
        edgeCases.forEach {
            graph.query { nullable(::StringReturn) of it } `should be equal to` listOf(it)
        }
    }
}