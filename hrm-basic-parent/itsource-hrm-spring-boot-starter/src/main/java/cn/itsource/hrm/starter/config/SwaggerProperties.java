package cn.itsource.hrm.starter.config;

import cn.itsource.hrm.util.Consts;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: yb
 * @Date: 2019/12/25 0025 18:59
 * @Description: TODO
 * @Version:1.0
 */
@Data
//可以在application中根据该hrm.swagger 进行赋值  以及修改
@ConfigurationProperties(prefix = "hrm.swagger")
public class SwaggerProperties {

    private String  basePackage= Consts.BASEPACKAGE;
    private String  title;
    private String  description;
    private String  author=Consts.AUTHOR;
    private String  url;
    private String  email=Consts.email;
    private String  version= Consts.version;

}
