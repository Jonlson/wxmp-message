package com.wth.msgmp_api.service.Impl;

import com.alibaba.fastjson.JSON;

import com.wth.msgmp_api.service.WxPhoneBindingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 微信手机绑定接口实现
 */
@Slf4j
@Service
@Component
public class WxPhoneBindingServiceImpl implements WxPhoneBindingService {
    

    /** redis服务 */
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    /** 微信手机接口 */
    @Resource
    private WechatMpUserService wechatMpUserService;

    /** 发送验证码 */
    public Result<?> message(Long phone) {
        SmsMsg msg = new SmsMsg();
        //验证码
        String code = RandomUtil.getCode(4);
        msg.setCode(code);
        log.info(code);
        try {
            //短信发送
            SendSmsResponse response = SmsUtil.sendSms(String.valueOf(phone), JSON.toJSONString(msg), TemplateEnum.THE_PUBLIC.getMsg());
            if (response.getCode() != null && response.getCode().equals("OK")) {
                redisTemplate.opsForValue().set("@wrshgmpPhoneBinding@password@" + phone, code, 20, TimeUnit.MINUTES);
                return ResultUtils.success();
            } else {
                log.info("{} 公众号手机绑定短信 {}", phone, JSON.toJSONString(response));
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return ResultUtils.error(ResultEnum.ACQ_INVALID_PARAMETER, "短信发送失败");
    }

    /** 判断微信手机绑定表中 */
    @Override
    public WechatMpUser getByOpenId(String openId) {
        //通过openId查询数据库中是否存在记录
        WechatMpUser wechatMpUser =  wechatMpUserService.getByOpenId(openId);
        if(null != wechatMpUser && null != wechatMpUser.getPhone() && !Long.valueOf(0).equals(wechatMpUser.getPhone())){
            return wechatMpUser;
        }
        return null;
    }

    /** 删除 */
    @Override
    public Result<?>  deleted(Long id) {
        Integer count = wechatMpUserService.deleted(id);
        if(count >0){
            return  ResultUtils.success();
        }
        return  ResultUtils.error(ResultEnum.ACQ_SYSTEM_ERROR,"删除数据失败");
    }

    /** 新增 */
    @Override
    public Result<?> insert(WechatMpUser WechatMpUser) {
        Integer count = 0;
        //先查询
        WechatMpUser mpUser = wechatMpUserService.getByOpenId(WechatMpUser.getOpenId());
        //新增
        if(null != mpUser && StringUtils.isNoneEmpty()){
            WechatMpUser.setId(mpUser.getId());
            count = wechatMpUserService.update(WechatMpUser);
        }else{
            count = wechatMpUserService.insert(WechatMpUser);
        }




        if(count >0){
            return ResultUtils.success();
        }
        return ResultUtils.error(ResultEnum.ACQ_SYSTEM_ERROR,"新增数据失败");
    }

    /** 校验 */
    @Override
    public Result<?> check(Long phone, String code) {
        //先判断值是否为空
        if(null == phone || Long.valueOf(0).equals(phone) || StringUtils.isEmpty(code)){
            return ResultUtils.error(ResultEnum.ACQ_INVALID_PARAMETER, "参数有误");
        }
        //先查询手机号是否已经存在
        WechatMpUser wechatMpUser = wechatMpUserService.getByPhone(phone);
        if(null != wechatMpUser &&  null != wechatMpUser.getPhone() && !Long.valueOf(0).equals(wechatMpUser.getPhone())){
            return ResultUtils.error(ResultEnum.ACQ_OPERATE_BARRED, "手机号已存在！");
        }
        String redisCode = (String) redisTemplate.opsForValue().get("@wrshgmpPhoneBinding@password@" + phone);
        if(!StringUtils.equals(code,redisCode)){
            return ResultUtils.error(ResultEnum.ACQ_INVALID_PARAMETER, "验证码错误！");
        }
        return ResultUtils.success();
    }

    @Override
    public WechatMpUser getByPhone(Long phone) {
        return wechatMpUserService.getByPhone(phone);
    }
}
