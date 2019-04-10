package com.cecurs.enums;

public enum ErrorNoEnum {

    SUCCESS("0","成功"),
    ERROR("1","失败"),
    PARAM_NULL_ERROR("000000001","传入参数为空."),
    INNER_UNKNOWN_ERROR("000000002","未知错误."),
    DB_INSERT_ERROR("000000003","插入数据错误."),
    DB_SELECT_ERROR("000000004","查询数据错误."),
    DB_UPDATE_ERROR("000000005","更新数据异常."),
    DB_COUNT_ERROR("000000006","COUNT数据异常."),
    DB_DELETE_ERROR("000000007","删除数据异常."),
    DB_PRIMARY_KEY_NULL_ERROR("000000008","主键不能为空."),
    DB_RESULT_NULL_ERROR("000000009","查询结果为空"),
    DB_INNERINTERFACE_PARAM_ERROR("000000010","内部接口参数错误！"),
    PARAM_ERROR("000000011","传入参数错误！"),
    REQ_SIGN_ERROR("000000012","传入参数sign校验失败！"),
    CMD_NULL_ERROR("000900063","cmd is null!"),
    REQ_DEDATA_ERROR("000900030","req dedata error!"),
    FIND_USER_ERROR("000900094","find user error!"),
    USERID_NULL_ERROR("000900064","userId不能为空!"),
    USERID_NOT_EXISTED_ERROR("000900075","userid 不存在"),

    PRINTSTATUS_USER_DISABLED_ERROR("000900200","用户已禁用"),
    PRINTSTATUS_USER_REPORTED_LOSS_ERROR("000900201","用户已挂失"),


    //开卡 start
    OPEN_CARD_FAILED("000100001","开卡失败！"),
    USER_ALREADY_EXIST("000100002","用户已开卡！"),
    ORDER_PROCESSED_BEFORE("000100003","此订单已被处理 或 正在处理！"),
    USER_NOT_AUTH("000100004","用户未实名！"),
    PRODUCT_INFO_ERROE("000100003","此产品信息发生异常！"),
    //开卡 end

    // app start
    USER_NOT_EXISTS("000200001","用户不存在！"),
    USER_REPORTED_LOSS("000200002","用户已挂失！"),
    USER_DISABLED("000200002","用户已挂失！"),
    REOPEN_CARD_FAILED("000200003","imei无变化，补卡失败！"),
    REOPEN_CARD_DIFFIMEI_FAILED("000200004","imei有变化，补卡失败！"),
    CHANGE_CARD_NO_FAILED("000200005","用户更换卡号失败！"),
    CHANGE_PHONE_NO_FAILED("000200006","用户更换手机号失败！"),
    IMEI_ERROR("000200007","imei错误！"),
    CARDNO_IN_BLACKLIST_ERROR("000200008","该卡在黑名单内！"),
    USER_HAVE_NO_CARD("000200009","用户名下无卡！"),
    USER_HAVE_CARD("000200010","用户名下有卡！"),
    // app end

    //交易 start
    ERR_TRADE_TYPE("000300000","交易类型错误"),
    ERR_ACCOUNT_NOT_EXISTS("000300001","账户不存在"),
    ERR_DISTINCT_KEY_NULL("000300002","去重KEY不能为空"),
    ERR_CONSUME_DUPLICATE("000300003","消费交易重复"),
    ERR_USER_NOT_EXISTS("000300004","用户不存在"),

    ERR_CORE_ACCT_LIST_NOT_EXISTS("000300005","资金变动流水不存在"),
    ERR_CONSUME_CANCEL_MONEY_INVALID("000300006","撤销金额无效"),
    ERR_CORE_TRANS_LIST_NOT_EXISTS("000300007","交易流水不存在"),
    ERR_CONSUME_CANCEL_STATUS_INVALID("000300008","已撤销，请不要重复提交"),

    ERR_TRANS_NOT_EXISTS("000300009","交易不存在"),
    ERR_MONEY_PARSE_ERROR("000300010","金额解析或类型转换失败"),
    ERR_MONEY_NEGATIVE_ERROR("000300011","余额负值"),
    //交易 end

    //二维码 start
    QR_MONEY_ERROR("000400001","余额不足"),
    QR_CODE_GEN_ERR("000400002","二维码生成失败！")
    //二维码 end
,





    ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String code;
    private String msg;

    ErrorNoEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(String code) {
        for (ErrorNoEnum e : ErrorNoEnum.values()) {
            if (e.code .equalsIgnoreCase(code) ) {
                return e.getMsg();
            }
        }
        return null;
    }
    public static ErrorNoEnum getErrorNoEnum(String code) {
        for (ErrorNoEnum e : ErrorNoEnum.values()) {
            if (e.code .equalsIgnoreCase(code) ) {
                return e;
            }
        }
        return null;
    }


}
