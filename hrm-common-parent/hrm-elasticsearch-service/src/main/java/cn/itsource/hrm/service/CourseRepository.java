package cn.itsource.hrm.service;

import cn.itsource.hrm.document.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: yb
 * @Date: 2020/1/1 0001 22:01
 * @Description: TODO
 */
@Repository
public interface CourseRepository extends ElasticsearchRepository<CourseDocument,Long> {
}
