package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = { "com.create.user.controller", "com.create.user.dao", "com.create.user.service", "com.create.user.interceptor", "com.create.user.exceptions", "inicio"})
@EntityScan(basePackages = { "com.create.user.model" })
@EnableJpaRepositories(basePackages = { "com.create.user.dao" })
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate template() {
		 return new RestTemplate();
		

	}
}
