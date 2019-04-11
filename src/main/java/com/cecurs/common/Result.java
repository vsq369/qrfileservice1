package com.cecurs.common;

import com.cecurs.enums.ErrorNoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: guowei
 * @since: 2019/4/10 15:35
 */
@Setter
@Getter
@NoArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private T data;
    private List<T> datas;
    private Object branchData;

    public void setStatus(ErrorNoEnum type)
    {
        this.code = type.getCode();
        this.msg = type.getMsg();
    }

    public void setSubStatus(ErrorNoEnum type)
    {
        this.subCode = type.getCode();
        this.subMsg = type.getMsg();
    }

    public Result(ErrorNoEnum type) {
        this.code = type.getCode();
        this.msg = type.getMsg();
    }

    public boolean isSubOk()
    {
        return ErrorNoEnum.SUCCESS.getCode().equals(this.subCode);
    }


}
