package sb.fontys.esw.spring;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring.PebbleViewResolver;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Basic Spring configuration, integrating Pebble.
 *
 * @author Robert
 */
@Configuration
@ComponentScan(basePackages = {"sb.fontys.esw.blog.controllers"})

@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ServletContext servletContext;

    @Bean
    public Loader templateLoader() {
        return new ServletLoader(servletContext);
    }

    @Bean
    public PebbleEngine pebbleEngine() {
        PebbleEngine engine = new PebbleEngine(templateLoader());
        
        engine.setTemplateCache(null);

        return engine;
    }

    @Bean
    public ViewResolver viewResolver() {
        PebbleViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix("");
        viewResolver.setPebbleEngine(pebbleEngine());
        
        return viewResolver;
    }
}
