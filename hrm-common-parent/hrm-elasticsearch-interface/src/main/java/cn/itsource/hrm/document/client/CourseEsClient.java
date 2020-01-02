package cn.itsource.hrm.document.client;

import cn.itsource.hrm.document.CourseDocument;
import cn.itsource.hrm.document.client.imp.CourseEsClientImpl;
import cn.itsource.hrm.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author: yb
 * @Date: 2020/1/1 0001 22:11
 * @Description: TODO
 */
@FeignClient(path = "/es",value = "ES-SERVICE",fallback = CourseEsClientImpl.class)
public interface CourseEsClient {
    @PostMapping("/create")
    AjaxResult createIndexName(List<CourseDocument> courseDocuments);
    @PostMapping("/delete")
    AjaxResult deleteindexs(List<Long> ids);
}
