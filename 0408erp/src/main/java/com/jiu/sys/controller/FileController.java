package com.jiu.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.jiu.sys.common.AppFileUtils;
import com.jiu.sys.common.Constant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FileController
 * @Author Jiu
 * @Create 2020/4/22 17:32
 **/
@RestController
@RequestMapping("file")
public class FileController {

    /**
     * 文件上传
     * @param mf
     * @return
     */
    @RequestMapping("uploadFile")
    public Map<String,Object> uploadFile(MultipartFile mf){
        //1.得到文件名
        String oldName=mf.getOriginalFilename();
        //2.根据文件名生成新的文件名
        String newName= AppFileUtils.createNewFileName(oldName);
        //3.得到当前日期的字符串
        String dirName= DateUtil.format(new Date(),"yyyy-MM-dd");
        //4.构造文件夹
        File dirFile=new File(AppFileUtils.UPLOAD_PATH,dirName);
        //5.判断当前文件夹是否存在
        if(!dirFile.exists()){
            //创建文件夹
            dirFile.mkdirs();
        }
        //6.构造文件对象
        File file=new File(dirFile,newName+ Constant.FILE_UPLOAD_TEMP);
        //7.把mf里面的图片信息写入到file中
        try {
            mf.transferTo(file);
        } catch (IllegalStateException|IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("path",dirName+"/"+newName+Constant.FILE_UPLOAD_TEMP);
        return map;
    }

    /**
     * 图片下载显示
     * @param path
     * @return
     */
    @RequestMapping("showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path){
        return AppFileUtils.createResponseEntity(path);
    }
}
