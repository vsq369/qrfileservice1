package com.cecurs.ctrl;

import com.cecurs.enums.ErrorNoEnum;
import com.cecurs.entity.FileInfo;
import com.cecurs.common.Result;
import com.cecurs.service.FileInfoService;
import com.cecurs.util.TcpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: guowei
 * @since: 2019/4/9 17:52
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FileInfoService fileInfoService;

    @RequestMapping("/addFile")
    public Result addFile(@RequestBody FileInfo info){
        log.info("上传文件接口！");
        fileInfoService.addFileInfo(info);
        log.info("文件上传成功！");
        Result result = new Result(ErrorNoEnum.SUCCESS);
        return result;

    }

    @RequestMapping("/updateFile")
    public Result updateFile(@RequestBody FileInfo info){
        log.info("更新文件接口！");
        fileInfoService.updateFileInfo(info);
        log.info("文件更新成功！");
        Result result = new Result(ErrorNoEnum.SUCCESS);
        return result;
    }

    @RequestMapping("/downloadFile")
    public Result downloadFile(@RequestBody FileInfo info){
        TcpClient tcpClient = new TcpClient();
        log.info("下载文件接口！");
        fileInfoService.downloadFileInfo(info,tcpClient);
        log.info("文件下载成功！");
        Result result = new Result(ErrorNoEnum.SUCCESS);
        return result;

    }
}
