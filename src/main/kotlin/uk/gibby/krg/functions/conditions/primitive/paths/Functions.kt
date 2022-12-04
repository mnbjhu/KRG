package uk.gibby.krg.functions.conditions.primitive.paths

import uk.gibby.krg.paths.closed.Path2
import uk.gibby.krg.paths.open.OpenPath2
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.generic.ArrayReturn
import uk.gibby.krg.returns.graph.entities.Node

fun <T, U: Node<T>>nodes(path2: Path2<U, *, U>) = ReturnValue.createReference(path2.getNodeType(), "nodes(${path2.getString()})") as ArrayReturn<T, U>