package com.cecurs.ctrl;

import com.cecurs.enums.ErrorNoEnum;
import com.cecurs.entity.FileInfo;
import com.cecurs.common.Result;
import com.cecurs.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guowei
 * @since: 2019/4/9 17:52
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
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
}
