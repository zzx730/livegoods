package com.livegoods.commons.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 验证码实体
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ValidateCode {
    // 手机号
    private String phone;
    // 验证码
    private String ValidateCode;
}
