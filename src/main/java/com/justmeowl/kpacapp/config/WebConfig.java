package com.justmeowl.kpacapp.config;

import com.justmeowl.kpacapp.knowledgepackage.KnowledgePackageField;
import com.justmeowl.kpacapp.knowledgepackageset.KnowledgePackageSetField;
import com.justmeowl.kpacapp.search.Order;
import com.justmeowl.kpacapp.search.ValueEnumConvertor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.justmeowl.kpacapp")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        Class[] enumClasses = {Order.class,
                KnowledgePackageField.class,
                KnowledgePackageSetField.class};
        for (Class enumClass : enumClasses) {
            registry.addConverter(String.class, enumClass, new ValueEnumConvertor<>(enumClass));
        }
    }
}
