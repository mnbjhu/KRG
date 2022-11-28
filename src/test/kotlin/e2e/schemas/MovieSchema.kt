package e2e.schemas

import uk.gibby.krg.returns.graph.entities.UnitNode
import uk.gibby.krg.returns.graph.entities.UnitRelation
import uk.gibby.krg.returns.primitives.LongReturn
import uk.gibby.krg.returns.primitives.StringReturn

class Actor(val name: StringReturn): UnitNode()
class ActedIn(val role: StringReturn): UnitRelation<Actor, Movie>()
class Movie(
    val title: StringReturn,
    val releaseYear: LongReturn
): UnitNode()