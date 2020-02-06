package com.project.trimmer;

import com.dsp.cassandra.CassandraConfiguration;
import com.dsp.pojo.Trimmer;
import com.project.services.TrimmingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jws.soap.SOAPBinding;
import java.io.*;

@SpringBootApplication
@Import(CassandraConfiguration.class)
@EnableCassandraRepositories(basePackages = {"com.dsp.repo"})
@ComponentScan({"com.project.controllers","com.dsp"})
@EnableCaching
@Configuration
public class TrimmerApplication {

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    public static void main(String[] args) {

        SpringApplication.run(TrimmerApplication.class, args);
            init();
    }
    private static void init(){
        File file = new File("/home/vassar/projects/url_trimmer/src/main/resources/latestId");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            TrimmingService.latestId = Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
