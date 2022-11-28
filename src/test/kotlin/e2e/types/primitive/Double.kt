package e2e.types.primitive


import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Match.Companion.match
import uk.gibby.krg.core.invoke
import uk.gibby.krg.core.nullable
import uk.gibby.krg.core.of
import uk.gibby.krg.returns.graph.entities.UnitNode
import uk.gibby.krg.returns.primitives.DoubleReturn
import util.GraphTest

class Double: GraphTest() {
    class DoubleNode(val innerDouble: DoubleReturn): UnitNode()
    @Test
    fun createLiteral(){
        graph.query { ::DoubleReturn of 23.0 } `should be equal to` listOf(23.0)
    }

    @Test
    fun createNullLiteral(){
        graph.query { nullable(::DoubleReturn) of null } `should be equal to` listOf(null)
    }

    @Test
    fun createAttribute(){
        graph.query {
            val newNode = create(::DoubleNode{ it[::innerDouble] = 23.0 })
            newNode.innerDouble
        } `should be equal to` listOf(23.0)
    }

    @Test
    fun matchAttribute(){
        createAttribute()
        graph.query {
            val newNode = match(::DoubleNode{ it[::innerDouble] = 23.0 })
            newNode.innerDouble
        } `should be equal to` listOf(23.0)
    }
}