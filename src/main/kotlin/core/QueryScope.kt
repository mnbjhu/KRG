package core

import statements.Statement

class QueryScope {
    private val statements = mutableListOf<Statement>()
    fun addStatement(statement: Statement){
        println(statement.getString())
        statements.add(statement)
    }

    fun getString() =statements.joinToString(" ")

}