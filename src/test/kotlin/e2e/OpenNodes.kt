package e2e

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should contain same`
import org.junit.jupiter.api.Test
import uk.gibby.krg.clauses.Create.Companion.create
import uk.gibby.krg.clauses.Match.Companion.match
import uk.gibby.krg.core.invoke
import uk.gibby.krg.returns.graph.entities.Node
import uk.gibby.krg.returns.graph.entities.UnitNode
import uk.gibby.krg.returns.primitives.StringReturn
import uk.gibby.krg.returns.util.ReturnScope
import util.GraphTest

open class AnimalNode(val name: StringReturn): Node<Animal>() {
    override fun ReturnScope.decode() = Animal(::name.result())
}

class DogNode(name: StringReturn, val breed: StringReturn): AnimalNode(name){
    override fun ReturnScope.decode() = Dog(::name.result(), ::breed.result())
}
class FishNode(name: StringReturn, val waterType: StringReturn): AnimalNode(name){
    override fun ReturnScope.decode() = Fish(::name.result(), ::waterType.result())
}

val animals = listOf(
    Dog(name = "Scooby Doo",breed = "Great Dane" ),
    Dog(name = "Santa's Little Helper",breed = "Greyhound" ),
    Fish(name = "Nemo",waterType = "Saltwater" )
)

open class Animal(val name: String){
    override fun equals(other: Any?) = other is Animal && other.name == name
    override fun hashCode(): Int {
        return name.hashCode()
    }
}
class Dog(name: String, val breed: String): Animal(name){
    override fun equals(other: Any?) = other is Dog && (name == other.name) && (breed == other.breed)
    override fun hashCode() = super.hashCode()
}
class Fish(name: String, val waterType: String): Animal(name){
    override fun equals(other: Any?) = other is Fish && (name == other.name) && (waterType == other.waterType)
    override fun hashCode() = super.hashCode()
}


class OpenNodes: GraphTest() {
    @Test
    fun create(){
        graph.query {
            create(::DogNode{ it[::name] = "Scooby Doo"; it[::breed] = "Great Dane" })
            create(::DogNode{ it[::name] = "Santa's Little Helper"; it[::breed] = "Greyhound" })
            create(::FishNode{ it[::name] = "Nemo"; it[::waterType] = "Saltwater" })
        }
    }
    @Test
    fun matchAnimalProperty(){
        create()
        graph.query {
            val animal = match(::AnimalNode{})
            animal.name
        } `should contain same` listOf("Scooby Doo", "Santa's Little Helper", "Nemo")
    }
    @Test
    fun matchDogProperty(){
        create()
        graph.query {
            val animal = match(::DogNode)
            animal.name
        } `should contain same` listOf("Scooby Doo", "Santa's Little Helper")
    }
    @Test
    fun matchFishProperty(){
        create()
        graph.query {
            val animal = match(::FishNode)
            animal.name
        } `should contain same` listOf("Nemo")
    }
    @Test
    fun matchAnimal(){
        create()
        graph.query {
            val animal = match(::AnimalNode{})
            animal
        }.apply {
            count{ it is Dog } `should be equal to` 2
            count{ it is Fish } `should be equal to` 1
            count() `should be equal to` 3
        }
    }
    @Test
    fun matchDog(){
        create()
        graph.query {
            val animal = match(::DogNode)
            animal
        } `should contain same` listOf(
            Dog(name = "Scooby Doo",breed = "Great Dane" ),
            Dog(name = "Santa's Little Helper",breed = "Greyhound" )
        )
    }
    @Test
    fun matchFish(){
        create()
        graph.query {
            val animal = match(::FishNode)
            animal
        } `should contain same` listOf(Fish("Nemo", "Saltwater"))
    }

}