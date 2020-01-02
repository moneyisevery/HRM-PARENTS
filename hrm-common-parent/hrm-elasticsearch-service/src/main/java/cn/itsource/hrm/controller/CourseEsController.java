package cn.itsource.hrm.controller;

import cn.itsource.hrm.document.CourseDocument;
import cn.itsource.hrm.service.CourseRepository;
import cn.itsource.hrm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yb
 * @Date: 2020/1/1 0001 21:46
 * @Description: TODO
 * @Version:1.0
 */
@RestController
@RequestMapping("/es")
public class CourseEsController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/create")
    public AjaxResult createIndexName(@RequestBody List<CourseDocument> courseDocuments){

        try {
            courseRepository.saveAll(courseDocuments);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("创建失败");
        }
    }
    @PostMapping("/delete")
    public AjaxResult deleteindexs(@RequestBody List<Long> ids){

        try {
            for (Long id : ids) {
                courseRepository.deleteById(id);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败");
        }
    }
}
