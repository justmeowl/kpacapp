package com.justmeowl.kpacapp.config;

import com.justmeowl.kpacapp.knowledgepackage.KnowledgePackageField;
import com.justmeowl.kpacapp.knowledgepackageset.KnowledgePackageSetField;
import com.justmeowl.kpacapp.search.Convertable;
import com.justmeowl.kpacapp.search.Order;
import com.justmeowl.kpacapp.search.ValueEnumConvertor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.justmeowl.kpacapp")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        List<Class<? extends Enum<?>>> convertableClasses = Arrays.asList(Order.class,
                KnowledgePackageField.class,
                KnowledgePackageSetField.class);

        convertableClasses.forEach(e ->
                registry.addConverter(String.class, e, new ValueEnumConvertor(e))
                );
    }
}
