package com.test.check.controller;

import com.test.check.utils.DeleteSql;
import com.test.check.utils.GetDate;
import com.test.check.utils.SaveSql;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@RestController
@Slf4j
public class CheckController  {
    List<String> dblist= null;
    List<String> list = new ArrayList<>();
    File file=null;
    //测试数据库是否连接
    @RequestMapping("/test")
    public  void test() {
//        ExecutorService pool = new ThreadPoolExecutor( 10,
//        30,
//        10,
//        TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        System.out.println("开始测试");

        try {
             dblist = getFileContext("D:\\name.txt");
             file = new File("D:/check/out.txt");
            if (!file.exists()){
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            ExecutorService pool = Executors.newCachedThreadPool();
            for (final String d : dblist) {

                Runnable run = new Runnable() {
                    public void run() {
                        Map m = (Map) getValue(d);
                        String ip = m.get("ip").toString();
                        System.out.println(ip);
                        String database = m.get("database").toString();
                        String username = m.get("user").toString();
                        String password = m.get("password").toString();
                        String URL = "jdbc:mysql://" + ip + ":3306/" + database + "?useSSL=false";
                        try {
                            String mes = CheckController.isCon(URL, username, password,database);
                            if (mes != null && !"".equals(mes)) {
                                System.out.println(mes + "=====================================");
                                list.add(mes + ":-----> " + ip + ":" + database);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            BufferedWriter rd = new BufferedWriter(new FileWriter(file));
                            for (String s : list) {
                                try {
                                    rd.write(s + "\r\n");
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
                };
                pool.execute(run);


            }
            pool.shutdown();
        } catch (Exception e) {
            //
        }
        }


    //进行数据删除
    @RequestMapping("/delete")
    public void deleteDbList() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (final String d : dblist) {
            System.out.println(d);
            Runnable run = new Runnable() {
                    public void run() {
                    Map m = (Map) getValue(d);
                    String ip = m.get("ip").toString();
                    String database = m.get("database").toString();
                    String username = m.get("user").toString();
                    String password = m.get("password").toString();
                    String URL = "jdbc:mysql://" + ip + ":3306/" + database + "?useSSL=false";
                    try {
                        String message = CheckController.isCon_delete(URL, username, password);
                        if (message != null && !"".equals(message)) {
                            System.out.println(message + "======================================");
                            list.add(message + ":-----> " + ip + ":" + database+"删除数据失败!");
                        }else
                        {
                            System.out.println(message + "======================================");
                            list.add(message + ":-----> " + ip + ":" + database+"删除数据成功!");
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
            };
            pool.execute(run);

        }
        System.out.println("[1] done!");
        pool.shutdown();
    }


    //进行数据备份
    @RequestMapping("/save")
    public  void saveDate(){
        try {
            if (list.size()!=0&&list!=null){
                list.clear();
            }

            if (!file.exists()){
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            for (final String d : dblist) {
             /*   new Thread(){
                    @Override
                    public void run() {*/
                        Map m = (Map) getValue(d);
                        String ip = m.get("ip").toString();
                        String database = m.get("database").toString();
                        String username = m.get("user").toString();
                        String password = m.get("password").toString();
                        String URL = "jdbc:mysql://" + ip + ":3306/" + database + "?useSSL=false";
                        try {
                            String message = CheckController.isCon_save(URL, username, password,ip);
                            if (message != null && !"".equals(message)) {
                                System.out.println(message + "======================================");
                                list.add(message + ":-----> " + ip + ":" + database+"数据库备份失败!");
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
             /*   }.start();

            }*/

        }catch (Exception e){
            e.printStackTrace();
        }



    }




    //判断连接数据库和备份数据
    public static String isCon_save(String URL, String username, String password,String ip) throws Exception {
        String conmess = "";
        String conmess1 = "";
        Connection conn = null;
        List<String> sqlList=new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            conmess = "Connect Successful.";
            conmess = "Cant't load Driver";
        }
        try {
            DriverManager.setLoginTimeout(5); //设置连接时间为5秒中，5秒还没有响应就认为连接失败
            conn = DriverManager.getConnection(URL, username, password);
            //数据库连接成功,进行数据库的备份
            String r = GetDate.getUid();
            System.out.println(r);
            SaveSql.exportDatabaseTool(ip,username,password,"D:/check","low_log_schema"+"_"+ GetDate.getDate()+"_"+r,"low_log_schema");
            conmess = "Connect Successful.";
        } catch (Exception e) {
            conmess1 = "Connect fail:" + e.getMessage();
        } finally {
            try {
                conn.close();
            } catch (Exception e2) {
                conmess1 = "Close Connection error.";
            }
        }
        return conmess1;
    }








    //判断连接数据库和删除
    public static String isCon_delete(String URL, String username, String password) throws Exception {
        String conmess = "";
        String conmess1 = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            conmess = "Connect Successful.";
            conmess = "Cant't load Driver";
        }
        try {
            DriverManager.setLoginTimeout(5); //设置连接时间为5秒中，5秒还没有响应就认为连接失败
            conn = DriverManager.getConnection(URL, username, password);
            //数据库连接成功,进行数据分区的删除
            List<String> sqlList = DeleteSql.deleteSql();
            for (String s : sqlList) {
                try {
                    PreparedStatement ps = conn.prepareStatement(s);
                    ps.execute();
                }catch (Exception e){
                    continue;
                }

            }
            conmess = "Connect Successful.";
        } catch (Exception e) {
            conmess1 = "Connect fail:" + e.getMessage();
        } finally {
            try {
                conn.close();
            } catch (Exception e2) {
                conmess1 = "Close Connection error.";
            }
        }
        return conmess1;
    }



    /**
     * 测试是否连接
     **/
    public static String isCon(String URL, String username, String password,String databases) throws Exception {
        String conmess = "";
        String conmess1 = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            conmess = "Connect Successful.";
            conmess = "Cant't load Driver";
        }
        try {
                    DriverManager.setLoginTimeout(3); //设置连接时间为三秒中，三秒还没有响应就认为连接失败
                    conn = DriverManager.getConnection(URL, username, password);
                    String sql = "select  sn from  cross_server_boss_kill_log limit 10";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.executeQuery(sql);
                    conmess = "Connect Successful.";
                return conmess1;
        } catch (Exception e) {
            conmess1 = "Connect fail:" + e.getMessage();
        } finally {
            try {
                conn.close();
            } catch (Exception e2) {
                conmess1 = "Close Connection error.";
            }
        }
        return conmess1;
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


//    @Override
//    public void run(ApplicationArguments args){
//        test();
//    }
}
