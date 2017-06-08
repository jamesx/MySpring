package com.main.core.config.mvc;
import com.google.common.base.Charsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import java.util.List;

/**
 * Created by superMan791 on 2017/5/6.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置静态资源连接,并设置缓存链
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置静态资源路径，并且启用资源链缓存
        registry.addResourceHandler("/static/**").addResourceLocations("/static/").resourceChain(true).addResolver(
                new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    /**
     * thymeleaf相关配置
     * @author superMan791
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        // 是否启动缓存
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        //启用spEL支持,在大多数情况下是正常的,但是有时候会异常,为了更好的兼容性,可以设置成false;
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        /**
         * http相关的消息转换器
         */
        converters.add(stringHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(byteArrayHttpMessageConverter());
        converters.add(resourceHttpMessageConverter());
        converters.add(sourceHttpMessageConverter());
        converters.add(formHttpMessageConverter());
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter=new StringHttpMessageConverter(Charsets.UTF_8);
        converter.setDefaultCharset(Charsets.UTF_8);
        converter.setWriteAcceptCharset(false);
        return converter;
    }
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter(){
        return new ByteArrayHttpMessageConverter();
    }
    @Bean
    public ResourceHttpMessageConverter resourceHttpMessageConverter(){
      return new ResourceHttpMessageConverter();
    }
    @Bean
    public SourceHttpMessageConverter sourceHttpMessageConverter(){
       return new SourceHttpMessageConverter();
    }
    @Bean
    public FormHttpMessageConverter formHttpMessageConverter(){
        FormHttpMessageConverter converter=new FormHttpMessageConverter();
        converter.setCharset(Charsets.UTF_8);
       return converter;
    }
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
       return new MappingJackson2HttpMessageConverter();
    }

}
