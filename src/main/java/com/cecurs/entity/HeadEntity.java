package com.cecurs.entity;

public class HeadEntity {

    private String packageLen;//整个包长度

    private String lsh;//同步流水号

    private String compressFlag;//压缩标志

    private String encrypType;//j加密算法

    private String version;

    private String msgType;


    public String getPackageLen() {
        return packageLen;
    }

    public void setPackageLen(String packageLen) {
        this.packageLen = packageLen;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getCompressFlag() {
        return compressFlag;
    }

    public void setCompressFlag(String compressFlag) {
        this.compressFlag = compressFlag;
    }

    public String getEncrypType() {
        return encrypType;
    }

    public void setEncrypType(String encrypType) {
        this.encrypType = encrypType;
    }

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
}
