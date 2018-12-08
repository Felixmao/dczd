package com.dczd.common.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: Redis接口
 * @author: hou yangkun
 * @create: 2018/11/28
 */
public interface IRedisService {

    /**
     * 获取json类型数据(普通对象类型)
     */
    public Boolean hasKey(String key);

    /**
     * 缓存普通对象
     *
     * @param key
     * @param value
     * @return
     */
    public void saveObject(String key, Object value);


    /**
     * 获取普通对象
     *
     * @param key
     * @return
     */
    public <T> T getObject(String key);

    /**
     * 存储json格式数据(普通对象类型)
     */
    public void saveObjectToJson(String key, Object value)
            throws JsonProcessingException;

    /**
     * 存储为hash存储结构(普通对象类型)
     */
    public void saveObjectToJson(final String key1, final String key2, Object value)
            throws JsonProcessingException;

    /**
     * 插入key并设置有效期(普通对象类型)
     *
     * @param key      键
     * @param value    值
     * @param liveTime 有效期 毫秒
     */
    public void saveObjectToJson(String key, Object value, long liveTime)
            throws JsonProcessingException;

    /**
     * byte[] 存储为String类型数据
     */
    public void saveByteToString(String key, byte[] value);

    /**
     * byte[] 存储为hash存储结构
     */
    public void saveByteToString(final String key1, final String key2, final byte[] value)
            throws JsonProcessingException;

    /**
     * 获取json类型数据(普通对象类型)
     */
    public <T> T getJson(String key, Class<T> elementType);

    /**
     * 获取hash存储结构的一个元素(普通对象类型)
     */
    public <T> T getJson(final String key1, final String key2, Class<T> elementType);

    /**
     * 获取json类型数据(普通对象类型),并刷新过期时间
     */
    public <T> T getJsonAndRefreshExpTime(String key, Class<T> elementType, long liveTime)
            throws JsonProcessingException;

    /**
     * 获取hash存储结构的一个元素集合(普通对象类型)
     */
    public <T> List<T> getAllListJson(final String key, Class<T> elementType);

    /**
     * 获取hash存储结构的一个元素Map(普通对象类型)
     */
    public <T> Map<String, T> getAllMapJson(final String key, Class<T> elementType);

    /**
     * 获取String类型数据( byte[]类型)
     */
    public byte[] getByte(String key);

    /**
     * 获取hash存储结构的一个元素( byte[]类型)
     */
    public byte[] getByte(final String key1, final String key2);

    /**
     * 获取hash存储结构的所有集合( byte[]类型)
     */
    public List<byte[]> getAllListByte(final String key);

    /**
     * 获取hash存储结构的所有Map( byte[]类型)
     */
    public Map<String, byte[]> getAllMapByte(final String key);

    /**
     * 按照key删除
     */
    public void del(String key);

    /**
     * 删除hash存储结构
     */
    public void del(final String key1, final String key2);

    /**
     * 模糊获取所有key
     */
    public Set<String> getKeys(String key);

    /**
     * 模糊删除所有key
     */
    public void delKeys(String keys);

    /**
     * redis失效时间
     */
    public Long getValidatetTime(String key);
}
