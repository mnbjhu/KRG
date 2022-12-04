package uk.gibby.krg.core

interface Searchable<out T>{
    val ref: String
    fun getReference(): T
    fun getSearchString(): String

}