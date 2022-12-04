package uk.gibby.krg.functions.conditions.primitive.double_return

import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.primitives.DoubleReturn
import uk.gibby.krg.returns.primitives.LongReturn

fun avg(number: DoubleReturn) = ReturnValue.createReference(::DoubleReturn, "avg(${number.getString()})")

fun min(number: DoubleReturn) = ReturnValue.createReference(::DoubleReturn, "min(${number.getString()})")

fun max(number: DoubleReturn) = ReturnValue.createReference(::DoubleReturn, "max(${number.getString()})")

