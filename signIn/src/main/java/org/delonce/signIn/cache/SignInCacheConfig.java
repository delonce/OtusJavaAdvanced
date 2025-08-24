package org.delonce.signIn.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;



@Configuration
@EnableCaching
public class SignInCacheConfig {

    private static final Map<Object, Object> logDeletedRecords = new ConcurrentHashMap<>();


    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("users");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .removalListener((key, value, cause) -> {
                    if (key != null && value != null) {
                        logDeletedRecords.put(key, value);

                        byte[] largeObject = new byte[512 * 1024];
                        Arrays.fill(largeObject, (byte) 1);
                        logDeletedRecords.put(key + "_large", largeObject);
                    }
                })
                .maximumSize(5));
        return cacheManager;
    }
}