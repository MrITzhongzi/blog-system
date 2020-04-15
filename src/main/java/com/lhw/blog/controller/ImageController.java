package com.lhw.blog.controller;

import com.lhw.blog.config.CommonParam;
import com.lhw.blog.tool.JsonBuilder;
import com.lhw.blog.tool.QiniuUtils;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        boolean contains = Arrays.asList(CommonParam.IMAGETYPE).contains(suffix);
        if(!contains) {
            return JsonBuilder.buildError("上传的图片类型不支持，仅支持 " + StringUtils.join(CommonParam.IMAGETYPE, ","));
        }

        //上传到七牛云
        DefaultPutRet defaultPutRet = qiniuUtils.uploadFile(file);
        //从七牛云获取图片地址
        String imagePath = qiniuUtils.getPrivateImagePath(defaultPutRet.key);

        Map<String, String> map = new HashMap<>();
        map.put("imageName", defaultPutRet.key);
        map.put("imagePath", imagePath);
        if(defaultPutRet != null) {
            return JsonBuilder.buildSuccess("上传成功", map);
        }
        return JsonBuilder.buildError("上传失败");
    }

}
