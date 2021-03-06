package com.bjtu.util;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Service
public class JedisAdapter implements InitializingBean{
	
	private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
	private JedisPool pool;

	public static  void print(int index,Object obj){
		System.out.println(String.format("%d,%s", index,obj.toString()));
	}
	public static void main(String[] args) {
		Jedis j = new Jedis("redis://localhost:6379/10");
		System.out.println(j.scard("LIKE:2:2"));
		j.close();
	}
	public long sadd(String key,String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.sadd(key, value);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return 0;
	}
	
	public long srem(String key,String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.srem(key, value);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return 0;
	}
	
	public long scard(String key){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.scard(key);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return 0;
	}
	
	public boolean sismember(String key,String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.sismember(key, value);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return false;
	}
	
	
	public long lpush(String key,String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.lpush(key, value);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return 0;
	}
	
	public List<String> brpop(int timeout,String key){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.brpop(timeout, key);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	
	public Jedis getJedis(){
		return pool.getResource();
	}
	
	public Transaction multi(Jedis jedis){
		try{
			return jedis.multi();
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}
		return null;
	}
	
	
	public List<Object> exec(Transaction tx,Jedis jedis){
		try{
			return tx.exec();
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(tx!=null){
				try {
					tx.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(jedis!=null){
				jedis.close();
			}
		}
		return null;
	}
	
	public long zadd(String key,double score,String value){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.zadd(key, score,value);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return 0;
	}
	
	public Set<String> zrevrange(String key,int start,int end){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.zrevrange(key, start, end);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	
	public long zcard(String key){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.zcard(key);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return 0;
	}
	
	
	public Double zscore(String key,String member){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			return jedis.zscore(key, member);
		}catch(Exception e){
			logger.error("发生异常"+e.getMessage());
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return null;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		pool = new JedisPool("redis://localhost:6379/10");
	}
	
}
