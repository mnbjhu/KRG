package uk.gibby.krg.core

import redis.clients.jedis.HostAndPort
import redis.clients.jedis.Jedis
import redis.clients.jedis.UnifiedJedis
import uk.gibby.krg.returns.ReturnValue
import uk.gibby.krg.returns.empty.EmptyReturn

/**
 * Graph
 *
 * Represents a graph on a Redis graph and is the entry point for making graph queries.
 *
 * @property name The name of the graph to query
 * @constructor Connects to Redis and allows queries to a graph ([name])
 *
 * @param host The database host
 * @param port The database port
 * @param password The database password
 */
class Graph(
    private val name: String,
    host: String,
    port: Int = 6379,
    password: String? = null
) {
    private val client: UnifiedJedis

    init {
        val hostAndPort = HostAndPort(host, port)
        val jedis = Jedis(hostAndPort)
        jedis.connect()
        password?.let { jedis.auth(it) }
        client = UnifiedJedis(jedis.client)
    }

    /**
     * Query
     *
     * @param T The return type of the query
     * @param queryBuilder A scope for creating a query using clauses defined on [QueryScope]
     * @return Query result in the form of a list of [T]
     */
    fun <T>query(queryBuilder: QueryScope.() -> ReturnValue<T>): List<T>{
        val scope = QueryScope()
        val result = scope.queryBuilder()
        val builtQuery = scope.getString()
        return if(result is EmptyReturn){
            client.graphQuery(name, builtQuery.also { println(it) })
            emptyList()
        } else {
            val response = client.graphQuery(name, "$builtQuery RETURN ${result.getString()}".also { println(it) })
            response.map { result.parse(it.values().first()) }
        }
    }

    /**
     * Delete
     *
     * Deletes the graph (graph by name: [name])
     */
    fun delete(){
        if(client.graphList().contains(name)) client.graphDelete(name)
    }
}