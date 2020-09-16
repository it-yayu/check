package com.test.check.controller;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    public static void main(String[] args) {
        try {
            List<String> text = UtilsTest.getText();
            for (String s : text) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i <=90; i++) {
            System.out.println("if("+i+"==dd.getDayFlag())");
            System.out.println("dayltv.setDayLtv"+i+"(dd.getDayLtv());");
        }


    }

    /**
     * 高效字符缓冲流
     * @return
     * @throws Exception
     */

    public static List<String> getText() throws Exception{

            List<String> list=new ArrayList<>();
            BufferedReader bf=new BufferedReader(new InputStreamReader(new FileInputStream("E:\\schema.xml")));
            BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\hah.xml")));
            String str;
            while ((str=bf.readLine())!=null){
//                bw.write(str+"\n");
                list.add(str+"\n");
            }
            bf.close();
            bw.close();
        return list;
    }

    /**
     * 高效字节缓冲流
     * @throws Exception
     */
   public static void getxxx() throws Exception{
       Long begin=System.currentTimeMillis();
        BufferedInputStream bi=new BufferedInputStream(new FileInputStream("D:\\all_low_log_schema.sql"));
        BufferedOutputStream bo=new BufferedOutputStream(new FileOutputStream("E:\\all_low_log_schema.sql"));

        int len;
        byte[] bs=new byte[1024];
        while ((len=bi.read(bs))!=-1){
            bo.write(bs,0,len);
       }
        bi.close();
        bo.close();
       System.out.println(System.currentTimeMillis()-begin);

   }


}
