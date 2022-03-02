package com.avinty.hr.config;

import com.avinty.hr.domain.entity.base.HrUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditorConfig implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.isAuthenticated()
        && Objects.equals(authentication.getPrincipal().getClass(), HrUser.class)) {
      return Optional.of(((HrUser) authentication.getPrincipal()).getId());
    }
    return Optional.of(0L);
  }
}
