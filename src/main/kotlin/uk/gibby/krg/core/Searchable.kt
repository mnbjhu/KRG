package uk.gibby.krg.core

interface Searchable<T>{
    val ref: String
    fun getReference(): T
    fun getSearchString(): String

}