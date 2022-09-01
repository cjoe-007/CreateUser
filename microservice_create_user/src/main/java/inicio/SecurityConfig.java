package inicio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	//definición roles y usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .inMemoryAuthentication()
        .withUser("user1")
          .password("{noop}user1") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
          .roles("USER")
          .and()
        .withUser("admin")
          .password("{noop}admin")
          .roles("USER", "ADMIN");
	}
	//definición de políticas de seguridad
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/login1").hasRole("ADMIN")
		.antMatchers("/login1").authenticated()
		.and()
		.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	
	}
}

