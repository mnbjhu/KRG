package uk.gibby.krg.clauses

import uk.gibby.krg.core.QueryScope
import uk.gibby.krg.returns.empty.EmptyReturn
import uk.gibby.krg.returns.empty.EmptyReturnInstance
import uk.gibby.krg.returns.primitives.BooleanReturn

class Where(private val predicate: BooleanReturn): Claus() {
    override fun getString(): String {
        return "WHERE (${predicate.getString()})"
    }
    companion object{
        infix fun QueryScope.where(predicate: BooleanReturn) = EmptyReturnInstance.also { addStatement(Where(predicate)) }
    }
}