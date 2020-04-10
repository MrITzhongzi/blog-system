package com.lhw.blog.controller;

import com.lhw.blog.tool.JsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 11:13 上午
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    /**
     * 发表文章 api
     * @return
     */
    @RequestMapping(path = "/publish", method = RequestMethod.POST)
    public JsonBuilder publishAticle(@RequestParam(value = "title") String title,
                                     @RequestParam(value = "content") String content,
                                     @RequestParam(value = "phone") String phone){
        //


        return null;
    }
}
