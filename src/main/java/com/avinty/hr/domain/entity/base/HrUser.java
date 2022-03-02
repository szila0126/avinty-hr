package com.avinty.hr.domain.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class HrUser extends User {
  private String username;
  private Long id;
  private String password;

  public HrUser(
      Long id,
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.id = id;
    this.username = username;
    this.password = password;
  }
}
