package com.cecurs.service.impl;

import com.cecurs.dao.FileInfoMapper;
import com.cecurs.entity.FileInfo;
import com.cecurs.entity.FileInfoExample;
import com.cecurs.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService{

    @Resource
    private FileInfoMapper fileInfoMapper;

    @Override
    public boolean addFileInfo(FileInfo info) {
       int count = fileInfoMapper.insertSelective(info);
       return count > 0;
    }

    @Override
    public boolean updateFileInfo(FileInfo info) {
        int count = fileInfoMapper.updateByPrimaryKeySelective(info);
        return count > 0;
    }

    @Override
    public List<FileInfo> selFileInfo(FileInfoExample example) {
        return fileInfoMapper.selectByExample(example);
    }
}
