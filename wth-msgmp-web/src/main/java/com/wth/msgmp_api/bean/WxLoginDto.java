package com.wth.msgmp_api.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxLoginDto {

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "gzh_id")
    private Long gzhId;

    @NotNull(message = "扫码ID为空！")
    @JSONField(name = "scene_id")
    private String sceneId;

    @JSONField(name = "from_user")
    private String fromUser;

    @JSONField(name = "phone")
    private Long phone;

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "open_id")
    private String openId;

    @JSONField(name = "grant_type")
    private String grantType;
}
