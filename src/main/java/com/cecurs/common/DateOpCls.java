package com.cecurs.common;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateOpCls {

	public DateOpCls()
	{
	}

	public static String DateToFormat(String date, int type)
	{
		Date temp = Date.valueOf(date);
		SimpleDateFormat df = null;
		switch (type)
		{
		case 0: // '\0'
			df = new SimpleDateFormat("yyyyMMdd");
			break;

		case 1: // '\001'
			df = new SimpleDateFormat("yyyy/MM/dd");
			break;

		case 2: // '\002'
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;

		case 5: // '\005'
			df = new SimpleDateFormat("yyyy��MM��dd��");
			break;

		case 6: // '\006'
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;

		case 7: // '\007'
			df = new SimpleDateFormat("yyyy-M-d");
			break;

		case 3: 
			df = new SimpleDateFormat("yyMMddHHmmss");
          break;
		case 4: 
			df = new SimpleDateFormat("yyyyMMdd");
			break;
		case 8:
			df= new SimpleDateFormat("MMddHHmmss");
			break;
		default:
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;
		}
		return df.format(temp);
	}
	public static String DateToFormat(String format,String date, int type)
	{
		SimpleDateFormat s = new SimpleDateFormat(format);
		java.util.Date temp=null;
		try {
			temp = s.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat df = null;
		switch (type)
		{
		case 0: // '\0'
			df = new SimpleDateFormat("yyyyMMdd");
			break;

		case 1: // '\001'
			df = new SimpleDateFormat("yyyy/MM/dd");
			break;

		case 2: // '\002'
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;

		case 5: // '\005'
			df = new SimpleDateFormat("yyyy��MM��dd��");
			break;

		case 6: // '\006'
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;

		case 7: // '\007'
			df = new SimpleDateFormat("yyyy-M-d");
			break;

		case 3: 
			df = new SimpleDateFormat("yyMMddHHmmss");
          break;
		case 4: 
			df = new SimpleDateFormat("yyyyMMdd");
			break;
		case 8:
			df= new SimpleDateFormat("MMddHHmmss");
			break;
		case 9:
			df= new SimpleDateFormat("yyMMdd");
			break;
		case 10:
			df= new SimpleDateFormat("HHmmss");
			break;
		default:
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;
		}
		return df.format(temp);
	}
	public static String DateToFormat(java.util.Date date, int type)
	{
		SimpleDateFormat df = null;
		switch (type)
		{
		case 0: // '\0'
			df = new SimpleDateFormat("yyyyMMdd");
			break;

		case 1: // '\001'
			df = new SimpleDateFormat("yyyy/MM/dd");
			break;

		case 2: // '\002'
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;

		case 5: // '\005'
			df = new SimpleDateFormat("yyyy��MM��dd��");
			break;

		case 15: // '\017'
			df = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��");
			break;

		case 16: // '\020'
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;

		case 6: // '\006'
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;

		case 3: 
			df = new SimpleDateFormat("yyMMddHHmmss");
           break;
		case 4:
			df = new SimpleDateFormat("yyyyMMdd");
			break;
		case 7: // '\007'
		case 8: 
			df= new SimpleDateFormat("MMddHHmmss");
			break;
		case 9: 
			df= new SimpleDateFormat("HHmmss");
			break;
		case 10: // '\n'
		case 11: // '\013'
		case 12: // '\f'
		case 13: // '\r'
		case 14: // '\016'
		default:
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;
		}
		if (date == null)
			return "";
		else
			return df.format(date);
	}
	
	/*
	 * �껻������
	 * 
	 */
	
	public static String getCC(){
		String d = new SimpleDateFormat("yyyy").format(new java.util.Date());
		int s = Integer.parseInt(d.substring(0, 2));
		return ""+(s+1);
	}
	
	public static void main(String[] args) {
		System.out.println(getCC());
	}
}
