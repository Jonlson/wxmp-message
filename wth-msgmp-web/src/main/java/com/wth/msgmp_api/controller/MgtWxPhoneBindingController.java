package com.wth.msgmp_api.controller;


import com.wth.msgmp_api.bean.WxLoginDto;
import com.wth.msgmp_api.service.MgtWxPhoneBindingService;
import com.wth.msgmp_api.service.WxPhoneBindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/mgt_wx")
public class MgtWxPhoneBindingController {
    @Resource
    private MgtWxPhoneBindingService mgtWxPhoneBindingService;
    @Resource
    private WxPhoneBindingService wxPhoneBindingService;

    /**
     * 用户点击是否确认登录
     */
    @RequestMapping("/confirm_login")
    public ModelAndView confirmLogin(WxLoginDto wxLoginDto, ModelMap modelMap) {
        WechatMpUser wechatMpUser = wxPhoneBindingService.getByOpenId(wxLoginDto.getFromUser());
        WxLoginDto rntWxLoginDto = new WxLoginDto();
        if (null == wechatMpUser) {
            rntWxLoginDto.setOpenId(wxLoginDto.getFromUser());
            //手机号为空，则返回手机绑定页面
            modelMap.addAttribute("WechatMpUser", rntWxLoginDto);
            return new ModelAndView("/binding/phone");
        }
        rntWxLoginDto.setPhone(wechatMpUser.getPhone());
        rntWxLoginDto.setSceneId(wxLoginDto.getSceneId());
        modelMap.addAttribute("WechatMpUser", rntWxLoginDto);
        return new ModelAndView("/mgtStatic/binding/has-login");
    }

    /**
     * 确认登录
     */
    @ResponseBody
    @RequestMapping("/wx_login")
    public Result<?> wxLogin(WxLoginDto wxLoginDto, ModelMap modelMap) {
        Result<?> result = mgtWxPhoneBindingService.wxLogin(wxLoginDto);
        return result;
    }
    /**
     * 登录完成跳转页面
     */
    @RequestMapping("/login_success")
    public ModelAndView loginSuccess(ModelMap modelMap) {
        return new ModelAndView("/mgtStatic/binding/login-success");
    }

}
