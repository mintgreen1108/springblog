package com.mint.springblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mint.springblog.common.dto.LoginDto;
import com.mint.springblog.common.lang.Result;
import com.mint.springblog.entity.User;
import com.mint.springblog.service.UserService;
import com.mint.springblog.util.JwtUtils;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: mint
 * @Date: 2022/1/6 17:04
 */
@RestController
public class AccountController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @CrossOrigin
    @PostMapping("/register")
    public Result register(@Validated @RequestBody User registerUser) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", registerUser.getUsername()));
        Assert.isNull(user, "用户已存在");
        registerUser.setPassword(SecureUtil.md5(registerUser.getPassword()));
        userService.save(registerUser);
        return Result.succ(null);
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
