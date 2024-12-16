package com.metbetestimate.betestimate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;







@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig{


   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       http
               .authorizeHttpRequests((requests) -> requests
                               .requestMatchers("/*", "/api/*", "/css/*", "/js/*", "/images/*", "/api/getBet/*", "/api/getCBet/*").permitAll()
                               .anyRequest().authenticated()

               )
               .formLogin((form) -> form
                               .loginPage("/login")
                               .permitAll()
               )
               .logout((logout) -> logout.logoutSuccessUrl("/login?logout").permitAll())
               .csrf(csrf -> {
                  csrf.disable();
               });
        return (SecurityFilterChain) http.build();
    }

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder() 
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	

   
}