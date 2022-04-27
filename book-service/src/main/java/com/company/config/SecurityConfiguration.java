package com.company.config;

import com.company.common.security.auth.AuthenticationEntryPointConfigurer;
import com.company.common.security.auth.service.AuthService;
import com.company.common.security.auth.service.JwtService;
import com.company.common.security.config.BaseSecurityConfig;
import com.company.common.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

import static com.company.common.constants.HttpConstants.SUB_PATH;
import static com.company.common.security.constants.UserAuthority.PUBLISHER;
import static com.company.common.security.constants.UserAuthority.USER;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Import({
        SecurityProperties.class, JwtService.class,
        AuthenticationEntryPointConfigurer.class
})
@EnableWebSecurity
public class SecurityConfiguration extends BaseSecurityConfig {
    private static final String BOOK_API = "/api/books";

    public SecurityConfiguration(SecurityProperties securityProperties, List<AuthService> authServices) {
        super(securityProperties, authServices);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(GET, BOOK_API + SUB_PATH).permitAll()
                .antMatchers(POST, BOOK_API+SUB_PATH).access(authorities(PUBLISHER))
                .antMatchers(BOOK_API + SUB_PATH).access(authorities(USER));

        super.configure(http);
    }
}
