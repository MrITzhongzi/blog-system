package com.lhw.blog.service.impl;

import com.lhw.blog.domain.LhwArticles;
import com.lhw.blog.mapper.ArticleMapper;
import com.lhw.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 3:32 下午
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int insertArticle(LhwArticles article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public List<LhwArticles> getAllArticle() {
        return articleMapper.getAllArticle();
    }

    @Override
    public List<LhwArticles> getUserArticle(int userId) {
        return articleMapper.getUserArticle(userId);
    }

    @Override
    public LhwArticles getArticleById(int articleId) {
        return articleMapper.getArticleById(articleId);
    }
}
