package statements

import core.QueryScope

interface Matchable<T>: Searchable<T>{
    companion object{
        fun <T, U : Matchable<T>>QueryScope.match(matchable: U): T{
            addStatement(Match(matchable))
            return matchable.getReference()
        }
    }
}
interface Searchable<T>{
    val ref: String
    fun getReference(): T
    fun getSearchString(): String

}
class Match(private val matchable: Matchable<*>): Statement(){
    override fun getString() = "MATCH ${matchable.getSearchString()}"
}