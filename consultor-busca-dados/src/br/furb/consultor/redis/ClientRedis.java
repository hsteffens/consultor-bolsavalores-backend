package br.furb.consultor.redis;

import com.lambdaworks.redis.*;

public class ClientRedis implements IClientRedis{
	
	private RedisURI uri;
	private RedisClient client;
	private RedisConnection<String, String> params;
	
	public ClientRedis(String uri) {
		this.uri = RedisURI.create(uri);
	}
	
	public RedisClient getClient() {
		return client;
	}

	public void setClient(RedisClient client) {
		this.client = client;
	}

	public RedisConnection<String, String> getParams() {
		return params;
	}

	public void setParams(RedisConnection<String, String> params) {
		this.params = params;
	}

	public RedisURI getUri() {
		return uri;
	}

	@Override
	public RedisConnection<String, String> getRedisConnection() {
		 if (getClient() == null) {
			setClient(new RedisClient(getUri()));
		}
		return getClient().connect();
	}

	@Override
	public void closeConnection(RedisConnection<String, String> params) {
		params.close();
		getClient().shutdown();
	}

}
