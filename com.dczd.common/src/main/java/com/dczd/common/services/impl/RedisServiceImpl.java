package com.dczd.common.services.impl;

import com.dczd.common.services.IRedisService;
import com.dczd.common.util.ConvertUtil;
import com.dczd.common.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description: Redis模板模块
 * @author: hou yangkun
 * @create: 2018/11/28
 */
@Service(value = "redisService")
public class RedisServiceImpl implements IRedisService {

    @Autowired
    public StringRedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);


    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void saveObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, String.valueOf(value));
    }

    @Override
    public <T> T getObject(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 存储json格式数据(普通对象类型)
     */
    @Override
    public void saveObjectToJson(String key, Object value)
            throws JsonProcessingException {
        if (value != null) {
            redisTemplate.opsForValue().set(key, JsonUtil.toJson(value));
        }

    }

    /**
     * 存储为hash存储结构(普通对象类型)
     */
    @Override
    public void saveObjectToJson(final String key1, final String key2, Object value)
            throws JsonProcessingException {
        if (value != null) {
            redisTemplate.opsForHash().put(key1, key2, JsonUtil.toJson(value));
        }
    }

    /**
     * 插入key并设置有效期(普通对象类型)
     *
     * @param key      键
     * @param value    值
     * @param liveTime 有效期 毫秒
     */

    @Override
    public void saveObjectToJson(String key, Object value, long liveTime)
            throws JsonProcessingException {
        if (value != null) {
            redisTemplate.opsForValue()
                    .set(key, JsonUtil.toJson(value), liveTime, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * byte[] 存储为String类型数据
     */
    @Override
    public void saveByteToString(String key, byte[] value) {
        if (value != null) {
            redisTemplate.opsForValue().set(key, ConvertUtil.bytesToHexString(value));
        }

    }

    /**
     * byte[] 存储为hash存储结构
     */
    @Override
    public void saveByteToString(final String key1, final String key2, final byte[] value)
            throws JsonProcessingException {
        if (value != null) {
            redisTemplate.opsForHash()
                    .put(key1, key2, JsonUtil.toJson(ConvertUtil.bytesToHexString(value)));
        }

    }

    /**
     * 获取json类型数据(普通对象类型)
     */
    @Override
    public <T> T getJson(String key, Class<T> elementType) {
        return JsonUtil.fromJson(redisTemplate.opsForValue().get(key), elementType);
    }

    /**
     * 获取json类型数据(普通对象类型),并刷新过期时间
     */
    @Override
    public <T> T getJsonAndRefreshExpTime(String key, Class<T> elementType, long liveTime)
            throws JsonProcessingException {
        String jsonStr = redisTemplate.opsForValue().get(key);

        // 如果有结果返回对象，并重置过期时间
        if (StringUtil.isNotEmpty(jsonStr)) {
            T t = JsonUtil.fromJson(jsonStr, elementType);

            // 重置过期时间
            saveObjectToJson(key, t, liveTime);

            // 返回查询结果
            return t;
        }
        return null;
    }

    /**
     * 获取hash存储结构的一个元素(普通对象类型)
     */
    @Override
    public <T> T getJson(final String key1, final String key2, Class<T> elementType) {
        return JsonUtil.fromJson((String) redisTemplate.opsForHash().get(key1, key2), elementType);
    }

    /**
     * 获取hash存储结构的一个元素集合(普通对象类型)
     */
    @Override
    public <T> List<T> getAllListJson(final String key, Class<T> elementType) {
        List resultList = new ArrayList();

        List<Object> list = redisTemplate.opsForHash().values(key);
        for (Object object : list) {
            resultList.add(JsonUtil.fromJson((String) object, elementType));
        }
        return resultList;
    }

    /**
     * 获取hash存储结构的一个元素Map(普通对象类型)
     */
    @Override
    public <T> Map<String, T> getAllMapJson(final String key, Class<T> elementType) {
        Map<String, T> resultMap = new HashMap<String, T>();
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            resultMap.put(String.valueOf(entry.getKey()),
                    JsonUtil.fromJson(String.valueOf(entry.getValue()), elementType));
        }
        return resultMap;
    }

    /**
     * 获取String类型数据( byte[]类型)
     */
    @Override
    public byte[] getByte(String key) {

        return ConvertUtil.hexStringToBytes(redisTemplate.opsForValue().get(key));
    }

    /**
     * 获取hash存储结构的一个元素( byte[]类型)
     */
    @Override
    public byte[] getByte(final String key1, final String key2) {
        String s = (String) redisTemplate.opsForHash().get(key1, key2);
        if (s == null) {
            return null;
        }
        return ConvertUtil.hexStringToBytes(s.replace("\"", ""));
    }

    /**
     * 获取hash存储结构的所有集合( byte[]类型)
     */
    @Override
    public List<byte[]> getAllListByte(final String key) {
        List<byte[]> resultList = new ArrayList<byte[]>();
        List<Object> list = redisTemplate.opsForHash().values(key);
        for (Object object : list) {
            resultList.add(ConvertUtil.hexStringToBytes(((String) object).replace("\"", "")));
        }
        return resultList;
    }

    /**
     * 获取hash存储结构的所有Map( byte[]类型)
     */
    @Override
    public Map<String, byte[]> getAllMapByte(final String key) {
        Map<String, byte[]> resultMap = new HashMap<String, byte[]>();
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            String value = String.valueOf(entry.getValue()).replace("\"", "");
            resultMap.put(String.valueOf(entry.getKey()), ConvertUtil.hexStringToBytes(value));
        }
        return resultMap;
    }

    /**
     * 按照key删除
     */
    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除hash存储结构
     */
    @Override
    public void del(final String key1, final String key2) {

        redisTemplate.opsForHash().delete(key1, key2);
    }

    /**
     * 模糊获取所有key
     */
    @Override
    public Set<String> getKeys(String key) {
        Set<String> keys = redisTemplate.keys(key);
        return keys;
    }

    /**
     * 模糊删除所有key
     */
    @Override
    public void delKeys(String keys) {
        // 删除redis记录
        Set<String> tb_set = this.getKeys(keys);

        //判空
        if (!StringUtil.isEmpty(String.valueOf(tb_set))) {
            for (String key : tb_set) {
                del(key);
            }
        }

    }


    @Override
    public Long getValidatetTime(String key) {
        return redisTemplate.getExpire(key);
    }
}