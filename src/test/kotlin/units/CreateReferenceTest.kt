import core.QueryScope
import core.invoke
import core.nullable
import core.of
import returns.StringReturn
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be instance of`
import org.junit.jupiter.api.Test
import returns.*
import returns.Node.Companion.minus
import returns.util.ReturnScope
import returns.util.ReturnValueType
import statements.Creatable.Companion.create
import statements.Delete.Companion.delete
import statements.Matchable.Companion.match
import statements.Set.Companion.set
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
    fun `Create Null Attribute Instance`(){
        val instance = createInstance(::TestReturn, null)
        instance.type `should be instance of` ReturnValueType.Instance::class
        instance.getString() `should be equal to` "{myString: NULL}"

    }
    @Test
    fun `Create Null Instance`(){
        val instance = nullable(::StringReturn) of null
        instance.getString() `should be equal to` "NULL"
    }
    @Test
    fun `Create not-null instance`(){
        val instance = nullable(::StringReturn) of "Hello"
        instance.getString() `should be equal to` "'Hello'"

    }
    @Test
    fun `Create node reference`(){
        with(QueryScope()){
            val first = createReference(::TestNode, "some_ref")
            val (node) = match(first - ::HasOther - first)
            val node2 = create(::TestNode{ it[::myString] = "some string" })
            create(node2 - ::HasOther - ::TestNode{ it[::myString] = "other string" })
            println(node.getString())
            println(node.myString.getString())
            delete(node, node2)
            set {
                node.myString to "Some String"
                node2.myString to "Other String"
            }
        }
    }
}

