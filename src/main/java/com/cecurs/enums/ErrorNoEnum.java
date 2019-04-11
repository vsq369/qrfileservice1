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
    REQ_SIGN_ERROR("000000012","传入参数校验失败！")

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
