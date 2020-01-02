package cn.itsource.hrm.document.client.imp;

import cn.itsource.hrm.document.CourseDocument;
import cn.itsource.hrm.document.client.CourseEsClient;
import cn.itsource.hrm.util.AjaxResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: yb
 * @Date: 2020/1/1 0001 22:13
 * @Description: TODO
 * @Version:1.0
 */
@Component
public class CourseEsClientImpl implements CourseEsClient {
    @Override
    public AjaxResult createIndexName(List<CourseDocument> courseDocuments) {
       return AjaxResult.me().setSuccess(false).setMessage("失败");
    }

    @Override
    public AjaxResult deleteindexs(List<Long> ids) {
        return AjaxResult.me().setSuccess(false).setMessage("失败");
    }
}
