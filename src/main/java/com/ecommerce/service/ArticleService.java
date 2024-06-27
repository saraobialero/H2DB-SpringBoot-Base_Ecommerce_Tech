package com.ecommerce.service;

import com.ecommerce.model.Article;
import com.ecommerce.repository.ArticleRepository;
import com.ecommerce.service.interfaces.ArticleFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements ArticleFunctions {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
