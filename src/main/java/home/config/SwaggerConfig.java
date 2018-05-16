package home.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
//@PropertySource("classpath:swagger.properties")
//@PropertySource("classpath:/com/myco/app.properties")
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .paths(Predicates.not(PathSelectors.regex("/red.*")))
//                .paths(Predicates.not(PathSelectors.regex("/tokens.*")))
                .build()
                .apiInfo(metaData())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(response404.build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(response404.build()))
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(response400.build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(response400.build()))
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(response500.build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(response500.build()))
                ;
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Test",
                "1.0",
                "Terms of service",
                new Contact(null, null, null),
                null,
                null);
        return apiInfo;
    }

    ResponseMessageBuilder response404 = new ResponseMessageBuilder()
            .code(404)
            .message("Not Found")
            .responseModel(new ModelRef("Error"));

    ResponseMessageBuilder response400 = new ResponseMessageBuilder()
            .code(400)
            .message("Bad Request")
            .responseModel(new ModelRef("Error"));

    ResponseMessageBuilder response500 = new ResponseMessageBuilder()
            .code(500)
            .message("Internal Server Error")
            .responseModel(new ModelRef("Error"));
}
