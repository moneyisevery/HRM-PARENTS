package cn.itsource.hrm.client.imp;

import cn.itsource.hrm.client.RedisClient;
import org.springframework.stereotype.Component;

/**
 * @Author: yb
 * @Date: 2019/12/27 0027 19:04
 * @Description: TODO
 * @Version:1.0
 */
@Component
public class RedisClientImpl implements RedisClient {
    @Override
    public void set(String key, String value) {

    }

    @Override
    public String get(String key) {
        return "获取失败";
    }
}
