package cn.itsource.hrm.util;

/**
 * @Author: yb
 * @Date: 2019/12/25 0025 20:04
 * @Description: TODO
 */
public interface Consts {
    //swagger中用到的controlle位置
    String  BASEPACKAGE="cn.itsource.hrm.web.controller";
    //swagger中用到的作者
    String  AUTHOR="yb";
    //swagger中用到的email
    String  email="yb809529544@qq.com";
    //版本
    String  version="1.0";
    //对应tenantMeal表中的state 表示未支付
    Integer TENANTMEAL_STATE=0;
    //对应employee表中的type  表示管理员
    Integer EMPLOYEE_TYPE=3;
    //对应tenant表中的state 表示在使用
    Integer TENANT_STATE=0;
}
