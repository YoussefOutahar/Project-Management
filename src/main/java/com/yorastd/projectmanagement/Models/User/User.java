package com.yorastd.projectmanagement.Models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yorastd.projectmanagement.Models.AuthModels.Token.Token;
import com.yorastd.projectmanagement.Models.Project;
import com.yorastd.projectmanagement.Models.Tasks.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;

  private String firstname;

  private String lastname;

  private String email;

  private Double costPerHour = 0.0;

  @JsonIgnore
  private String password;

  @ManyToMany
  @JsonIgnore
  private List<Task> tasks;

  @JsonIgnore
  @ManyToOne
  private Project projects;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Token> tokens;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @JsonIgnore
  @Override
  public String getUsername() {
    return email;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }
}
