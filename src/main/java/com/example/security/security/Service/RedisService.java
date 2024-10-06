package com.example.security.security.Service;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisService {
    JedisPool pool = new JedisPool("localhost", 6379);

    public boolean storeRedisToken(String token,String username){
        try(Jedis jedis=pool.getResource()){

            jedis.set(token,username);
            return true;
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getUserNameFromToken(String token) {
        String username = null;
        try (Jedis jedis = pool.getResource()) {
            String name = jedis.get(token);
            if (name != null) {
                username = name;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());

        }
        return username;
    }


}
