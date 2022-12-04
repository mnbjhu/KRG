package uk.gibby.krg.functions.conditions.primitive.boolean_return

import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.primitives.BooleanReturn

operator fun BooleanReturn.not() = ReturnValue.createReference(::BooleanReturn, "(NOT ${getString()})")
infix fun BooleanReturn.and(other: BooleanReturn) = ReturnValue.createReference(::BooleanReturn, "(${getString()} AND ${other.getString()})")
infix fun BooleanReturn.or(other: BooleanReturn) = ReturnValue.createReference(::BooleanReturn, "(${getString()} OR ${other.getString()})")
infix fun BooleanReturn.xor(other: BooleanReturn) = ReturnValue.createReference(::BooleanReturn, "(${getString()} XOR ${other.getString()})")