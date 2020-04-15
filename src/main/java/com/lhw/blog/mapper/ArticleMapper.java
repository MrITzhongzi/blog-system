package com.lhw.blog.mapper;

import com.lhw.blog.domain.LhwArticles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * @description:
 * @author: lihongwei
 * @time: 2020/4/10 3:24 下午
 */
public interface ArticleMapper {

    @Insert("INSERT INTO lhw_articles (user_id, article_title, article_content) VALUES (#{userId}, #{articleTitle}, #{articleContent});")
    @Options(useGeneratedKeys = true, keyProperty = "articleId", keyColumn = "article_id")
    int insertArticle(LhwArticles article);



}
