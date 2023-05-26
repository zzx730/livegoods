package com.livegoods.login.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.livegoods.commons.pojo.LoginLog;
import com.livegoods.commons.pojo.ValidateCode;
import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.login.dao.LoginLogDao;
import com.livegoods.login.dao.ValidateCodeDao;
import com.livegoods.login.service.LoginService;
import com.livegoods.login.utils.SMSUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;


// 用户登录服务实现类
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private ValidateCodeDao validateCodeDao;
    @Autowired
    private LoginLogDao loginLogDao;
    /**
     * 发送验证码到手机号
     * @param phone
     * @return
     */
    @Override
    public LivegoodsResult sendyzm(String phone) {
        //查找phone对应的验证码
        ValidateCode validateCode = validateCodeDao.get(phone);
        if(validateCode!=null){
            LivegoodsResult ok = LivegoodsResult.ok();
            ok.setMessage("验证码已经发送，请勿重复申请");
            return ok;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        // 生成4位数的验证码
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(r.nextInt(9)+1);
        }
        String validateCode_1 = stringBuilder.toString();
        // 创建验证码对象
        ValidateCode code = new ValidateCode();
        code.setPhone(phone);
        code.setValidateCode(validateCode_1);
        // 将验证码对象保存到redis中
        validateCodeDao.set(phone,code);

        //返回结果

        try {
            Boolean flag = SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, phone, Integer.parseInt(validateCode_1));
            if(flag) {
                LivegoodsResult result = LivegoodsResult.ok();
                result.setMessage("验证码发送成功");
                System.out.println("手机号：" + phone + "，验证码：" + validateCode_1);
                return result;
            }else{
                validateCodeDao.delete(phone);
                LivegoodsResult result = LivegoodsResult.error("验证码发送失败");
                return result;
            }
        } catch (ClientException e) {
            e.printStackTrace();
            validateCodeDao.delete(phone);
            LivegoodsResult result = LivegoodsResult.error("验证码发送失败");
            return result;
        }

    }

    /**
     * 登录
     * @param username
     * @param validateCode
     * @return
     */
    @Override
    public LivegoodsResult login(String username, String validateCode) {
        // 1.创建登录日志对象
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setLoginTime(new Date());
        // 1-代表验证码登录
        loginLog.setType("1");

        // 2.从redis中获取对应用户的验证码
        ValidateCode validateCode2 = validateCodeDao.get(username);
        // 2.1 判断验证码是否为空，校验不通过时保存日志
        if(StringUtils.isEmpty(validateCode2.getValidateCode())){
            loginLog.setIsSuccess(false);
            loginLog.setMessage("验证码不存在或已过期");
            loginLogDao.insertLoginLog(loginLog);
            return LivegoodsResult.error("验证码已过期");
        }
        // 2.2 判断验证码与redis中存储的是否一致，校验不通过时保存日志
        if(!validateCode.equals(validateCode2.getValidateCode())){
            loginLog.setIsSuccess(false);
            loginLog.setMessage("验证码错误");
            loginLogDao.insertLoginLog(loginLog);
            return LivegoodsResult.error("验证码错误");
        }
        // 3.保存日志
        loginLog.setIsSuccess(true);
        loginLog.setMessage("登录成功");
        loginLogDao.insertLoginLog(loginLog);
        // 4.从redis中删除验证码
        validateCodeDao.delete(username);
        // 5.返回LivegoodsResult对象，提示登录成功
        LivegoodsResult ok = LivegoodsResult.ok();
        ok.setMessage("登录成功");
        return ok;
    }
}
