package com.cecurs.dao;

import com.cecurs.entity.FileInfo;
import com.cecurs.entity.FileInfoExample;

import java.util.List;

public interface FileInfoMapper {
    int countByExample(FileInfoExample example);

    int deleteByExample(FileInfoExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    List<FileInfo> selectByExample(FileInfoExample example);

    FileInfo selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByExample(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}