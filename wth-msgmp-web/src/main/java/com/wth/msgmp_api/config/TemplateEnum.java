package com.wth.msgmp_api.config;

/**
 * 模板ID枚举
 */
public enum TemplateEnum {

    WITHDRAWAL_ACCOUNT("OPENTM417944220","QaZcI0FGDQ35qGMR956T6NryKKLDiEihivXdPy165-I","提现到账"),

    ABNORMAL_ORDER_PROCESSED("OPENTM418265387","WvzzQCZ4-7rUEbUacjsw5gaW1n28TKl2L6CPJc33SKs","异常订单待处理"),

    SELLING_LACK("OPENTM416739138","jTW2YA6PKtoN7gYHoJ5uYn8TPynJv-augnBKxX0QZrI","售货机缺货"),

    EQUIPMENT_FAILURE("OPENTM417491100","kj_thmY8THsnlCeuWEdixXoWDMIhUH0DmE252FDVOl0","设备故障"),

    WITHDRAWAL_APPLICATION("OPENTM408664889","Pek52MRArVoVolhDQGGuagoLnl-Uh2YKKzN5TQ0eyCc","提现申请");


    private String key;
    private String value;
    private String msg;

    TemplateEnum(String key, String value, String msg) {
        this.key = key;
        this.value = value;
        this.msg = msg;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
