package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.client.RedisClient;
import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.mapper.CourseTypeMapper;
import cn.itsource.hrm.service.ICourseTypeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author yb
 * @since 2019-12-25
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private RedisClient redisClient;

    private final String  COURSE_TYPE_KEY="hrm:course_type:all";
    @Override
    public List<CourseType> loadTreeData() {
        //List<CourseType> courseTypes = getByParentId(0L);
        //List<CourseType> courseTypes = loadTreeDataLoop_1();
        //List<CourseType> courseTypes = loadTreeDataLoop();
        //  直接使用redis
        String courseTypeStr = redisClient.get(COURSE_TYPE_KEY);
        List<CourseType> courseTypes =null;
        //如果redis不存在，使用双重校验第一次redis不存在的时候查询数据库
        if(StringUtils.isEmpty(courseTypeStr)){
            //查询数据库，防止缓存穿透，大量请求同时查询数据库，同步代码块
            synchronized (CourseTypeServiceImpl.class){
                courseTypeStr = redisClient.get(COURSE_TYPE_KEY);
                if(StringUtils.isEmpty(courseTypeStr)){
                    //如果不存在，则查询数据库
                    courseTypes = loadTreeDataLoop();
                    //list集合转json字符串
                    String jsonStr = JSONObject.toJSONString(courseTypes);
                    //保存到redis中
                    redisClient.set(COURSE_TYPE_KEY, jsonStr);
                    return courseTypes;
                }
            }

        }
        //如果存在
        //json字符串转java集合
        courseTypes = JSONObject.parseArray(courseTypeStr, CourseType.class);
        return courseTypes;
    }

    private void synOperate(){
        List<CourseType> courseTypes =loadTreeData();
        String jsonString = JSONObject.toJSONString(courseTypes);
        redisClient.set(COURSE_TYPE_KEY, jsonString);
    }
    @Override
    public boolean save(CourseType entity) {
         super.save(entity);
        synOperate();
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        super.removeById(id);
        synOperate();
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        super.updateById(entity);
        synOperate();
        return true;
    }

    /**
     * case3 map循环方式
     * @return
     */
    public List<CourseType> loadTreeDataLoop(){
        //存放一级类型集合
        ArrayList<CourseType> firstLevelTypes  = new ArrayList<>();
        List<CourseType> courseTypes = baseMapper.selectList(null);
        HashMap<Long, CourseType> courseTypeHashMap = new HashMap<>();
        //先将查询出来的所有以键值对存储
        for (CourseType courseType : courseTypes) {
           courseTypeHashMap.put(courseType.getId(),courseType);
        }

        for (CourseType courseType : courseTypes) {
            if(courseType.getPid().longValue()==0L){
                firstLevelTypes.add(courseType);
            }else {
                //获取父级
                CourseType parent = courseTypeHashMap.get(courseType.getPid());
                if (parent!=null){
                    parent.getChildren().add(courseType);
                }
            }
        }

        return firstLevelTypes;
    }
    /**
     * case2 循环方式
     * @return
     */
    public List<CourseType> loadTreeDataLoop_1(){
        //存放一级类型集合
        ArrayList<CourseType> firstLevelTypes  = new ArrayList<>();
        List<CourseType> courseTypeList = baseMapper.selectList(null);
        //进行循环
        for (CourseType courseType : courseTypeList) {
            if (courseType.getPid()==0){
                //放入顶级集合
                firstLevelTypes .add(courseType);
            }else {
                //再次进行循环
                for (CourseType type : courseTypeList) {
                    //放入子集list
                    if (type.getId().longValue()==courseType.getPid().longValue()){
                        type.getChildren().add(courseType);
                    }
                }
            }
        }
        return firstLevelTypes;
    }
    /**
     *
     * @param pid
     * @return  递归方式  查询出家族关系  查询出所有子
     */
    public List<CourseType> getByParentId(Long pid) {
        //查询出所有一级菜单
        List<CourseType> children = baseMapper.selectList(
                new QueryWrapper<CourseType>().eq("pid", pid)
        );
        //出口
        if(children==null || children.size()==0){
            return null;
        }
        //对父级菜单进行循环
        for (CourseType child : children) {
            //查询出所有子菜单
            List<CourseType> childs = getByParentId(child.getId());
            child.setChildren(childs);
        }
        return children;
    }
}
