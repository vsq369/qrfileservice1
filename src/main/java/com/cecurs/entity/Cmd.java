package com.cecurs.entity;

public class Cmd {

    private HeadEntity headEntity;

    private BodyEntity bodyEntity;

    private String mac;


    public HeadEntity getHeadEntity() {
        return headEntity;
    }

    public void setHeadEntity(HeadEntity headEntity) {
        this.headEntity = headEntity;
    }

    public BodyEntity getBodyEntity() {
        return bodyEntity;
    }

    public void setBodyEntity(BodyEntity bodyEntity) {
        this.bodyEntity = bodyEntity;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
