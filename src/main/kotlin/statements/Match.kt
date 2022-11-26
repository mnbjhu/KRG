package statements

import core.QueryScope

interface Matchable<T>: Searchable<T>{
    companion object{
        fun <T, U : Matchable<T>>QueryScope.match(matchable: U): T{
            addStatement(Match(matchable))
            return matchable.getReference()
        }
        fun <T, U : Matchable<T>, A, B : Matchable<A>>QueryScope.match(first: U, second: B): Pair<T, A>{
            addStatement(Match(first, second))
            return first.getReference() to second.getReference()
        }

    }
}
interface Searchable<T>{
    val ref: String
    fun getReference(): T
    fun getSearchString(): String

}
class Match(private vararg val matchable: Matchable<*>): Statement(){
    override fun getString() = "MATCH ${matchable.joinToString { it.getSearchString() }}"
}