package com.cecurs.service.impl;

import com.cecurs.dao.FileInfoMapper;
import com.cecurs.entity.FileInfo;
import com.cecurs.entity.FileInfoExample;
import com.cecurs.handle.DownFileSvr;
import com.cecurs.service.FileInfoService;
import com.cecurs.util.TcpClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class FileInfoServiceImpl implements FileInfoService{

    @Resource
    private FileInfoMapper fileInfoMapper;
    @Resource
    private DownFileSvr downFileSvr;

    @Override
    public boolean addFileInfo(FileInfo info) {
       int count = fileInfoMapper.insertSelective(info);
       return count > 0;
    }


    public boolean updateFileInfo(FileInfo info) {
        int count = fileInfoMapper.updateByPrimaryKeySelective(info);
        return count > 0;
    }

    @Override
    public List<FileInfo> selFileInfo(FileInfoExample example) {
        return fileInfoMapper.selectByExample(example);
    }

    @Override
    public boolean downloadFileInfo(FileInfo info,TcpClient tcpClient) {
        String lsh = UUID.randomUUID().toString().substring(0,12);
        int code = downFileSvr.downLoad(tcpClient,info.getInnCode(),info.getInnName(),lsh);
        return 0 == code;
    }

}
