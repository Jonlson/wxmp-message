package com.wth.msgmp_api.controller;


import com.wth.msgmp_api.service.WxPhoneBindingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * 微信手機綁定控制器
 */
@Slf4j
@Controller
@RequestMapping("/wx")
public class WxPhpneBindingController {

    /** 手机绑定接口 */
    @Resource
    private WxPhoneBindingService wxPhoneBindingService;

    /**
     * 进入手机绑定页面
     */
    @RequestMapping("/phoneBinding")
    public ModelAndView phoneBinding(WechatMpUser req, ModelMap modelMap){
        //如果Id不为空，这是要修改的
        if(null != req.getId()){
            modelMap.addAttribute("WechatMpUser",req);
            return new ModelAndView("binding/phone");
        }
        //判断openId是否已经绑定
        WechatMpUser wechatMpUser = wxPhoneBindingService.getByOpenId(req.getOpenId());
        //不为空，则进入选择页面
        if(null != wechatMpUser && null != wechatMpUser.getPhone() && !Long.valueOf(0).equals(wechatMpUser.getPhone())){
            modelMap.addAttribute("WechatMpUser",wechatMpUser);
           return new ModelAndView("binding/has-bind");
        }
        //手机号为空，则返回手机绑定页面
        modelMap.addAttribute("WechatMpUser",req);
        return new ModelAndView("binding/phone");
    }

    /**
     * 手机短信校验
     */
    @RequestMapping("/get-code")
    public ModelAndView ceckCode(WechatMpUser req, ModelMap modelMap){
        Result<?> result = wxPhoneBindingService.message(req.getPhone());
        modelMap.addAttribute("req",req);
        modelMap.addAttribute("result",result);
        return new ModelAndView( "binding/get-code");
    }

    /**
     * 验证码比较
     */
    @ResponseBody
    @RequestMapping("/check")
    public Result<?> check(Long phone,String code){
        Result<?> check = wxPhoneBindingService.check(phone,code);
        return check;
    }

    /**
     * 手机短信认证成功，保存绑定信息，并跳转值绑定页面
     */
    @RequestMapping("/has-bind")
    public ModelAndView hasBind(WechatMpUser req, ModelMap modelMap){
        //新增
        wxPhoneBindingService.insert(req);
        //新增之后，将对应的Id查询出来
        req = wxPhoneBindingService.getByOpenId(req.getOpenId());
        modelMap.addAttribute("WechatMpUser",req);
        return new ModelAndView( "binding/has-bind");
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/deleted")
    public Result<?> deleted(Long id, ModelMap modelMap){
        Result<?> result =  wxPhoneBindingService.deleted(id);
        return result;
    }


    /**
     * 根据标识判断新增或修改
     */
    @ResponseBody
    @RequestMapping("/input")
    public Result<?> delete(WechatMpUser req, ModelMap modelMap){
        Result<?> result =  wxPhoneBindingService.insert(req);
        return result;
    }

}
