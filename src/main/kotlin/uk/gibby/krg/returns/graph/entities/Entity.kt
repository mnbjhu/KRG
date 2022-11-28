package uk.gibby.krg.returns.graph.entities

import uk.gibby.krg.returns.NotNull
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.util.ReturnScope
import kotlin.reflect.KTypeProjection
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

sealed class Entity<T>: NotNull<T>(){
    override fun getStructuredString(): String {
        throw Exception("Only references to entities should be created")
    }

    override fun encode(value: T): ReturnValue<T> {
        throw Exception("Only references to entities should be created")
    }
    override fun parse(value: Any?): T {
        val nodeData = (value as redis.clients.jedis.graph.entities.GraphEntity)
        val map = this::class.memberProperties
            .filter { it.returnType.isSubtypeOf(ReturnValue::class.createType(listOf(KTypeProjection.STAR))) }
            .associate { it.name to nodeData.getProperty(it.name).value }
        return ReturnScope(map).decode()
    }
    abstract fun ReturnScope.decode(): T
}