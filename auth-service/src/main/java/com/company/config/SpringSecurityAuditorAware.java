package com.company.config;

import com.company.common.security.auth.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

import static com.company.common.security.constants.AuthConstants.SYSTEM_ACCOUNT;

@Component
@EnableJpaAuditing
@RequiredArgsConstructor
@EnableTransactionManagement
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    private final SecurityService securityService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(securityService.getCurrentUserEmail().orElse(SYSTEM_ACCOUNT));
    }
}