package com.wth.msgmp_api.service;



public interface WxPhoneBindingService {

    /** 发送验证码短信 */
    Result<?> message(Long phone);

    /** 通过openId查询是否存在记录 */
    WechatMpUser getByOpenId(String openId);

    /** 通过Id删除对应的绑定信息 */
    Result<?> deleted(Long id);

    /** 新增 */
    Result<?> insert(WechatMpUser WechatMpUser);

    /** 校验 */
    Result<?> check(Long phone,String code);

    /** 通过手机号查询 */
    WechatMpUser getByPhone(Long phone);
}
