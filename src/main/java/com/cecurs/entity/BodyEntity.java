package com.cecurs.entity;

public class BodyEntity {

    private String version;
    private String msgType;
    private CmdEntity cmdEntity;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public CmdEntity getCmdEntity() {
        return cmdEntity;
    }

    public void setCmdEntity(CmdEntity cmdEntity) {
        this.cmdEntity = cmdEntity;
    }
}
