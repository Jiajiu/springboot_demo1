package com.jiu.sys.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName AppFileUtils
 * @Author Jiu
 * @Create 2020/4/22 17:43
 **/
public class AppFileUtils {

    /**
     * 文件上传的保存路径
     */
    public static String UPLOAD_PATH="D:/Program Files/upload";

    static {
        //读取配置文件的存储地址
        InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties=new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property=properties.getProperty("filepath");
        if(null!=property){
            UPLOAD_PATH=property;
        }
    }

    /**
     * 根据文件旧名字得到新名
     * @param oldName
     * @return
     */
    public static String createNewFileName(String oldName) {
        String stuff=oldName.substring(oldName.lastIndexOf("."),oldName.length());
        return IdUtil.simpleUUID().toUpperCase()+stuff;
    }

    /**
     * 文件下载
     * @param path
     * @return
     */
    public static ResponseEntity<Object> createResponseEntity(String path) {
        //1.构造文件对象
        File file=new File(UPLOAD_PATH,path);
        if(file.exists()){
            //得到要下载的文件，封装为byte[]
            byte[] bytes=FileUtil.readBytes(file);

            //创建封装响应头信息的对象
            HttpHeaders header=new HttpHeaders();
            //封装响应内容类型（APPLICATION_OCTET_STREAM 响应的内容不限定）
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件名称
//            header.setContentDispositionFormData("attachment","123.jpg");

            //创建ResponseEntity对象
            ResponseEntity<Object> entity=new ResponseEntity<Object>(bytes,header, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }

    /**
     * 根据路径名 去掉后缀_temp
     * @param goodsimg
     * @return
     */
    public static String reNameFile(String goodsimg) {
        File file=new File(UPLOAD_PATH,goodsimg);
        String replace = goodsimg.replace(Constant.FILE_UPLOAD_TEMP, "");
        if(file.exists()){
            file.renameTo(new File(UPLOAD_PATH,replace));
        }
        return replace;
    }

    /**
     * 根据旧路径删掉图片
     * @param oldPath
     */
    public static void removeFileByPath(String oldPath) {
        if(!oldPath.equals(Constant.DEFAULT_GOODS_IMG)){
            File file=new File(UPLOAD_PATH,oldPath);
            if(file.exists()){
                file.delete();
            }
        }
    }
}
