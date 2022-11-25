package paths.open

import core.NameCounter
import core.NodeParamMap
import core.RelationParamMap
import paths.closed.MatchablePath2
import returns.Node
import returns.util.NodeReference
import returns.Relation
import statements.Searchable
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