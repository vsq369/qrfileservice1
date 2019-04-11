package com.cecurs.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

/**
 * 解析工具类
 *
 * 解析交通部返回的对账文件
 * @author  wangjc
 *
 */

@Slf4j
@Component
public class AnalysisSvr {

    @Value("file.downFilePath")
    private  String downFilePath ;

    /**
     *   sourceDir 资源文件
     *   文件格式均为 流水格式 三行一组数据
     *   LD 文件  消费已处理 详见 城市公共交通IC卡技术规范第四部分：信息接口20150331.pdf 6.2.2.1
     *   第一行文件说明区 01 版本号
     *   第二行 交易头
     *   第三行 交易数据体
     *   CL文件 脱机消费清算明细文件 详见 城市公共交通IC卡技术规范第四部分：信息接口20150331.pdf  6.2.2.2
     *   第一行 文件说明 01 版本号
     *   第二行 交易头
     *   第三行 交易数据体
     *   FB 文件
     */


    public boolean unPuckFile(String inncode,String sourceDir,String[] Msg){

        BufferedReader read = null;

        String oldpath = downFilePath+""+sourceDir;
        String newpath = downFilePath+"_bak/"+sourceDir;

        File file = new File(oldpath);
        try {
            read = new BufferedReader(new FileReader(file));
            String dataTemp = "";
            int line = 1;
            if("FB".equals(sourceDir.substring(0,2))){
               //解析fb文件

                while((dataTemp=read.readLine())!=null){
                    boolean flag = resloveFB( sourceDir, line, dataTemp, Msg);
                    if(flag==false){
                        Msg[0] = "解析FB文件失败:"+sourceDir;
                        return false;
                    }
                    line++;
                }
            }else if("CL".equals(sourceDir.substring(0,2))){
                //脱机消费明细文件
                while((dataTemp=read.readLine())!=null){
                    boolean temp = resloveCL(sourceDir,line, dataTemp,Msg);
                    if(temp==false){
                        Msg[0] = "解析CL文件失败:"+sourceDir;
                        return false;
                    }
                    line++;
                }
            }else{
                Msg[0] = "无此解析类型:"+sourceDir;
                return false;
            }

            Msg[2]=String.valueOf(line);

            boolean copyFlag = copyFile(oldpath, newpath);
            if(copyFlag){
                if (file.isFile()) {
                    boolean f = file.delete();
                   log.info(new Date()+" "+"删除"+f);
                }
            }



        }catch (Exception e){
           log.error(e.getMessage());
        }finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Msg[0] = "成功:"+new Date();
        return true;
    }



    /**
     * 解析FB文件
     */

    public static boolean resloveFB(String fileName,long rowNum,String rowsData,String Msg[]){

        byte[] data = rowsData.getBytes();
        if(rowNum==1){
            //第一行版本号
            System.out.println("文件名"+fileName+"版本号"+rowsData);
            return true;
        }else if(rowNum==2){
            String recount = rowsData.substring(0,6);
            int   i_recount = Integer.parseInt(recount); //6 记录数
            String rq = rowsData.substring(6, 14);//8 YYYYMMDD
            String r_inncode = rowsData.substring(14,25).trim();//11 接收机构代码
            String rowLen = rowsData.substring(25,29).trim();//4 单笔交易数据长度 不包括TVL
            int i_rowLen = Integer.parseInt(rowLen);
            String reData = rowsData.substring(29,49);//20 保留全F
            Msg[0] = rowLen;
           // boolean flag = FileDao.getInstall().saveFileInfo(conn, fileName, i_recount, rq, r_inncode, i_rowLen, reData);
           // return flag;
            return true;
        }else{
            //解析数据题
            try{
                String lsh = rowsData.substring(0,12);//流水号1
                String r_lsh = rowsData.substring(12,24);//收单机构流水号2
                String r_rq = rowsData.substring(24,32);//收单机构受理日期3
                String syh = rowsData.substring(32,44); //检索参考号4
                String jylx = rowsData.substring(44,48).trim();//交易类型5
                String r_Mark_code = rowsData.substring(48,59).trim();//接收清算机构标识码6
                String s_card_mark_code = rowsData.substring(59,70).trim();//发卡地通卡公司代码11
                //新增
                String sdjgbsm = rowsData.substring(70,81);//收单机构标识码11
                String fsjgdm =rowsData.substring(81,92);//发送机构代码
                //新增结束
                String MCC = rowsData.substring(92,96);//MCC4
                String qdlx = rowsData.substring(96,98);//渠道类型
                String cardNo = rowsData.substring(98,118).trim();//卡号
                String cardNum = rowsData.substring(118,124);//卡片消费计数器
                int cardN = Integer.parseInt(cardNum);
                String card_befor_Ye = rowsData.substring(124,136);//消费前余额 12
                double cardbye = Double.parseDouble(card_befor_Ye)/100;
                String card_DJ =rowsData.substring(136,148) ;//交易金额
                double dj = Double.parseDouble(card_DJ)/100;
                System.out.println("dj"+dj);
                if(Msg[1]!=null){
                    double je = Double.parseDouble(Msg[1]);
                    je+=dj;
                    Msg[1] = String.valueOf(je);
                }
                String card_rq = rowsData.substring(148,162);//交易时间 yyyyMMdd hhmiss
                String err_code = rowsData.substring(162,168);//错误代码

                byte[] err_des_byte = new byte[40];
                System.arraycopy(data, 168, err_des_byte, 0, 40);
                String err_des = new String(err_des_byte).trim();
                byte[] test_lx_byte = new byte[1];
                System.arraycopy(data, 208, test_lx_byte, 0, 1);
                String test_lx = new String(test_lx_byte);//测试类型 0正式数据 1 测试数据
                //新增
                byte[] sxf_byte = new byte[28];
                System.arraycopy(data,209,sxf_byte,0,sxf_byte.length);
                String sxf = new String(sxf_byte);//手续费   28

                if("".equals(sxf.trim())){
                    sxf = "0";
                }
                double sxf_d = Double.parseDouble(sxf)/100;

                byte[] fkfr_byte = new byte[28];//发卡分润
                System.arraycopy(data,237,fkfr_byte,0,fkfr_byte.length);
                String fkfr = new String(fkfr_byte);
                if("".equals(fkfr.trim())){
                    fkfr = "0";
                }
                double fkfr_d = Double.parseDouble(fkfr)/100;

                byte[] sdfr_byte = new byte[28];//收单分润
                System.arraycopy(data,265,sdfr_byte,0,sdfr_byte.length);
                String sdfr = new String(sdfr_byte);
                if("".equals(sdfr.trim())){
                    sdfr = "0";
                }
                double sdfr_d =  Double.parseDouble(sdfr)/100;
                System.out.println("sdfr_d"+sdfr_d);
                byte[] yl_byte = new byte[28];//预留
                System.arraycopy(data,293,yl_byte,0,yl_byte.length);
                String yl = new String(yl_byte);
                System.out.println(yl);
                if(yl==null || "".equals(yl.trim())){
                    yl = "0";
                }
                double yl_d =  Double.parseDouble(yl)/100;
                //新增结束
                byte[] tlv_byte = new byte[40];
                System.arraycopy(data,321,tlv_byte,0,tlv_byte.length);
                String resverve = new String(tlv_byte);

               // boolean flag = FileDao.getInstall().saveFBData(conn,rowsData, lsh, r_lsh, r_rq, syh, jylx, r_Mark_code, s_card_mark_code, MCC, qdlx, cardNo, cardN, cardbye, card_rq, err_code, err_des, test_lx, resverve, fileName,dj,sdjgbsm,fsjgdm,sxf_d,fkfr_d,sdfr_d,yl_d );
                return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
    }



    /**
     *
     * @param rowsData
     * @return
     * 解析CL文件
     */
    private static boolean resloveCL(String fileName,long rowNum,String rowsData,String Msg[]){
        rowsData = rowsData.replaceAll("灰\\?", "交易 ");
        System.out.println(rowsData);
        byte[] data = rowsData.getBytes();
        if(rowNum==1){
            //第一行版本号
            System.out.println("文件名"+fileName+"版本号"+rowsData);
            return true;
        }else if(rowNum==2){
            String recount = rowsData.substring(0,6);
            int   i_recount = Integer.parseInt(recount); //6 记录数
            String rq = rowsData.substring(6, 14);//8 YYYYMMDD
            String r_inncode = rowsData.substring(14,25).trim();//11 接收机构代码
            String rowLen = rowsData.substring(25,29).trim();//4 单笔交易数据长度 不包括TVL
            int i_rowLen = Integer.parseInt(rowLen);
            String reData = rowsData.substring(29,49);//20 保留全F
            Msg[0] = rowLen;
           /// boolean flag = FileDao.getInstall().saveFileInfo(conn, fileName, i_recount, rq, r_inncode, i_rowLen, reData);
            return true;
        }else{
            //解析数据题
            try{

                String lsh = rowsData.substring(0,12);//流水号
                String r_lsh = rowsData.substring(12,24);//收单机构流水号
                String r_rq = rowsData.substring(24,32);//收单机构受理日期
                String syh = rowsData.substring(32,44); //检索参考号
                String jylx = rowsData.substring(44,48).trim();//交易类型
                String r_Mark_code = rowsData.substring(48,59).trim();//收单机构标识码
                String r_code = rowsData.substring(59,70).trim();//收单机构代码

                String s_card_mark_code = rowsData.substring(70,81).trim();//发卡地通卡公司代码
                //新增
                String sdjgbsm = rowsData.substring(81,92);//收单机构标识码11
                String fsjgdm =rowsData.substring(92,103);//发送机构代码
                //新增结束

                String MCC = rowsData.substring(103,107);//MCC
                String qdlx = rowsData.substring(107,109);//渠道类型
                String cardNo = rowsData.substring(109,129).trim();//卡号
                String cardNum = rowsData.substring(129,135);//卡片消费计数器
                int cardN = Integer.parseInt(cardNum);
                String card_befor_Ye = rowsData.substring(135,147);//消费前余额
                double cardbye = Double.parseDouble(card_befor_Ye)/100;

                String card_DJ =rowsData.substring(147,159) ;//交易金额

                double dj = Double.parseDouble(card_DJ)/100;
                if(Msg[1]!=null){
                    double je = Double.parseDouble(Msg[1]);
                    je+=dj;
                    Msg[1] = String.valueOf(je);
                }

                String card_rq = rowsData.substring(159,173);//交易时间 yyyyMMdd hhmiss
                String ye_lx = rowsData.substring(173,174);//余额类型 0 电子钱包 1电子现金
                String A_lx = rowsData.substring(174,176);//算法标示
                String err_code = rowsData.substring(176,182);//错误代码
                byte[] err_des_byte = new byte[40];
                System.arraycopy(data, 182, err_des_byte, 0, 40);
                String err_des = new String(err_des_byte).trim();
                byte[] test_lx_byte = new byte[1];
                System.arraycopy(data, 222, test_lx_byte, 0, 1);
                String test_lx = new String(test_lx_byte);//测试类型 0正式数据 1 测试数据


                //新增
                byte[] sxf_byte = new byte[28];
                System.arraycopy(data,223,sxf_byte,0,sxf_byte.length);
                String sxf = new String(sxf_byte);//手续费   28

                if("".equals(sxf.trim())){
                    sxf = "0";
                }
                double sxf_d = Double.parseDouble(sxf)/100;

                byte[] fkfr_byte = new byte[28];//发卡分润
                System.arraycopy(data,251,fkfr_byte,0,fkfr_byte.length);
                String fkfr = new String(fkfr_byte);

                if("".equals(fkfr.trim())){
                    fkfr = "0";
                }
                double fkfr_d = Double.parseDouble(fkfr)/100;

                byte[] sdfr_byte = new byte[28];//收单分润
                System.arraycopy(data,279,sdfr_byte,0,sdfr_byte.length);
                String sdfr = new String(sdfr_byte);
                if("".equals(sdfr.trim())){
                    sdfr = "0";
                }

                double sdfr_d =  Double.parseDouble(sdfr)/100;
                byte[] yl_byte = new byte[28];//预留
                System.arraycopy(data,307,yl_byte,0,yl_byte.length);
                String yl = new String(yl_byte);
                if("".equals(yl.trim())){
                    yl = "0";
                }
                double yl_d =  Double.parseDouble(yl)/100;
                //新增结束
                //System.out.println();
                byte[] tlv_byte = new byte[data.length-325];
                System.arraycopy(data,325,tlv_byte,0,tlv_byte.length);
                String TLV = new String(tlv_byte);
               // boolean flag = FileDao.getInstall().saveCLData(conn,rowsData, lsh, r_lsh, r_rq, syh, jylx, r_Mark_code, r_code, s_card_mark_code, MCC, qdlx, cardNo, cardN, cardbye, card_rq, ye_lx, A_lx, err_code, err_des, test_lx, TLV, fileName,dj,sdjgbsm,fsjgdm,sxf_d,fkfr_d,sdfr_d,yl_d);
                return true;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

    }



    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
