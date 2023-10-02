package com.satori.satoriservice.config;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.redisson.spring.starter.RedissonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auth YanFuYou
 * @date 05/09/23 下午 10:40
 */

@Configuration
@ConditionalOnClass({Redisson.class, RedisOperations.class})
@AutoConfigureBefore(RedisAutoConfiguration.class)
@EnableConfigurationProperties({RedissonProperties.class, RedisProperties.class})
public class RedissonEnableAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RedissonEnableAutoConfiguration.class);

    @Resource
    private RedissonProperties redissonProperties;
    @Resource
    private RedisProperties redisProperties;
    @Resource
    private ApplicationContext applicationContext;

    /**
     * redisKey序列化
     * @return key字符串
     */
    @Bean
    public RedisSerializer<String> redisKeySerializer(){
        return RedisSerializer.string();
    }

    /**
     * redis value格式化
     * @return value2json字符串
     */
    @Bean
    public RedisSerializer<Object> redisValueSerializer(){
        return RedisSerializer.json();
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory,RedisSerializer<String> keySerializer,RedisSerializer<Object> valueSerializer){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        redisTemplate.setDefaultSerializer(valueSerializer);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public RedisConnectionFactory redisConnectionFactory(RedissonClient redissonClient){
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient() throws IOException {
        Config config = null;

        Method getCluster = ReflectionUtils.findMethod(RedisProperties.class, "getCluster");
        Method getTimeout = ReflectionUtils.findMethod(RedisProperties.class, "getTimeout");
        Object timeOutValue = ReflectionUtils.invokeMethod(getTimeout, redisProperties);
        int timeOut;

        if (ObjectUtil.isNull(timeOutValue)){
            timeOut = 0;
        } else if (!(timeOutValue instanceof Integer)) {
            Method toMilliss = ReflectionUtils.findMethod(timeOutValue.getClass(), "toMilliss");
            timeOut = ((Long) ReflectionUtils.invokeMethod(toMilliss, toMilliss)).intValue();
        }else {
            timeOut = (Integer)timeOutValue;
        }

        if (ObjectUtil.isNotNull(redissonProperties.getConfig())){
            try{
                InputStream configStream = getConfigStream();
                config = Config.fromYAML(configStream);
            }catch (IOException e){
                throw new IllegalArgumentException("配置转换异常",e);
            }
        }else if (ObjectUtil.isNotNull(redisProperties.getSentinel())){
            Method getNodes = ReflectionUtils.findMethod(RedisProperties.Sentinel.class, "getNodes");
            Object nodesVal = ReflectionUtils.invokeMethod(getNodes, redisProperties.getSentinel());
            //节点数组
            String[] nodes;
            if (nodesVal instanceof String){
                nodes = convertNodes(Arrays.asList(((String) nodesVal).split(",")));
            }else {
                nodes = convertNodes((List<String>) nodesVal);
            }

            config = new Config();
            config.useSentinelServers()
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .addSentinelAddress(nodes)
                    .setDatabase(redisProperties.getDatabase())
                    .setConnectTimeout(timeOut)
                    .setPassword(redisProperties.getPassword())
                    .setUsername(redisProperties.getUsername());
        }else if (ObjectUtil.isNotNull(getCluster) && ObjectUtil.isNotNull(ReflectionUtils.invokeMethod(getCluster,redisProperties))){
            Object cluster = ReflectionUtils.invokeMethod(getCluster, redisProperties);
            Method nodes = ReflectionUtils.findMethod(cluster.getClass(), "getNodes");
            List<String> nodeList = (List) ReflectionUtils.invokeMethod(nodes, cluster);
            String[] strings = convertNodes(nodeList);

            config = new Config();
            config.useClusterServers()
                    .addNodeAddress(strings)
                    .setTimeout(timeOut)
                    .setPassword(redisProperties.getPassword());
        }else {
            config = new Config();
            String prefix = "redis://";
            Method method = ReflectionUtils.findMethod(RedisProperties.class, "isSsl");
            if (method != null && (Boolean)ReflectionUtils.invokeMethod(method, redisProperties)) {
                prefix = "rediss://";
            }

            RedisProperties.Pool pool = redisProperties.getLettuce().getPool() == null ? redisProperties.getJedis().getPool() :redisProperties.getLettuce().getPool();
            pool = pool == null ? new RedisProperties.Pool() : pool;
            log.info("redisson连接池配置,size:{},min:{}",pool.getMaxIdle(),pool.getMinIdle());
            config.useSingleServer()
                    .setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                    .setConnectTimeout(timeOut)
                    .setDatabase(redisProperties.getDatabase())
                    .setPassword(redisProperties.getPassword())
                    .setConnectionPoolSize(pool.getMaxIdle())//连接池最大容量
                    .setConnectionMinimumIdleSize(pool.getMinIdle());//最小空闲连接数
        }
        return Redisson.create(config);
    }


    private InputStream getConfigStream() throws IOException{
        org.springframework.core.io.Resource resource = applicationContext.getResource(redissonProperties.getConfig());
        return resource.getInputStream();
    }

    private String[]  convertNodes(List<String> nodesObject) {
        ArrayList<String> nodes = new ArrayList<>(nodesObject.size());
        for (String node : nodesObject) {
            if (!node.startsWith("redis://") && !node.startsWith("rediss")){
                nodes.add("redis://" + node);
            }else {
                nodes.add(node);
            }
        }
        return nodes.toArray(new String[nodes.size()]);
    }

    /**
     * 关闭redis健康检查
     * @return
     */
    @Bean
    public HealthIndicator redisHealthIndicator(){
        return ()-> Health.up().build();
    }
}
