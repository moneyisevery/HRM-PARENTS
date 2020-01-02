package cn.itsource.hrm.client;

import cn.itsource.hrm.domain.Systemdictionaryitem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: TODO
 * @Author: LY
 * @Date: 2020-01-01 21:21
 * @Version: v1.0
 **/
@FeignClient(value = "SYSTEM-SERVICE",path = "/systemdictionaryitem")
public interface SystemDictionaryitemClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    Systemdictionaryitem get(@PathVariable("id")Long id);


}