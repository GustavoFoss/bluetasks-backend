package br.com.softblue.bluetasks;

import br.com.softblue.bluetasks.domain.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class BluetasksBackendApplication implements RepositoryRestConfigurer {

  private static final Logger logger = LoggerFactory.getLogger(BluetasksBackendApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(BluetasksBackendApplication.class, args);

  }

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    config.exposeIdsFor(Task.class);
    cors
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET","POST","PUT","DELETE");
    logger.info("CORS Setup... OK!");
  }

  @Bean
  public Validator validator() {
    return new LocalValidatorFactoryBean();
  }

  @Override
  public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener vrel) {
    Validator validator = validator();
    vrel.addValidator("beforeCreate", validator);
    vrel.addValidator("beforeSave",validator);

    logger.info("Configure validator... OK!");
  }
}
