package com.example.applefarm_.security.config;


import com.example.applefarm_.security.jwt.JwtAuthFilter;
import com.example.applefarm_.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers().frameOptions().disable(); // 해당 페이지를 <frame> 또는<iframe>, <object> 에서 렌더링할 수 있는지 여부

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션이 필요하면 생성하도록 셋팅


        http.authorizeHttpRequests()
                //todo .antMatchers( preflight request?)permitAll()
                //교차 출처 리소스 공유 (CORS) 사전 요청은 본격적인 교차 출처 HTTP 요청 전에 서버 측에서 그 요청의 메서드와 헤더에 대해 인식하고 있는지를 체크
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/users/signin").permitAll()
                .antMatchers("/api/users/signup").permitAll()
                .antMatchers("/api/users/signout").permitAll()
                .antMatchers("/api/users/**").hasRole("CUSTOMER")
                .antMatchers("/api/sellers/**").hasRole("SELLER")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
