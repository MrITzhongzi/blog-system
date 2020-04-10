package com.lhw.blog.controller;

import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.service.ArticleService;
import com.lhw.blog.tool.JsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 11:13 上午
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 发表文章 api
     * @return
     */
    @RequestMapping(path = "/publish", method = RequestMethod.POST)
    public JsonBuilder publishAticle(@RequestParam(value = "title") String title,
                                     @RequestParam(value = "content") String content,
                                     HttpServletRequest request){
        //查询用户的相关信息
        Integer userId = (Integer)request.getAttribute("user_id");
        LhwArticles article = new LhwArticles();
        article.setUserId(userId);
        article.setArticleTitle(title);
        article.setArticleContent(content);
        try {
            int row = articleService.insertArticle(article);
            if(row == 0) {
                return JsonBuilder.buildError("文章插入失败");
            }
            return JsonBuilder.buildSuccess("文章插入成功");

        } catch (Exception e){}


        return JsonBuilder.buildError("文章插入失败");
    }
}
