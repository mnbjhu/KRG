package statements

import core.QueryScope

interface Creatable<T>: Searchable<T>{
    companion object{
        fun <T, U : Creatable<T>>QueryScope.create(creatable: U): T{
            addStatement(Create(creatable))
            return creatable.getReference()
        }
    }
}
class Create(private val creatable: Creatable<*>): Statement(){
    override fun getString() = "CREATE ${creatable.getSearchString()}"
}

