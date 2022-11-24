import core.QueryScope
import returns.StringReturn
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test
import returns.*
import returns.Node.Companion.minus
import statements.Matchable.Companion.match
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.createType


class TestNode(val myString: Nullable<String, StringReturn>): Node<Unit>(){
    override fun ReturnScope.decode() {
        return Unit
    }
}
class HasOther: Relation<TestNode, TestNode, Unit>() {
    override fun ReturnScope.decode() {
        TODO("Not yet implemented")
    }
}

class NestedReturn(val test: Nullable<String?, TestReturn>): StructReturn<String?>() {
    override fun encode(value: String?) = NestedReturn(test[value])

    override fun ReturnScope.decode() = test.result()
}

class TestReturn(val myString: Nullable<String, StringReturn>): StructReturn<String?>() {
    override fun encode(value: String?) = TestReturn(myString[value])
    override fun ReturnScope.decode() = myString.result()
}

class CreateReferenceTest {
    @Test
    fun createDummy(){
        val string = createDummy(StringReturn::class.createType()) as StringReturn
        val arr = createDummy(
            ArrayReturn::class.createType(
                listOf(
                    KTypeProjection(KVariance.INVARIANT, String::class.createType()),
                    KTypeProjection(KVariance.INVARIANT, StringReturn::class.createType())
                )
            )
        )
        val struct = createDummy(TestReturn::class.createType())
        val arr2 = createDummy(
            ArrayReturn::class.createType(
                listOf(
                    KTypeProjection(KVariance.INVARIANT, String::class.createType()),
                    KTypeProjection(KVariance.INVARIANT, TestReturn::class.createType())
                )
            )
        )
        val arr3 = createDummy(
            ArrayReturn::class.createType(
                listOf(
                    KTypeProjection(KVariance.INVARIANT, String::class.createType()),
                    KTypeProjection(KVariance.INVARIANT, NestedReturn::class.createType())
                )
            )
        )
    }
    @Test
    fun `Create Reference`(){
        createReference(::TestReturn, "TestRef").also {
            it.type `should be instance of` ReturnValueType.Reference::class
            it.getString() `should be equal to` "TestRef"
            it.myString.getString() `should be equal to` "TestRef.myString"
        }
    }
    @Test
    fun `Create Instance`(){
        val instance = createInstance(::TestReturn, "TestCreate")
        instance.type `should be instance of` ReturnValueType.Instance::class
        instance.getString() `should be equal to` "{myString: 'TestCreate'}"
    }
    @Test
    fun `Create Null Instance`(){
        val instance = createInstance(::TestReturn, null)
        instance.type `should be instance of` ReturnValueType.Instance::class
        instance.getString() `should be equal to` "NULL"

    }
    @Test
    fun `Create node reference`(){
        with(QueryScope()){
            val first = createReference(::TestNode, "some_ref")
            val (node, _, other) = match(first - ::HasOther - first)
            println(node.getString())
            println(node.myString.getString())
        }
    }
}

