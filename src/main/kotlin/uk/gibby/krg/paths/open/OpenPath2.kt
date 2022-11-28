package uk.gibby.krg.paths.open

import uk.gibby.krg.core.NameCounter
import uk.gibby.krg.core.NodeParamMap
import uk.gibby.krg.core.RelationParamMap
import uk.gibby.krg.core.Searchable
import uk.gibby.krg.paths.matchable.MatchablePath2
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import uk.gibby.krg.returns.util.NodeReference
import kotlin.reflect.KFunction

class OpenPath2<A: Node<*>, B: Relation<A, C, *>, C: Node<*>>(
    private val first: Searchable<A>,
    private val firstToSecond: RelationParamMap<B>
){
    operator fun minus(node: NodeParamMap<C>) =
        MatchablePath2(first, firstToSecond, node, NameCounter.next())
    operator fun minus(nodeRef: C) =
        MatchablePath2(first, firstToSecond, NodeReference(nodeRef), NameCounter.next())
    operator fun minus(nodeType: KFunction<C>) =
        MatchablePath2(first, firstToSecond, NodeParamMap(nodeType.returnType), NameCounter.next())
}