package com.wth.msgmp_api.handler;

import com.wth.msgmp_api.builder.TextBuilder;
import com.wth.msgmp_api.config.UrlProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *关注公众号处理事件类
 */
@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties({UrlProperties.class})
public class SubscribeHandler extends AbstractHandler {

    private final UrlProperties urlProperties;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager){

        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            //关注公众号后提示信息
            StringBuilder html = new StringBuilder();
            html.append( "哈喽！欢迎关注【24H智能货柜】");
            html.append("\n\n");
            html.append("关注24H智能货柜，第一时间接收货柜状态");
            html.append("\n\n");
            html.append("<a  href='"+urlProperties.getRequestUrl()+"/wx/phoneBinding?openId="+wxMessage.getFromUser()+"'>《点击绑定手机号，立即查询详细信息！》</a>");
            html.append("\n\n");
            html.append("如需帮助请联系客服人员");
            return new TextBuilder().build(html.toString(), wxMessage, weixinService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

}
