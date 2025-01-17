package com.jtp.spring_microservice3_api_gateway.security;

import com.jtp.spring_microservice3_api_gateway.model.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Builder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    transient private String password;
    transient private User user;
    private Set<GrantedAuthority> authorities;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String username, String password, User user, Set<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    /*public static class Builder {
        private Long id;
        private String username;
        private String password;
        private User user;
        private Set<GrantedAuthority> authorities;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder authorities(Set<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }*/
}
