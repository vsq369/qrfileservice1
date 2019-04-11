package com.cecurs.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {

    private Long id;

    private String fileName;

    private Integer fileSize;

    private String hash;

    private Integer flag;

    private Integer status;

    private String path;

    private Integer isAnalysis;

    private Date analysisTime;

    private String innCode;

    private String innName;

    private Date modifier;

}