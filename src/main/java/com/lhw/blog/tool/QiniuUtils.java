package com.lhw.blog.tool;

import com.google.gson.Gson;
import com.lhw.blog.config.QiniuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 4:05 下午
 */
@Component
public class QiniuUtils {

    @Autowired
    private QiniuConfig qiniuConfig;

    /**
     *
     * 上传图片到七牛云
     * @param file
     * @return
     */
    public DefaultPutRet uploadFile(MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = qiniuConfig.accessKey;
        String secretKey = qiniuConfig.secretKey;
        String bucket = qiniuConfig.bucket;
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //默认不指定key的情况下，以文件内容的hash值作为文件名 (这里的key就是存在七牛云上的文件名)
        String key = UuidUtils.getUUID() + suffix;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            InputStream is = file.getInputStream();
            try {
                Response response = uploadManager.put(is, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return putRet;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取七牛云私有空间的图片路径
     * @param filename
     * @return
     */
    public String getPrivateImagePath(String filename){
        //"1.png"
        String fileName = filename;
        String domainOfBucket = "http://q8eezq1qm.bkt.clouddn.com";
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        String accessKey = qiniuConfig.accessKey;
        String secretKey = qiniuConfig.secretKey;
        Auth auth = Auth.create(accessKey, secretKey);
        //1天，可以自定义链接过期时间
        long expireInSeconds = 3600 * 24;
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        System.out.println(finalUrl);
        return finalUrl;
    }

    /**
     * 获取七牛云共有空间的图片路径
     * @param filename
     * @return
     */
    public String getPublicImagePath(String filename){
        String fileName = filename;
        String domainOfBucket = "http://q8pvrckr7.bkt.clouddn.com";
        String finalUrl = String.format("%s/%s", domainOfBucket, fileName);
        System.out.println(finalUrl);
        return "";
    }
}
