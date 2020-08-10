package sshj.sshj.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile({"local", "dev"})
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Profile("local")
    @Bean
    public Docket localApi() {
        String info = "-----------------------------\n"
                + "-- swagger api for [local] --\n"
                + "-----------------------------\n";
        return getDefaultDocket(null, null);
    }

    @Profile("dev")
    @Bean
    public Docket devApi() {    // ?? ë°ęż?ź?¨
        String info = "-----------------------------\n"
                + "-- swagger api for [dev] --\n"
                + "-----------------------------\n";
        return getDefaultDocket(null, null);
    }



    private Docket getDefaultDocket(Set<String> protocols, String host){
        ApiInfo apiInfo = new ApiInfoBuilder().title("sshj-api").description("SSHJ API ¸íźź").build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        if(protocols != null) {
            docket.protocols(protocols);
        }

        if(host != null) {
            docket.host(host);
        }

        List global = new ArrayList();
        global.add(new ParameterBuilder().name("Authorization").
                description("Access Token").parameterType("header").
                required(false).modelRef(new ModelRef("string")).build());

        return docket
                .globalOperationParameters(global)
                .select()
                .apis(RequestHandlerSelectors.basePackage("sshj.sshj.controller"))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(apiInfo);

    }

}
