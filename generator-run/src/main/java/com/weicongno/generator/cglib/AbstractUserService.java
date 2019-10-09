package com.weicongno.generator.cglib;

/**
 * @version 1.0 createTime:2019/9/25 15:40
 * @author:weicong
 */
public class AbstractUserService<K, V> implements UserService<K, V>{
    public K key;

    private V value;

    public Class<?> getKeyClass(){
        return key.getClass();
    }
}
