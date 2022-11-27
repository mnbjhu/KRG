package core

import returns.NotNull
import returns.Nullable
import returns.ReturnValue
import returns.ReturnValue.Companion.createInstance
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.createType

infix fun <T, U: ReturnValue<T>>KFunction<U>.of(value: T): U = createInstance(this, value)

class TypeProducer<T, U: ReturnValue<T>>(val type: KType)

inline fun <reified T, U: NotNull<T>>nullable(type: KFunction<U>) = TypeProducer<T?, Nullable<T, U>>(Nullable::class.createType(listOf(
    KTypeProjection(KVariance.INVARIANT, T::class.createType()),
    KTypeProjection(KVariance.INVARIANT, type.returnType),
)))

infix fun <T, U: ReturnValue<T>>TypeProducer<T, U>.of(value: T) = createInstance<T, U>(type, value)