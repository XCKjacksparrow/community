package per.xck.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc 开启的话会拦截静态资源
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SessionInterception sessionInterception;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterception).addPathPatterns("/publish","/publish/**");
    }
}
