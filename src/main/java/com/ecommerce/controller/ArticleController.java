package com.ecommerce.controller;

import com.ecommerce.model.dto.ArticleDTO;
import com.ecommerce.exception.ArticleNotFoundException;
import com.ecommerce.model.Article;
import com.ecommerce.service.interfaces.ArticleFunctions;
import com.ecommerce.utilities.JwtUtility;
import io.jsonwebtoken.Claims;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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
    public ResponseEntity<Set<ArticleDTO>> viewArticles(@RequestHeader("Authorization") String token) {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        Set<Article> articles = articleFunctions.getArticles();
        Set<ArticleDTO> articlesDTO = articles.stream()
                                        .map(article -> convertToDTO(article, ArticleDTO.class))
                                        .collect(Collectors.toSet());

        return articles.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(articlesDTO);

    }

    @GetMapping("article/{idArticle}")
    public ResponseEntity<ArticleDTO> viewArticle(@RequestHeader("Authorization") String token,
                                                  @PathVariable("idArticle") int idArticle) throws ArticleNotFoundException {
        Claims claims = jwtUtility.validateToken(token.replace("Bearer ", ""));

        Article article = articleFunctions.getArticleById(idArticle)
                            .orElseThrow(() -> new ArticleNotFoundException("Article with id " + idArticle + "not found"));

        ArticleDTO articleDTO = convertToDTO(article, ArticleDTO.class);

        return ResponseEntity.ok(articleDTO);

    }

    public <Entity, D> D convertToDTO(Entity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
