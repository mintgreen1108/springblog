package com.mint.springblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: mint
 * @Date: 2022/1/6 14:59
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;
}
