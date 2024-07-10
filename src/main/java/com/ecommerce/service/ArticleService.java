package com.ecommerce.service;

import com.ecommerce.exception.ArticleNotFoundException;
import com.ecommerce.exception.NoArticlesException;
import com.ecommerce.model.Article;
import com.ecommerce.repository.ArticleRepository;
import com.ecommerce.service.interfaces.ArticleFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticleService implements ArticleFunctions {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Optional<Article> existArticle(int idArticle) throws ArticleNotFoundException {
        Optional<Article> article = articleRepository.findById(idArticle);
        if(article.isEmpty()) throw new ArticleNotFoundException("Article not found");
        return article;
    }

    @Override
    public Set<Article> getArticles() {
        Set<Article> articles = articleRepository.findAll().stream().collect(Collectors.toSet());
        if (articles.isEmpty()) throw new NoArticlesException("No article in store");
        return articles.stream()
                .filter(article -> article.getAvailableQuantity() > 0)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Article> getArticleById(int idArticle) {
        return articleRepository.findById(idArticle);
    }
}
