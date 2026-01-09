package com.server.shared.infrastructure;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

    /**
     * Configuration class for setting up the ModelMapper bean.
     * It defines custom mapping behavior.
     */

    @Configuration

    public class ModelMapperConfig {
        @Bean
        public ModelMapper modelMapper() {
            ModelMapper  mapper = new ModelMapper();

            mapper.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT)
                    .setFieldMatchingEnabled(true)
                    .setSkipNullEnabled(true)
                    .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

            Converter<Object, Object> hibernateCollectionConverter = new AbstractConverter<Object, Object>() {
                @Override
                protected Object convert(Object source) {
                    if (source instanceof PersistentCollection) {
                        PersistentCollection pc = (PersistentCollection) source;
                        if (!pc.wasInitialized()) {
                            return new ArrayList<>();
                        }
                        return new ArrayList<>((Collection<?>) source);
                    }
                    if (source instanceof Collection) {
                        return new ArrayList<>((Collection<?>) source);
                    }
                    return source;
                }
            };

            mapper.addConverter(hibernateCollectionConverter);
            return mapper;
        }
    }