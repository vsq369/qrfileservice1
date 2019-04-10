package com.cecurs.Task;

import com.cecurs.common.Result;
import com.cecurs.entity.FileInfo;
import com.cecurs.entity.FileInfoExample;
import com.cecurs.enums.ErrorNoEnum;
import com.cecurs.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: guowei
 * @since: 2019/4/10 16:28
 */
@Component
@Slf4j
public class UploadFileTask {

    @Resource
    private FileInfoService fileInfoService;

    /**
     * 每天的凌晨0点上传文件
     */
    @Scheduled(cron="0 0 0 * * *")
    public Result updateFile() {
        log.info("---查询未上传完成的文件，进行上传---");
        Result result = new Result(ErrorNoEnum.SUCCESS);
        try{
            FileInfoExample example = new FileInfoExample();
            example.createCriteria().andFlagEqualTo(0).andStatusEqualTo(0);
            List<FileInfo> fileInfoList =  fileInfoService.selFileInfo(example);
            log.info("---未上传文件任务开始---");
            if(fileInfoList.size()>0){
                log.info("---未上传文件任务开始---");
            }else {
                log.info("---无未上传文件---");
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            result.setSubStatus(ErrorNoEnum.INNER_UNKNOWN_ERROR);
        }
        log.info("---未上传文件任务结束---");
        return result;
    }
}
