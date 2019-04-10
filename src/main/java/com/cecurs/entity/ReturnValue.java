package com.cecurs.entity;

public class ReturnValue {

    private String desc;

    private String result;

    private String messageType;

   private String version;

    private CmdEntity cmd;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public CmdEntity getCmd() {
        return cmd;
    }

    public void setCmd(CmdEntity cmd) {
        this.cmd = cmd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
