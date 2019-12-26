package cn.itsource.hrm.service.impl;

import cn.itsource.hrm.domain.CourseType;
import cn.itsource.hrm.mapper.CourseTypeMapper;
import cn.itsource.hrm.service.ICourseTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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


    @Override
    public List<CourseType> loadTreeData() {
        //List<CourseType> courseTypes = getByParentId(0L);
        //List<CourseType> courseTypes = loadTreeDataLoop_1();
        List<CourseType> courseTypes = loadTreeDataLoop();
        return courseTypes;
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
