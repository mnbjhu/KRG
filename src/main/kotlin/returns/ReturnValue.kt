package returns

import returns.util.Box
import returns.util.ReturnValueType
import kotlin.reflect.*
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.primaryConstructor


/**
 * Return value
 *
 * A value which can be returned from a graph query: [core.RedisGraph.query].
 * i.e. RETURN myValue
 *
 * Return values should be created as:
 * - A reference
 * - Structured representations
 * @param T Return value type.
 */
sealed class ReturnValue<T>{
    internal var type: ReturnValueType = ReturnValueType.Instance

    /**
     * Get string
     *
     * The string representation of the return value.
     * E.g
     * - obj1.myVar
     * - {myString:'Some String', myInt: 123}
     */
    fun getString() = when(type){
        is ReturnValueType.ParserOnly -> throw Exception("Cannot call getString, instance is parser only")
        is ReturnValueType.Reference -> (type as ReturnValueType.Reference).ref
        is ReturnValueType.Instance -> getStructuredString()
    }

    /**
     * Get structured string
     *
     * e.g. {x: 1.0, y: 2.0}
     * @return The string representation of a structured return value.
     */
    abstract fun getStructuredString(): String

    /**
     * Parse
     *
     * Parses the un-typed data to the returned value
     *
     * @param value Un-typed response data
     * @return decoded return value
     */
    abstract fun parse(value: Any?): T

    /**
     * Encode
     *
     * Creates a structured instance of [value].
     *
     * @param value the value to encode
     * @return [value] as a [ReturnValue]
     */
    abstract fun encode(value: T): ReturnValue<T>
    companion object{
        fun <T, U: ReturnValue<T>, A: ArrayReturn<T, U>>createDummyArray(type: KType): Any{
            return (ArrayReturn<T, U>(Box.WithoutValue, type) as U)
                .apply { this.type = ReturnValueType.ParserOnly }
        }
        fun <T, U: ReturnValue<T>, A: ArrayReturn<T, U>>createReferenceArray(type: KType, ref: String): Any{
            return ArrayReturn<T, U>(Box.WithoutValue, type).apply { this.type = ReturnValueType.Reference(ref) }
        }
        fun createDummy(type: KType): Any{
            return when{
                type.isSubtypeOf(ArrayReturn::class.createType(listOf(KTypeProjection.STAR, KTypeProjection.STAR))) -> {
                    createDummyArray<Any, ReturnValue<Any>, ArrayReturn<Any, ReturnValue<Any>>>(type.arguments[1].type!!)
                }
                type.isSubtypeOf(StructReturn::class.createType(listOf(KTypeProjection.STAR))) -> {
                    val constructor = (type.classifier as KClass<*>).primaryConstructor!!
                    val params = constructor.parameters.associateWith {
                        createDummy(it.type)
                    }
                    (constructor.callBy(params) as ReturnValue<*>).apply { this.type = ReturnValueType.ParserOnly }
                }
                type.isSubtypeOf(Entity::class.createType(listOf(KTypeProjection.STAR))) -> {
                    val constructor = (type.classifier as KClass<*>).primaryConstructor!!
                    val params = constructor.parameters.associateWith {
                        if(!it.type.isSubtypeOf(NotNull::class.createType(listOf(KTypeProjection.STAR))) &&
                            !it.type.isSubtypeOf(Nullable::class.createType(listOf(
                                KTypeProjection.STAR,
                                KTypeProjection(KVariance.OUT, NotNull::class.createType(listOf(KTypeProjection.STAR)))))))
                            throw Exception("Attributes can only be return_types.NotNull or return_types.Nullable<return_types.NotNull>")
                        createDummy(it.type)
                    }
                    (constructor.callBy(params) as ReturnValue<*>).apply { this.type = ReturnValueType.ParserOnly }
                }
                type.isSubtypeOf(Nullable::class.createType(listOf(KTypeProjection.STAR, KTypeProjection.STAR)), ) -> {
                    Nullable<Any, NotNull<Any>>(Box.WithoutValue, type.arguments[1].type!!)
                        .apply { this.type = ReturnValueType.ParserOnly }
                }
                type.isSubtypeOf(PrimitiveReturn::class.createType(listOf(KTypeProjection.STAR))) -> {
                    val constructor = (type.classifier as KClass<*>).primaryConstructor!!
                    (constructor.call(null) as ReturnValue<*>).apply { this.type = ReturnValueType.ParserOnly }
                }
                else -> throw Exception()
            }
        }
        fun createReference(type: KType, ref: String): Any{
            return when{
                type.isSubtypeOf(ArrayReturn::class.createType(listOf(KTypeProjection.STAR, KTypeProjection.STAR))) -> {
                    createReferenceArray<Any, ReturnValue<Any>, ArrayReturn<Any, ReturnValue<Any>>>(type.arguments[1].type!!, ref)
                }
                type.isSubtypeOf(StructReturn::class.createType(listOf(KTypeProjection.STAR))) -> {
                    val constructor = (type.classifier as KClass<*>).primaryConstructor!!
                    val params = constructor.parameters.associateWith {
                        if(!it.type.isSubtypeOf(NotNull::class.createType(listOf(KTypeProjection.STAR))) &&
                            !it.type.isSubtypeOf(Nullable::class.createType(listOf(KTypeProjection.STAR, KTypeProjection(KVariance.OUT, NotNull::class.createType(listOf(KTypeProjection.STAR)))))))
                            throw Exception("Attributes can only be return_types.NotNull or return_types.Nullable<return_types.NotNull>")
                        createReference(it.type, ref + "." + it.name)
                    }
                    (constructor.callBy(params) as ReturnValue<*>).apply { this.type = ReturnValueType.Reference(ref) }
                }
                type.isSubtypeOf(Entity::class.createType(listOf(KTypeProjection.STAR))) -> {
                    val constructor = (type.classifier as KClass<*>).primaryConstructor!!
                    val params = constructor.parameters.associateWith {
                        if(!it.type.isSubtypeOf(NotNull::class.createType(listOf(KTypeProjection.STAR))) &&
                            !it.type.isSubtypeOf(Nullable::class.createType(listOf(KTypeProjection.STAR, KTypeProjection(KVariance.OUT, NotNull::class.createType(listOf(KTypeProjection.STAR)))))))
                            throw Exception("Attributes can only be return_types.NotNull or return_types.Nullable<return_types.NotNull>")
                        createReference(it.type, ref + "." + it.name)
                    }
                    (constructor.callBy(params) as ReturnValue<*>).apply { this.type = ReturnValueType.Reference(ref) }
                }
                type.isSubtypeOf(Nullable::class.createType(listOf(KTypeProjection.STAR, KTypeProjection.STAR)), ) -> {
                    Nullable<Any, NotNull<Any>>(Box.WithoutValue, type.arguments[1].type!!)
                        .apply { this.type = ReturnValueType.Reference(ref) }
                }
                type.isSubtypeOf(PrimitiveReturn::class.createType(listOf(KTypeProjection.STAR))) -> {
                    val constructor = (type.classifier as KClass<*>).primaryConstructor!!
                    (constructor.call(null) as ReturnValue<*>).apply { this.type = ReturnValueType.Reference(ref) }
                }
                else -> throw Exception()
            }
        }

        fun <T, U: ReturnValue<T>>createDummy(type: KFunction<U>): U{
            return createDummy(type.returnType) as U
        }
        fun <T, U: ReturnValue<T>>createReference(type: KFunction<U>, ref: String): U{
            return createReference(type.returnType, ref) as U
        }
        fun <T, U: ReturnValue<T>>createInstance(type: KFunction<U>, value: T): U{
            val dummy = createDummy(type)
            return dummy.encode(value) as U
        }
        fun <T, U: ReturnValue<T>>createInstance(type: KType, value: T): U{
            val dummy = createDummy(type) as U
            return dummy.encode(value) as U
        }
    }
}


