package ru.springgb.sem10HW.securyty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import ru.springgb.sem10HW.securyty.details.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthService authService;


    @Autowired
    private CustomLogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .formLogin(form -> form
                        .loginPage("/signIn")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/users")
                        .failureUrl("/signIn")
                        .permitAll()
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizationManagerRequest -> authorizationManagerRequest

//                                .requestMatchers("/users/**").hasAnyRole("USER","ADMIN")
//                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                .requestMatchers("/users").authenticated()
                                .requestMatchers("/signIn").permitAll()
                                .requestMatchers("/regist").permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling(
                        e -> e.accessDeniedHandler(
                                        (request, response, accessDeniedException) -> response.setStatus(403)
                                )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))


                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService);
    }

}
