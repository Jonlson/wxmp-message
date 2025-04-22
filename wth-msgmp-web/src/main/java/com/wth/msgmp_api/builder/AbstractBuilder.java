package com.wth.msgmp_api.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 消息日志
 */
public abstract class AbstractBuilder {

    public abstract WxMpXmlOutMessage build(String content,
                                            WxMpXmlMessage wxMessage, WxMpService service);
}
