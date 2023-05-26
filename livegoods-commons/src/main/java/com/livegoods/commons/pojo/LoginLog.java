package com.livegoods.commons.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


/**
 * 登录日志实体
 * 记录一个用户登录的日志数据。
 * 当前系统是一个无注册逻辑的系统，用户只要提供有效的手机号，即可通过验证码登录。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginLog {
    // 日志id
    private String id;
    // 登录的用户名
    private String username;
    // 登录方式
    private String type;
    // 登录时间
    private Date loginTime;
    // 是否登录成功
    private Boolean isSuccess;
    // 日志消息
    private String message;
}
