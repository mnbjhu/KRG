package uk.gibby.krg.functions.conditions.primitive

import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.primitives.BooleanReturn

infix fun <T, U: ReturnValue<T>>U.eq(other: U) = ReturnValue.createReference(::BooleanReturn, "(${getString()} = ${other.getString()})")
infix fun <T, U: ReturnValue<T>>U.eq(other: T) = ReturnValue.createReference(::BooleanReturn, "(${getString()} = ${encode(other).getString()})")