package com.livegoods.login.service;

import com.livegoods.commons.pojo.ValidateCode;
import com.livegoods.login.dao.ValidateCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private ValidateCodeDao validateCodeDao;
    /**
     * 自定义登录逻辑
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ValidateCode validateCode = validateCodeDao.get(username);
        String password = "";
        if(validateCode != null){
            password = validateCode.getValidateCode();
        }
        // 用户权限集合
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails user = new User(username,
                // noop-不加密，bcrypt--加密
                "{noop}" + password,
                // 启动用户
                true,
                // 用户永不过期
                true,
                // 凭证永不过期
                true,
                // 锁定用户
                true,
                // 权限集合
                authorities);
        return user;
    }
}
