package cn.itsource.hrm.web.dto;

import cn.itsource.hrm.domain.Tenant;
import lombok.Data;

/**
 * @Description 租户注册的参数接收
 * @Author yb
 * @Date 2019/12/29 10:20
 * @Version v1.0
 **/
@Data
public class TenantDto {

    private Tenant tenant;

    private String username;

    private String password;

    private Long meal;

}
