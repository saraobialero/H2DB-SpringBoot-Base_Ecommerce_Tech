package com.ecommerce.controller;

import com.ecommerce.dto.ArticleDTO;
import com.ecommerce.model.Article;
import com.ecommerce.service.interfaces.ArticleFunctions;
import com.ecommerce.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ecommerce/api/v1")
public class ArticleController {

    @Autowired
    private ArticleFunctions articleFunctions;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtility jwtUtility;

    @GetMapping("articles")
    public ResponseEntity<List<ArticleDTO>> viewArticles(@RequestHeader("Authorization") String token) {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        List<Article> articles = articleFunctions.getArticles();
        List<ArticleDTO> articlesDTO = articles.stream()
                                        .map(article -> convertToDTO(article, ArticleDTO.class))
                                        .collect(Collectors.toList());

        return articles.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(articlesDTO);

    }

    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
