package entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class SysUser implements UserDetails {

    private String id;

    private String username;

    private String password;

    private String avatar;

    private String openid;


    //获得权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    //账户是否上锁
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
