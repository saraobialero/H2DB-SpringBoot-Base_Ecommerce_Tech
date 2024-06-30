package com.ecommerce.config;

import com.ecommerce.dto.CartArticleDTO;
import com.ecommerce.model.CartArticle;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
   /* @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CartArticle.class, CartArticleDTO.class)
                .addMapping(src -> src.CartArticle.getQuantity(), CartArticleDTO::setQuantity);
        return modelMapper;
    }*/

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }
}
