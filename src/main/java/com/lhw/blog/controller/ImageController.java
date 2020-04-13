package com.lhw.blog.controller;

import com.lhw.blog.tool.FileUtils;
import com.lhw.blog.tool.JsonBuilder;
import com.lhw.blog.tool.QiniuUtils;
import com.qiniu.storage.model.DefaultPutRet;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @description: 图片上传 api
 * @author: lihongwei
 * @time: 2020/4/10 4:05 下午
 */
@RestController
@RequestMapping("/api/img")
public class ImageController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @RequestMapping(path = "/upload_img", method = RequestMethod.POST)
    public JsonBuilder uploadFile(@RequestParam("img") MultipartFile file) {

        File requestFile = FileUtils.multipartFile2File(file);
        DefaultPutRet defaultPutRet = qiniuUtils.uploadFile(requestFile);
        if(defaultPutRet != null) {
            return JsonBuilder.buildSuccess("上传成功", defaultPutRet);
        }

        return JsonBuilder.buildError("上传失败");
    }

}
