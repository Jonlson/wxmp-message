package com.wth.msgmp_api.config;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 请求验证
 */
@Controller
@RequestMapping({"/"})
public class WxConfigController {

    @RequestMapping({"MP_verify_8C0s84p4b0U57yeU.txt"})
    @ResponseBody
    private void returnConfigFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String echostr = "8C0s84p4b0U57yeU";
        // 通过检验signature 对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        out.print(echostr);
        out.flush();
        out.close();
        out = null;
    }
}