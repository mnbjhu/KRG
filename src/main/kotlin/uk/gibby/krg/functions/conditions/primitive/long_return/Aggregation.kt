package uk.gibby.krg.functions.conditions.primitive.long_return

import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.primitives.DoubleReturn
import uk.gibby.krg.returns.primitives.LongReturn

fun count(item: ReturnValue<*>) = ReturnValue.createReference(::LongReturn, "count(${item.getString()})")

fun avg(number: LongReturn) = ReturnValue.createReference(::DoubleReturn, "avg(${number.getString()})")

fun min(number: LongReturn) = ReturnValue.createReference(::LongReturn, "min(${number.getString()})")

fun max(number: LongReturn) = ReturnValue.createReference(::LongReturn, "max(${number.getString()})")