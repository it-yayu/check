package com.test.check.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HGQU {
    public static void main(String[] args) throws  Exception{

int y=0;
        for (int i = 10001; i <=10343; i++) {
            String s="{\"ConfArea\":\"{\"confid\":\""+y+"\",\"areaid\":\""+i+"\",\"areaname\":\""+getFileContext().get(y)+"\",\"starttime\":\"null\"}}";
            System.out.println(s);
            y++;
        }

    }

    //获取数据库
    public static List<String> getFileContext() throws Exception {
        FileReader fileReader = new FileReader("F:\\HG.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> list = new ArrayList<String>();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            if (str.trim().length() > 0) {
                list.add(str);
            }
        }
        return list;
    }
}
