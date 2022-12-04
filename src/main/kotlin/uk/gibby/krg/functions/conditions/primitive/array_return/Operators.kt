package uk.gibby.krg.functions.conditions.primitive.array_return

import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.generic.ArrayReturn
import uk.gibby.krg.returns.primitives.BooleanReturn

infix fun <T, U: ReturnValue<T>>ArrayReturn<T, U>.contains(element: U) = ReturnValue.createReference(::BooleanReturn, "(${element.getString()} IN ${getString()})")
infix fun <T, U: ReturnValue<T>>ArrayReturn<T, U>.contains(element: T) = ReturnValue.createReference(::BooleanReturn, "($element IN ${getString()})")