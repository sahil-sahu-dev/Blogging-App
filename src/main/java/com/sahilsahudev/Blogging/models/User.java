package com.sahilsahudev.Blogging.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private Integer id;
  private String name;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name="user_friends",
          joinColumns=@JoinColumn(name="user_id"),
          inverseJoinColumns=@JoinColumn(name="friend_id")
  )
  private List<User> following;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name="user_friends",
          joinColumns=@JoinColumn(name="friend_id"),
          inverseJoinColumns=@JoinColumn(name="user_id")
  )
  private List<User> followers;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Comment> comments = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Like> likes = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
