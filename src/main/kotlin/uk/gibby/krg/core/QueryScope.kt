package uk.gibby.krg.core

import uk.gibby.krg.clauses.Claus

class QueryScope {
    private val clauses = mutableListOf<Claus>()
    fun addStatement(statement: Claus){ clauses.add(statement) }

    fun getString() = clauses.joinToString(" "){it.getString()}

}