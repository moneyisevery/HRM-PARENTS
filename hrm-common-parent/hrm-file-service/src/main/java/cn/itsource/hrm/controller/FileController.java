package cn.itsource.hrm.controller;

import cn.itsource.hrm.FileUtil.FastDfsApiOpr;
import cn.itsource.hrm.util.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: yb
 * @Date: 2019/12/30 0030 19:45
 * @Description: TODO
 * @Version:1.0
 */
@RestController
@RequestMapping("fastdfs")
public class FileController {

    @PostMapping("upload")
    public AjaxResult fileLoad(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        int indexOf = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(indexOf + 1);
        try {
            String file_id = FastDfsApiOpr.upload(file.getBytes(),substring);
            return AjaxResult.me().setResultObj(file_id);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败"+e.getMessage());
        }
    }

    /**
     *
     * @param file_id @param file_id  /group1/M00/00/01/rBAFZF4HmpuASg0LAAVJ-pk7-do81.jpeg
     * @return
     */
    @GetMapping("delete")
    public AjaxResult deleteFile(String file_id){
        try {
            file_id=file_id.substring(1);
            int index = file_id.indexOf("/");
            String groupName = file_id.substring(0, index);
            String fileName = file_id.substring(index + 1);
            FastDfsApiOpr.delete(groupName, fileName);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败"+e.getMessage());
        }
    }


}
