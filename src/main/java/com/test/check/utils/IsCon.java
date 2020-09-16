package com.test.check.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
public class IsCon {

    /**
     * 测试是否连接
     **/
    public static String isCon(String URL, String username, String password,int time,String database) throws Exception {
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
            //获取到每个库中需要添加分区的表
            List<String> forList = getFileContext("D://form.txt");
            //遍历表添加分区
            for (String form:forList) {
                con(URL,username,password,form,database,time,form);
//                String sql="";
//                try {
//                    DriverManager.setLoginTimeout(3); //设置连接时间为三秒中，三秒还没有响应就认为连接失败
//                    conn = DriverManager.getConnection(URL, username, password);
//                     sql = "alter table  "+form+" add PARTITION  ( PARTITION "+form+"_"+IsCon.getLastDayOfMonth(time)+"  VALUES  LESS  THAN  (to_days("+"'"+IsCon.getFirstDayOfMonth(time)+"'"+")))";
//                     PreparedStatement preparedStatement = conn.prepareStatement(sql);
//                    preparedStatement.execute(sql);
//                    conmess = "Connect Successful.";
//                    log.info(database+"====>"+"数据库执行添加分区成功~~"+form+"========"+sql);
//  //                  log.info("alter table  "+form+" add PARTITION  ( PARTITION "+form+"_"+IsCon.getLastDayOfMonth(time)+"  VALUES  LESS  THAN  (to_days("+"'"+IsCon.getFirstDayOfMonth(time)+"'"+")))");
//                }catch (Exception e){
//                    log.error(database+"====>"+"数据库执行添加分区失败!!!!!!"+form+"========"+sql);
// //                   log.error("alter table  "+form+" add PARTITION  ( PARTITION "+form+"_"+IsCon.getLastDayOfMonth(time)+"  VALUES  LESS  THAN  (to_days("+"'"+IsCon.getFirstDayOfMonth(time)+"'"+")))");
//                    continue;
//                }
//                continue;
            }
        } catch (Exception e) {
            conmess1 = "Connect fail:" + e.getMessage();
        } finally {
            try {
                conn.commit();
                conn.close();
            } catch (Exception e2) {

            }
        }
        return conmess1;
    }
    public static void con(String url, String user, String password,String table,String databases,int time,String form) throws Exception {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接

            connection = DriverManager.getConnection(url, user, password);
            // 获取statement，preparedStatement
           String sql = "SELECT partition_name part, partition_expression expr, partition_description descr, table_rows FROM information_schema. PARTITIONS WHERE table_schema = SCHEMA () AND table_name = '"+table+"' ORDER BY descr DESC LIMIT 1;";
  //       String sql = "alter table  "+form+" add PARTITION  ( PARTITION "+form+"_"+IsCon.getLastDayOfMonth(time)+"  VALUES  LESS  THAN  (to_days("+"'"+IsCon.getFirstDayOfMonth(time)+"'"+")))";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                    preparedStatement.execute(sql);
            // 执行查询
           rs = preparedStatement.executeQuery(sql);

 //               preparedStatement.execute(sql);
  //              log.info(databases+"====>"+"数据库执行添加分区成功~~"+form+"========"+sql);

            // 处理结果集
            while (rs.next()) {
                System.out.println("数据库名:"+databases+"分区名称："+rs.getString("part"));
                System.out.println("数据库名:"+databases+"分区值："+rs.getString("descr"));
                if(rs.getString("descr")!=null) {
                    Integer descr = Integer.valueOf(rs.getString("descr"));
                    if(descr<time){
                        log.info("数据库名:"+databases+"====>"+rs.getString("part")+"账号名"+user+password);
                    }
                }else {
                    log.error("数据库名:"+databases+"====>"+table);
                }

            }
        } catch (Exception e){
//            log.error(databases+"====>"+"数据库执行添加分区失败!!!!!!"+form);
        }finally {
            // 关闭连接，释放资源
            if (rs != null) {
                rs.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("account_create_log_"+IsCon.getLastDayOfMonth(4));
        System.out.println(IsCon.getFirstDayOfMonth(12));
    }


    public static  String getLastDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 2);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth.replaceAll("-", "");
    }

    public static String getFirstDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 查询所有的表
     */
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


}
