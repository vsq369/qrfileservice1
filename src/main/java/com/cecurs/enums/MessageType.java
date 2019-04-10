package com.cecurs.enums;

public enum MessageType {

    CMDHEADV1("head","01",20),
    CMDHEADV2("head","01",14),
    CMD4001("4001","01",313),
    CMD4002("4002","01",321),
    CMD4003("4003","01",578),
    CMD4004("4004","01",0),
    CMD4005("4005","01",18),
    CMD4006("4006","01",12),
    CMD4007("4007","01",14),
    CMD4008("4008","01",8),
    ;


    private MessageType(String type,String version,int length){
      this.type = type;
      this.version = version;
      this.length = length;
    }

    private String type;

    private String version;

    private int length;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
