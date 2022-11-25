package statements

import core.QueryScope
import returns.EmptyReturn
import returns.Entity

class Delete(private val toDelete: List<Entity<*>>): Statement() {
    override fun getString(): String {
        return "DELETE ${toDelete.joinToString { it.getString() }}"
    }
    companion object{
        fun QueryScope.delete(vararg toDelete: Entity<*>) = EmptyReturn.also { addStatement(Delete(toDelete.toList())) }
    }
}