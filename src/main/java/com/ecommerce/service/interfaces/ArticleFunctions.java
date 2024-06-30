package com.ecommerce.service.interfaces;

import com.ecommerce.exception.ArticleNotFoundException;
import com.ecommerce.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleFunctions {
    Optional<Article> existArticle(String idArticle) throws ArticleNotFoundException;
    List<Article> getArticles();
    Optional<Article>getArticleById(String idArticle);
}
