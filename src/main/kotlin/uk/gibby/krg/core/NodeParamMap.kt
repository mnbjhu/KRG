package uk.gibby.krg.core


import uk.gibby.krg.paths.open.OpenPath2
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.ReturnValue.Companion.createReference
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.Relation
import uk.gibby.krg.returns.graph.entities.UnitNode
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.full.superclasses


class NodeParamMap<U: Node<*>>(
    private val refType: KType,
    override val ref: String = NameCounter.next()
): ParamMap<U>(refType), Matchable<U>, Creatable<U> {
    override fun getSearchString(): String {
        val paramString = if(entries.isEmpty()) "" else "{${entries.joinToString { "${it.first}:${it.second.getString()}" }}}"
        val labels = listOf(type.classifier as KClass<*>) + (type.classifier as KClass<*>).superclasses
            .takeWhile { it != Node::class && it != UnitNode::class }
            .map { it.qualifiedName!!.replace(".", "_") }
        return "($ref:${labels.joinToString(":")}$paramString)"
    }
    operator fun <B: Relation<U, C, *>, C: Node<*>, T: RelationParamMap<B>>minus(relation: T): OpenPath2<U, B, C> {
        return OpenPath2(this, relation)
    }
    inline operator fun <reified B: Relation<U, C, *>, C: Node<*>>minus(relation: KFunction<B>): OpenPath2<U, B, C> = minus(relation{})
    override fun getReference(): U {
        return createReference(refType, ref) as U
    }
    companion object{
        inline operator fun <reified U: Node<*>, reified B: Relation<U, C, *>,C: Node<*>>
                KFunction<U>.minus(relation: KFunction<B>) = this{} - relation{}
        inline operator fun <reified U: Node<*>, reified B: Relation<U, C, *>,C: Node<*>>
                KFunction<U>.minus(relation: RelationParamMap<B>) = this{} - relation
    }
}