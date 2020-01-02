package cn.itsource.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: yb
 * @Date: 2020/1/2 0002 18:37
 * @Description: TODO
 * @Version:1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@MapperScan("cn.itsource.hrm.mapper")
@EnableSwagger2
@EnableFeignClients //开启feign
public class PageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageServiceApplication.class, args);
    }
}
