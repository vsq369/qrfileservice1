package com.cecurs.entity;

import java.util.Date;

public class FileInfo {
    private Byte id;

    private String fileName;

    private int fileSize;

    private String hash;

    private int flag;

    private int status;

    private String path;

    private int isAnalysis;

    private Date analysisTime;

    private String innCode;

    private String innName;

    private Date modifier;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIsAnalysis() {
        return isAnalysis;
    }

    public void setIsAnalysis(int isAnalysis) {
        this.isAnalysis = isAnalysis;
    }

    public Date getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(Date analysisTime) {
        this.analysisTime = analysisTime;
    }

    public String getInnCode() {
        return innCode;
    }

    public void setInnCode(String innCode) {
        this.innCode = innCode;
    }

    public String getInnName() {
        return innName;
    }

    public void setInnName(String innName) {
        this.innName = innName;
    }

    public Date getModifier() {
        return modifier;
    }

    public void setModifier(Date modifier) {
        this.modifier = modifier;
    }
}