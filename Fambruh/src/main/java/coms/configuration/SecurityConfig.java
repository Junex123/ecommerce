package coms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import coms.service.UserDetailService;
import coms.configuration.JwtAuthFilter;
import coms.configuration.AuthEntryPoint;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailService).passwordEncoder(passwordEncoder());
    }
//
//    
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("your.smtp.host"); // Set your SMTP host here
//        mailSender.setPort(587); // Set your SMTP port here (typically 587 for TLS)
//        mailSender.setUsername("your.email@example.com"); // Set your email username
//        mailSender.setPassword("your-email-password"); // Set your email password
//        // You can configure more properties like protocol, default encoding, etc.
//        return mailSender;
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .authorizeRequests()
            .antMatchers("/generate-token").permitAll()
            .antMatchers("/user/signup").permitAll()
            .antMatchers("/get/all-products", "/get/products/**", "/get/products-by-category/**", "/get-product/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/deleteCartItem/**","/getCartDetails","/addToCart/**","/get/all-blogs", "/newsletter/subscriptions", "/newsletter/subscription/**", "/newsletter/subscribe", "/newsletter/unsubscribe/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers("/get/order-invoice/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(authEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
