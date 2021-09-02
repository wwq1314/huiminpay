package com.huiminpay.api;

/**
 * 验证码接口
 */
public interface ISmaService {

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    public String sendSms(String phone);
    /**
     * 验证码校验
     * @param verifyKey
     * 验证码的key值，由前端传递
     * @param verifyCode
     * 验证码，由用户输入的
     * @return
     */
    public String verify(String verifyKey,String verifyCode);
}
