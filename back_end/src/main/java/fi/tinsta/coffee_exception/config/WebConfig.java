package fi.tinsta.coffee_exception.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableHypermediaSupport(type= {EnableHypermediaSupport.HypermediaType.HAL})
@EnableWebMvc
@EnableEntityLinks
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

            registry
                    .addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/");
    }
}