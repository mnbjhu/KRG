package statements

import core.QueryScope
import returns.EmptyReturnInstance
import returns.Entity

class Delete(private val toDelete: List<Entity<*>>): Statement() {
    override fun getString(): String {
        return "DELETE ${toDelete.joinToString { it.getString() }}"
    }
    companion object{
        fun QueryScope.delete(vararg toDelete: Entity<*>) = EmptyReturnInstance.also { addStatement(Delete(toDelete.toList())) }
    }
}