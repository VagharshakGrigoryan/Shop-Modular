package am.onlinesite.onlinesiterest.config;

import am.onlinesite.onlinesitecommon.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/users","/listings","/categorys").authenticated()
//                .antMatchers(HttpMethod.GET, "/users/{id}","/listings/{id}","/categorys/{id}").authenticated()
//                .antMatchers(HttpMethod.GET, "/listings/users/{email}","/listings/categorys/{categoryId}").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/users/{id}","/listings/{id}","/categorys/{id}").hasAnyAuthority("ADMIN","USER")
//                .antMatchers(HttpMethod.POST,"/login","/users","/listings","/categorys").permitAll()
//                .antMatchers(HttpMethod.PUT,"/users/{id}","/listings/{id}","/categorys/{id}").permitAll();

//        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JWTAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JWTAuthenticationTokenFilter();
//    }

}
