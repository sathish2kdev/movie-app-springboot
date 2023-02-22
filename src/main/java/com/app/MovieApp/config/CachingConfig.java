package com.app.MovieApp.config;
/**
 * 
 *//*
package com.app.MovieApp.security.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import java.lang.reflect.Method;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName("localhost");
		redisConnectionFactory.setPort(6379);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// Number of seconds before expiration. Defaults to unlimited (0)
		cacheManager.setDefaultExpiration(86400);
		return cacheManager;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
}


 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.cache.CacheManager; import
 * org.springframework.cache.annotation.EnableCaching; import
 * org.springframework.cache.concurrent.ConcurrentMapCacheManager; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 * import org.springframework.data.redis.cache.RedisCacheManager; import
 * org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
 * import org.springframework.data.redis.core.RedisTemplate;
 * 
 *//**
	 * @author Nandhu
	 *//*

 * @Configuration
 * 
 * @EnableCaching
 * 
 * @PropertySource("classpath:/redis.properties") public class CachingConfig {
 * 
 * 
 * private @Value("${redis.host}") String redisHost;
 * private @Value("${redis.port}") int redisPort;
 * 
 * @Bean public static PropertySourcesPlaceholderConfigurer
 * propertySourcesPlaceholderConfigurer() { return new
 * PropertySourcesPlaceholderConfigurer(); }
 * 
 * @Bean JedisConnectionFactory jedisConnectionFactory() {
 * JedisConnectionFactory factory = new JedisConnectionFactory();
 * factory.setHostName(redisHost); factory.setPort(redisPort);
 * factory.setUsePool(true); return factory; }
 * 
 * @Bean RedisTemplate<Object, Object> redisTemplate() { RedisTemplate<Object,
 * Object> redisTemplate = new RedisTemplate<Object, Object>();
 * redisTemplate.setConnectionFactory(jedisConnectionFactory()); return
 * redisTemplate; }
 * 
 * @Bean CacheManager cacheManager() { return new
 * RedisCacheManager(redisTemplate()); }
 * 
 *//**
	 * This method can be used to enable spring with guava cache manager
	 *//*
	 * 
	 * @Bean public CacheManager cacheManager() {
	 * 
	 * SimpleCacheManager cacheManager = new SimpleCacheManager(); GuavaCache
	 * guavaCache1 = new GuavaCache(CachingConstants.SERVICE_TAX,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache2 = new
	 * GuavaCache(CachingConstants.VPC_INSURER_LIST,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache3 = new
	 * GuavaCache(CachingConstants.VPC_COVER_LIST,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache4 = new
	 * GuavaCache(CachingConstants.VPC_UPA_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache5 = new
	 * GuavaCache(CachingConstants.VPC_BIFUEL_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache6 = new
	 * GuavaCache(CachingConstants.VPC_LLD_PREMIUM,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache7 = new
	 * GuavaCache(CachingConstants.VPC_CPA_PREMIUM,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache8 = new
	 * GuavaCache(CachingConstants.VPC_ANTI_THEFT_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache9 = new
	 * GuavaCache(CachingConstants.VPC_AMAI_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache10 = new
	 * GuavaCache(CachingConstants.VPC_PAD_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache11 = new
	 * GuavaCache(CachingConstants.VPC_LLE_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache12 = new
	 * GuavaCache(CachingConstants.VD_SUM_INSURED_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); GuavaCache guavaCache13 = new
	 * GuavaCache(CachingConstants.VPC_BIFUEL_RATE,
	 * CacheBuilder.newBuilder().expireAfterAccess(1,
	 * TimeUnit.DAYS).maximumSize(10).build()); cacheManager
	 * .setCaches(Arrays.asList(guavaCache1, guavaCache2, guavaCache3,
	 * guavaCache4, guavaCache5, guavaCache6, guavaCache7, guavaCache8,
	 * guavaCache9, guavaCache10, guavaCache11, guavaCache12, guavaCache13));
	 * cacheManager .setCaches(Arrays.asList(guavaCache6)); return cacheManager;
	 * 
	 * 
	 * SimpleCacheManager cacheManager = new SimpleCacheManager();
	 * cacheManager.setCaches(Arrays.asList(new
	 * ConcurrentMapCache(CachingConstants.SERVICE_TAX), new
	 * ConcurrentMapCache(CachingConstants.VPC_INSURER_LIST), new
	 * ConcurrentMapCache(CachingConstants.VPC_COVER_LIST), new
	 * ConcurrentMapCache(CachingConstants.VPC_UPA_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_BIFUEL_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_LLD_PREMIUM), new
	 * ConcurrentMapCache(CachingConstants.VPC_CPA_PREMIUM), new
	 * ConcurrentMapCache(CachingConstants.VPC_ANTI_THEFT_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_AMAI_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_PAD_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_LLE_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_LLE_RATE), new
	 * ConcurrentMapCache(CachingConstants.VD_SUM_INSURED_RATE), new
	 * ConcurrentMapCache(CachingConstants.VPC_BIFUEL_RATE)
	 * 
	 * )); return cacheManager;
	 * 
	 * }
	 * 
	 * 
	 * @Bean public CacheManager cacheManager() { SimpleCacheManager
	 * simpleCacheManager = new SimpleCacheManager();
	 * simpleCacheManager.setCaches(Arrays.asList(CachingConstants.
	 * VPC_INSURER_LIST, CachingConstants.VPC_LLE_RATE)); return
	 * simpleCacheManager; }
	 * 
	 * @Bean public CacheManager cacheManager() {
	 * System.out.println("Inside Cache.."); return new
	 * ConcurrentMapCacheManager("addresses"); }
	 * 
	 * }
	 
*/