package com.lhw.blog.service;

import com.lhw.blog.domain.LhwArticles;

import java.util.List;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 3:31 下午
 */
public interface ArticleService {

    int insertArticle(LhwArticles article);

    List<LhwArticles> getAllArticle();

    List<LhwArticles> getUserArticle(int userId);

    LhwArticles getArticleById(int articleId);
}
