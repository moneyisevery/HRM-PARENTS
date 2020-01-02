package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: yb
 * @Date: 2019/12/27 0027 18:21
 * @Description: TODO
 * @Version:1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class RedisServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(RedisServiceApplication.class, args);
    }
}
