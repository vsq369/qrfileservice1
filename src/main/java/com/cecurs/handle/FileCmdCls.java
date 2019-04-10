package com.cecurs.handle;

import com.cecurs.common.Tools;
import com.cecurs.entity.CmdEntity;
import com.cecurs.common.ReturnValue;
import com.cecurs.enums.CmdResp;
import com.cecurs.enums.MessageType;
import com.cecurs.util.HashAlgorithms;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jincao
 * 参照 城市交通IC卡技术规范第四部分 信息接口20150315.pdf 7.3
 * 消息报文类型   参照      7.3.1
 * 2016年1月6日15:28:57
 * 上传和下载工具类
 *
 * 拼装协议数据
 */

@Component
public class FileCmdCls {

    /**
     * head有两种格式
     * 第一版和第二版 第一版是长度少6
     * @return
     */
    public ReturnValue cmdHeadV1(String messageID, String messageType, int dataLen){

        StringBuffer sb = new StringBuffer();
        sb.append(Tools.leftopdata(""+(20+dataLen), 4, "0"));//包长度
        sb.append(messageID);//12位同步消息块
        sb.append("0"); //不压缩
        sb.append("0"); //不加密
        sb.append("01");//版本号
        sb.append(messageType);

        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());

        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType("head");
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());

        return returnValue;
    }

    public ReturnValue cmdHeadV2(String messageID, String messageType, int dataLen){
        Map<String,CmdEntity> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(Tools.leftopdata(""+(16+14+dataLen), 4, "0"));//包长度
        sb.append(messageID);//12位同步消息块
        sb.append("0"); //不压缩
        sb.append("0"); //不加密
        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMDHEADV2.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }


    /**
     * 发送报文头  4001 文件请求报文
     * 数据长度 313
     * instName 请求机构简称
     * instCode 请求机构代码
     */
    public  ReturnValue cmd4001Data(String instName, String instCode){

        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4001.getVersion());//版本号 消息类型
        sb.append(MessageType.CMD4001.getType());//版本号 消息类型
        sb.append(Tools.rightopdata(instName.length()>40?instName.substring(0, 40):instName, 40, " "));
        sb.append(Tools.rightopdata(instCode, 11, " "));
        sb.append(Tools.rightopdata("", 256, "F"));
        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4001.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }


    /**
     * 报文4002 长度321 文件下载报文
     * @param instName 请求机构简称
     * @param instCode 请求机构代码
     * @return
     */
    public ReturnValue cmd4002Data(String instName, String instCode){
        Map<String,CmdEntity> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4002.getVersion());
        sb.append(MessageType.CMD4002.getType());
        sb.append(Tools.rightopdata(instName.length()>40?instName.substring(0, 40):instName, 40, " "));
        sb.append(Tools.rightopdata(instCode, 11, " "));
        sb.append(Tools.rightopdata("", 8, "0"));
        sb.append(Tools.rightopdata("", 256, "F"));


        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4002.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }
    /**
     * 4003文件信息通知报文
     * @param  filePath 文件路径
     * @param fileName  文件名 fileAstract 摘要 filesize 文件大小
     * @return
     */
    public  ReturnValue cmd4003Data(String filePath, String fileName){

        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4003.getVersion());//版本号
        sb.append(MessageType.CMD4003.getType());//消息类型
        sb.append(Tools.rightopdata(fileName, 50, " ")); //文件名
        File file = new File(fileName);
        String fileAstract= HashAlgorithms.sha256(file);
        sb.append(Tools.rightopdata(fileAstract, 256, " "));//文件摘要
        sb.append(Tools.leftopdata(""+file.length(), 10, "0"));//文件大小
        sb.append(Tools.leftopdata("", 256, " "));//ans256 保留

        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4003.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }


    /**
     *
     * @param data 待解析数据
     *  fileName 文件名  fileAstract 文件摘要 fileSize 文件大小
     * @return
     */

   public  Map<String,String> cmd4003Dcompose(String data){

       Map<String,String> map = new HashMap<>();
       map.put("cmd","4003");
       if(StringUtils.isEmpty(data)){
           map.put("result","01");
       }
       String type = data.substring(20,24);
      //解析如果以4007结尾则说明文件已经传完
       if("4007".equals(type)){
           map.put("result","00");
           map.put("cmd","4007");
       }
       int len = data.length();
       try{
           String fileName =data.substring(len-256-16-10-256-50,len-256-16-10-256);//文件名
           String fileAstract=data.substring(len-256-16-10-256, len-256-16-10);//文件摘要
           String fileSize=data.substring(len-256-16-10,len-256-16);//文件大小
           map.put("fileName",fileName);
           map.put("fileAstract",fileAstract);
           map.put("fileSize",fileSize);
       }catch (Exception e) {
           e.printStackTrace();
           map.put("result","01");
       }
       return map;
   }


    /**
     * 4004 报文数据块
     * lstflag 1代表最后一个报文 0 代表有后续报文
     * dataBlock 数据块
     */
    public  ReturnValue cmd4004uploadData(String lstflag,String dataBlock){
        Map<String,String> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4004.getVersion());//版本
        sb.append(MessageType.CMD4004.getType());
        sb.append(lstflag);
        sb.append(dataBlock);

        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4004.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }

    /**
     *
     * @param data 待解析数据
     * @return
     */
   public Map<String,String> cmd4004DownloadData(String data){

       Map<String,String> map = new HashMap<>();

       int datalen = data.length();
       //head
       String len = data.substring(0,4);//文件长度
       String messageID = data.substring(4,16);
       String type = data.substring(16,18);//文件类型
       //head
       //文件体
       String version = data.substring(18,20);
       String msgType = data.substring(20, 24);
       String lstflag = data.substring(24, 25);
       String dataBlock = data.substring(25,datalen-16);
       String MAC = data.substring(datalen-16);
       map.put("result","00");
       map.put("cmd","4004");
       map.put("resultdata",version);
      return map;
   }


    /**
     * 断点续传
     * @param fileSize
     * @param retCode
     * @return
     */
   public ReturnValue cmd4005Data(String fileSize,String retCode){
       StringBuffer sb = new StringBuffer();
       sb.append(MessageType.CMD4005.getVersion());
       sb.append(MessageType.CMD4005.getType());
       sb.append(Tools.leftopdata(""+fileSize, 10, "0"));//文件大小
       sb.append(retCode);

       ReturnValue returnValue = new ReturnValue();
       CmdEntity cmdEntity = new CmdEntity();
       cmdEntity.setDataBlock(sb.toString());
       returnValue.setCmd(cmdEntity);
       returnValue.setMessageType(MessageType.CMD4005.getType());
       returnValue.setResult(CmdResp.SUCCESS.getCode());
       returnValue.setDesc(CmdResp.SUCCESS.getDesc());
       return returnValue;
   }


    /**
     *
     * @param files 文件个数
     * @param retCode  返回码
     * @return
     */
    public ReturnValue cmd4006Data(String files,String retCode){
        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4006.getVersion());
        sb.append(MessageType.CMD4006.getType());
        sb.append(Tools.leftopdata(""+files, 4, "0"));//文件大小
        sb.append(retCode);
        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4006.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }


    /**
     * 解析4006
     * @param data
     * @return
     */
    public  ReturnValue cmd4006Decompose(String data){
        ReturnValue returnValue = new ReturnValue();
       if(StringUtils.isEmpty(data)){
           returnValue.setResult(CmdResp.DATANULL.getCode());
           returnValue.setDesc(CmdResp.DATANULL.getDesc());
           return returnValue;
       }
        int len = data.length();
        String retcode = data.substring(len-18,len-16);
        if("00".equals(retcode)){
            CmdEntity cmdEntity = new CmdEntity();
            cmdEntity.setFiles(data.substring(len-22, len-18));
            returnValue.setCmd(cmdEntity);
            returnValue.setResult(CmdResp.SUCCESS.getCode());
            returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        }else{
            returnValue.setResult(retcode);
            returnValue.setDesc("读取文件个数为0");
        }
        return returnValue;
    }

    /**
     * 4007
     * @return
     */
    public  ReturnValue cmd4007Data(){
        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4007.getVersion());
        sb.append(MessageType.CMD4007.getType());
        sb.append("**TEOF**");
        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4007.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }

    /**
     * 解析4007
     */

    public ReturnValue cmd4007Decompose(String data){
        ReturnValue returnValue = new ReturnValue();
        if("**TEOF**".equals(data.substring(data.length()-16-8, data.length()-16))){
            returnValue.setResult(CmdResp.SUCCESS.getCode());
            returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        }
        return returnValue;
    }

    /**
     *
     * @param retCode
     * @return
     */
    public  ReturnValue cmd4008Data(String retCode){
        StringBuffer sb = new StringBuffer();
        sb.append(MessageType.CMD4008.getVersion());
        sb.append(MessageType.CMD4008.getType());
        sb.append(retCode);

        ReturnValue returnValue = new ReturnValue();
        CmdEntity cmdEntity = new CmdEntity();
        cmdEntity.setDataBlock(sb.toString());
        returnValue.setCmd(cmdEntity);
        returnValue.setMessageType(MessageType.CMD4008.getType());
        returnValue.setResult(CmdResp.SUCCESS.getCode());
        returnValue.setDesc(CmdResp.SUCCESS.getDesc());
        return returnValue;
    }

    /**
     *解析4008
     * @param  data 待解析数据
     */
  public ReturnValue cmd4008Dcompose(String data){

      String resp =data.substring(data.length()-18,data.length()-16);
      ReturnValue returnValue = new ReturnValue();

      returnValue.setMessageType(MessageType.CMD4008.getType());
      if("00".equals(resp)){
          returnValue.setResult(CmdResp.SUCCESS.getCode());
          returnValue.setDesc(CmdResp.SUCCESS.getDesc());
      }else{
          returnValue.setResult(resp);
          returnValue.setDesc("4008返回错误码");
      }
      return returnValue;
  }



    public  String MAC(String Key,String data){
        if("".equals(Key)){
            return "0000000000000000";
        }
        return "0000000000000000";
    }

}
