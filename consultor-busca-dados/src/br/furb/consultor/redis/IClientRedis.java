package br.furb.consultor.redis;

import com.lambdaworks.redis.RedisConnection;

public interface IClientRedis {

	public RedisConnection<String, String> getRedisConnection();
	
	public void closeConnection(RedisConnection<String, String> params);
}
