package uk.gibby.krg.functions.conditions.primitive.array_return

import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.generic.ArrayReturn
import uk.gibby.krg.returns.primitives.BooleanReturn
import uk.gibby.krg.returns.primitives.LongReturn
import java.util.Arrays
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.createType

private val intArrayType = ArrayReturn::class.createType(listOf(
    KTypeProjection(KVariance.INVARIANT, Long::class.createType()), KTypeProjection(
        KVariance.INVARIANT, LongReturn::class.createType())
))

fun <U: ArrayReturn<*, *>>isEmpty(array: U) = ReturnValue.createReference(::BooleanReturn, "isEmpty(${array.getString()})")

fun <U: ArrayReturn<*, *>>reverse(original: U) = ReturnValue.createReference(original.inner, "reverse(${original.getString()})") as U

fun range(start: LongReturn, end: LongReturn, step: Long): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range(${start.getString()},${end.getString()},${step})") as ArrayReturn<Long, LongReturn>
fun range(start: LongReturn, end: Long, step: Long): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range(${start.getString()},$end,$step)") as ArrayReturn<Long, LongReturn>
fun range(start: Long, end: LongReturn, step: Long): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range($start,${end.getString()},$step)") as ArrayReturn<Long, LongReturn>
fun range(start: Long, end: Long, step: Long): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range($start,$end,$step)") as ArrayReturn<Long, LongReturn>
fun range(start: LongReturn, end: LongReturn, step: LongReturn): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range(${start.getString()},${end.getString()},$step)") as ArrayReturn<Long, LongReturn>
fun range(start: LongReturn, end: Long, step: LongReturn): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range(${start.getString()},$end,$step)") as ArrayReturn<Long, LongReturn>
fun range(start: Long, end: LongReturn, step: LongReturn): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range($start,${end.getString()},$step)") as ArrayReturn<Long, LongReturn>
fun range(start: Long, end: Long, step: LongReturn): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range($start,$end,$step)") as ArrayReturn<Long, LongReturn>

fun range(start: LongReturn, end: LongReturn): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range(${start.getString()},${end.getString()})") as ArrayReturn<Long, LongReturn>
fun range(start: LongReturn, end: Long): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range(${start.getString()},$end)") as ArrayReturn<Long, LongReturn>
fun range(start: Long, end: LongReturn): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range($start,${end.getString()})") as ArrayReturn<Long, LongReturn>
fun range(start: Long, end: Long): ArrayReturn<Long, LongReturn> = ReturnValue.createReference(intArrayType, "range($start,$end)") as ArrayReturn<Long, LongReturn>
