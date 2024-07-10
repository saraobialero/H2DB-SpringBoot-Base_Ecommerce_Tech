package com.ecommerce.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(new Converter<BigDecimal, BigDecimal>() {
            @Override
            public BigDecimal convert(MappingContext<BigDecimal, BigDecimal> context) {
                BigDecimal source = context.getSource();
                return source != null ? source.setScale(2, RoundingMode.HALF_UP) : null;
            }
        });
        return modelMapper;
    }
}
