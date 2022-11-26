package core

import redis.clients.jedis.HostAndPort
import redis.clients.jedis.Jedis
import redis.clients.jedis.UnifiedJedis
import returns.EmptyReturnInstance
import returns.ReturnValue

class RedisGraph(
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

    fun <T>query(queryBuilder: QueryScope.() -> ReturnValue<T>): List<T>{
        val scope = QueryScope()
        val result = scope.queryBuilder()
        val builtQuery = scope.getString()
        return if(result == EmptyReturnInstance){
            client.graphQuery(name, builtQuery)
            emptyList()
        } else {
            val response = client.graphQuery(name, "$builtQuery RETURN ${result.getString()}")
            response.map { result.parse(it) }
        }
    }
}