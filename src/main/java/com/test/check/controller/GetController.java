package com.test.check.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetController {

    List<String> dblist= null;
    List<String> list = new ArrayList<>();
    File file=null;

    //获取数据的格式
    @RequestMapping("/getFormt")
    public  void test() {
        try {
            dblist = getFileContext("D:\\dbpath.txt");
            file = new File("D:\\out.txt");
            if (!file.exists()){
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            for (final String d : dblist) {

                       Map m = (Map) getValue(d);
                        String ip = m.get("ip").toString();
                        String database = m.get("database").toString();
                        String username = m.get("user").toString();
                        String password = m.get("password").toString();
                        String URL = "jdbc:mysql://" + ip + ":3306/" + database + "?useSSL=false";
                        try {
                            String mes = CheckController.isCon(URL, username, password,database);
                            if (mes!=null&&!"".equals(mes)) {
                                System.out.println(mes + "======================================");
                                list.add(mes + ":-----> " + ip+":"+database);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            BufferedWriter rd=new BufferedWriter(new FileWriter(file));
                            for (String s : list) {
                                try {
                                    rd.write(s+"\r\n");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                rd.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }





        } catch (Exception e) {
            //
        }
    }

    /**
     * 获取txt文件内容并按行放入list中
     **/
    public static List<String> getFileContext(String path) throws Exception {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> list = new ArrayList<String>();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            if (str.trim().length() > 2) {
                list.add(str);
            }
        }
        return list;
    }

    //转换为map
    public static Object getValue(String param) {
        Map map = new HashMap();
        String str = "";
        String key = "";
        Object value = "";
        char[] charList = param.toCharArray();
        boolean valueBegin = false;
        for (int i = 0; i < charList.length; i++) {
            char c = charList[i];
            if (c == '<') {
                if (valueBegin == true) {
                    value = getValue(param.substring(i, param.length()));
                    i = param.indexOf('}', i) + 1;
                    map.put(key, value);
                }
            } else if (c == ':') {
                valueBegin = true;
                key = str;
                str = "";
            } else if (c == ',') {
                valueBegin = false;
                value = str;
                str = "";
                map.put(key, value);
            } else if (c == '>') {
                if (str != "") {
                    value = str;
                }
                map.put(key, value);
                return map;
            } else if (c != ' ') {
                str += c;
            }
        }
        return map;
    }


}
