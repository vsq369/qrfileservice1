package com.cecurs.enums;

public enum CmdResp {

    SUCCESS("0000","成功"),

    FAIL("10003","上传失败"),
    DATANULL("1000","数据为空"),

    SYS_ERROR("9999","系统异常")
    ;

    private String code;
    private String desc;

   private CmdResp(String code,String desc){
       this.code = code;
       this.desc = desc;
   }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
