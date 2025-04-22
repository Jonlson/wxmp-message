package com.wth.msgmp_api.config;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果集枚举
 */
public enum ReturnEnum {
    ERROR("0009","异常"),
    CONDITION_ERR("0002","查询条件有误"),
    RETURN_ERR("0003","查询结果有误"),
    SUCCESS("0000","成功");

    private String code;
    private String msg;

    ReturnEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    //返回Map结果集
    public static  Map<String,String> getMap(String code){
        Map<String,String> returnMap = new HashMap<String,String>();
        if(StringUtils.equals(ReturnEnum.CONDITION_ERR.code,code)){
            returnMap.put("code",ReturnEnum.CONDITION_ERR.code);
            returnMap.put("msg",ReturnEnum.CONDITION_ERR.msg);
        }else if(StringUtils.equals(ReturnEnum.RETURN_ERR.code,code)){
            returnMap.put("code",ReturnEnum.RETURN_ERR.code);
            returnMap.put("msg",ReturnEnum.RETURN_ERR.msg);
        }else if(StringUtils.equals(ReturnEnum.ERROR.code,code)){
            returnMap.put("code",ReturnEnum.ERROR.code);
            returnMap.put("msg",ReturnEnum.ERROR.msg);
        }else if(StringUtils.equals(ReturnEnum.SUCCESS.code,code)){
            returnMap.put("code",ReturnEnum.SUCCESS.code);
            returnMap.put("msg",ReturnEnum.SUCCESS.msg);
        }
        return returnMap;
    }
}
