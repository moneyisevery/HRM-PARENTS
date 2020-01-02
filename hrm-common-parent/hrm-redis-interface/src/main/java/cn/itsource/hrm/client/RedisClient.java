package cn.itsource.hrm.client;

import cn.itsource.hrm.client.imp.RedisClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: yb
 * @Date: 2019/12/27 0027 18:58
 * @Description: TODO
 * @Version:1.0
 */
@FeignClient(value = "REDIS-SERVICE",path = "/redis",fallback = RedisClientImpl.class)
public interface RedisClient {


    @PostMapping("set")
    void set(@RequestParam("key") String key ,@RequestParam("value") String value);

    @GetMapping("get")
    String get(@RequestParam("key")String key);
}
