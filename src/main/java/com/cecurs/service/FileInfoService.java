package com.cecurs.service;

import com.cecurs.entity.FileInfo;
import com.cecurs.entity.FileInfoExample;

import java.util.List;

public interface FileInfoService {

    boolean addFileInfo(FileInfo info);

    boolean updateFileInfo(FileInfo info);

    List<FileInfo> selFileInfo(FileInfoExample example);

}
