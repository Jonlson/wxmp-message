package com.wth.msgmp_api.handler;

import com.wth.msgmp_api.builder.TextBuilder;
import com.wth.msgmp_api.common.AppProperties;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 扫码事件处理
 */
@Component
@Slf4j
public class ScanHandler extends AbstractHandler {
     @Resource
     private AppProperties appProperties;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理
        log.info("扫码用户 OPENID: " + wxMpXmlMessage.getFromUser());
        try {
            //关注公众号后提示信息
            StringBuilder html = new StringBuilder();
            html.append( "哈喽！欢迎关注【24小时智能货柜】");
            html.append("\n\n");
            html.append( "关注24H智能货柜,第一时间接收货柜状态！");
            html.append("\n\n");
            html.append( "登录：");
            html.append("\n\n");
            html.append("<a href='").append(appProperties.getWebUrl()).append("?sceneId=").append(wxMpXmlMessage.getEventKey()).append("&fromUser=").append(wxMpXmlMessage.getFromUser()).append("'>请点击链接，立即登录</a>");
            return new TextBuilder().build(html.toString(), wxMpXmlMessage, wxMpService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
