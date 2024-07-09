package myconstruction.example.myconstruction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import myconstruction.example.myconstruction.services.UsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UsersService usersService;

  public SecurityConfig(UsersService usersService) {
    this.usersService = usersService;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()
            .anyRequest().authenticated())
        .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true"))
        .logout(logout -> logout
            .permitAll()
            .logoutSuccessUrl("/login?logout=true"))
        .logout(logout -> logout
            .permitAll()
            .logoutSuccessUrl("/login?logout=true"))
        .csrf().disable()
        .userDetailsService(usersService);

    return http.build();
  }
}