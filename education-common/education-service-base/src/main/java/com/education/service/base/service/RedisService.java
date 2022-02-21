package com.education.service.base.service;

import java.util.concurrent.TimeUnit;

/**
 * @author ALazyDogXD
 * @date 2022/2/21 23:24
 * @description Redis 服务
 */

public interface RedisService {

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key key
     * @param value value
     * @param timeout 过期时间
     * @param unit 时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *            秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    void setEx(String key, Object value, long timeout, TimeUnit unit);

}
