package com.cecurs.util;

import com.cecurs.common.StringCls;

import java.util.HashMap;
import java.util.Map;

public class MsgSplit {


    //拆分的消息
    private String msg;
    //拆分起始索引
    private int index;

    public MsgSplit(String msg) {
        this.msg = msg;
        this.index=0;
    }


    public MsgSplit(String msg,int index) {
        this.msg = msg;
        this.index=index;
    }


    public String split(FieldType fileType,int length){

        String result = "";

        int headLen = 0;

        switch (fileType) {

            case FIX:
                result = this.msg.substring(this.index, this.index+length);
                this.index = this.index+length;
                break;
            case HEXNUM:
                result = Integer.parseInt(this.msg.substring(this.index, this.index+length),16)+"";
                this.index = this.index+length;
                break;
            case LCHAR:
                headLen = Integer.parseInt(this.msg.substring(this.index,this.index+2),16)*2;
                this.index = this.index+2;
                result = this.msg.substring(this.index,this.index+headLen);
                this.index = this.index+headLen;
                result = StringCls.HexToStr(result);
                break;
            case LLCHAR:
                headLen = Integer.parseInt(this.msg.substring(this.index,this.index+4),16)*2;
                this.index = this.index+4;
                result = this.msg.substring(this.index,this.index+headLen);
                this.index = this.index+headLen;
                result = StringCls.HexToStr(result);
                break;
            case LLLCHAR:
                headLen = Integer.parseInt(this.msg.substring(this.index,this.index+6),16)*2;
                this.index = this.index+6;
                result = this.msg.substring(this.index,this.index+headLen);
                this.index = this.index+headLen;
                result = StringCls.HexToStr(result);
                break;
            case LHEX:
                headLen = Integer.parseInt(this.msg.substring(this.index,this.index+2),16)*2;
                this.index = this.index+2;
                result = this.msg.substring(this.index,this.index+headLen);
                this.index = this.index+headLen;
                break;
            case LLHEX:
                headLen = Integer.parseInt(this.msg.substring(this.index,this.index+4),16)*2;
                this.index = this.index+4;
                result = this.msg.substring(this.index,this.index+headLen);
                this.index = this.index+headLen;
                break;
            case LLLHEX:
                headLen = Integer.parseInt(this.msg.substring(this.index,this.index+6),16)*2;
                this.index = this.index+6;
                result = this.msg.substring(this.index,this.index+headLen);
                this.index = this.index+headLen;
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * TLV格式分离 2字节类型 1字节长度
     * @param typeLen 类型长度
     * @param lengthLen 长度的长度
     * @return
     */
    public Map<String,String> splitTLV(int typeLen, int lengthLen){

        Map<String,String> map = new HashMap<String, String>();

        do{
            String type = this.msg.substring(index,index+typeLen);
            this.index = this.index+typeLen;
            String hexLen = this.msg.substring(index,index+lengthLen);
            this.index = this.index+lengthLen;
            //内容的长度
            int len = Integer.parseInt(hexLen,16)*2;
            String value = this.msg.substring(index,index+len);
            this.index = this.index+len;

            //System.out.println("type:"+type+"-->"+"value:"+value);

            map.put(type, value);

        }while(this.index<this.msg.length());

        return map;
    }

    public static enum FieldType {
        /** 定长 */
        FIX,

        /**十六进制数*/
        HEXNUM,

        /** 变长 ASCII */
        LCHAR,
        LLCHAR,
        LLLCHAR,

        /** 变长 HEX */
        LHEX,
        LLHEX,
        LLLHEX,
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
