package com.wez.crm.aspects;

import java.nio.file.AccessDeniedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

  @Before("@annotation(com.wez.crm.util.annotations.RequireAdmin)")
  public void checkAdminAccess() throws AccessDeniedException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
      throw new AccessDeniedException("You must be an admin to perform this action.");
    }
  }
}
