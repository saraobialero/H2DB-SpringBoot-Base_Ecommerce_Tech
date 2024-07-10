package com.ecommerce.service.interfaces;

import com.ecommerce.exception.ArticleNotFoundException;
import com.ecommerce.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleFunctions {
    Optional<Article> existArticle(int idArticle) throws ArticleNotFoundException;
    Set<Article> getArticles();
    Optional<Article>getArticleById(int idArticle);

}
