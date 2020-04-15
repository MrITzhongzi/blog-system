package com.lhw.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.service.ArticleService;
import com.lhw.blog.tool.JsonBuilder;
import com.lhw.blog.tool.PageUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private PageUtils<LhwArticles> pageUtils = new PageUtils<>();
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

    /**
     * 查询所有文章
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public JsonBuilder getAllArticle(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){
        PageHelper.startPage(page, size);
        List<LhwArticles> allArticle = articleService.getAllArticle();
        Map<String, Object> map = pageUtils.dealPageInfo(allArticle, page);
        return JsonBuilder.buildSuccess(map);
    }

    /**
     * 获取用户文章
     * @param page
     * @param size
     * @return
     */
    @GetMapping(path = "/user_article")
    public JsonBuilder getUserArticle(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size,
                                      HttpServletRequest request){
        int userId = (int)request.getAttribute("user_id");
        PageHelper.startPage(page, size);
        List<LhwArticles> userListArticle = articleService.getUserArticle(userId);
        Map<String, Object> map = pageUtils.dealPageInfo(userListArticle, page);
        return JsonBuilder.buildSuccess(map);
    }
}
