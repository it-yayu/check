package com.test.check.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeInsertTest {
    public static void main(String[] args) throws SQLException {
        PreparedStatement state=null;
        Connection conn=null;
        //1.加载驱动包
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接对象
             conn = DriverManager.getConnection("jdbc:mysql://192.168.0.213:3306/low_log_scheam?useUnicode=true&characterEncoding=UTF-8","root" , "root123");
            //4.创建sql命令
            for (int i = 0; i <45; i++) {
                String sql = " insert into type_value (fid, id, name,type,remarks) values (?,?,convert(unhex(hex(convert( ? using utf8))) using latin1),?,?)";
                List<String>list=getFileContext();
                String name=list.get(i);
                //3.连接sql命令对象
                state = conn.prepareStatement(sql);
                state.setString(1,null);
                state.setString(2, String.valueOf(i));
                state.setString(3,name);
                state.setString(4,"1");
                state.setString(5,null);
                //5.执行sql命令
                int i1 = state.executeUpdate();
                System.out.println(i1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //6.关闭相关资源
            state.close();
            conn.close();
        }




    }

    /**
     * 获取txt文件内容并按行放入list中
     **/
    public static List<String> getFileContext() throws Exception {
        FileReader fileReader = new FileReader("D:\\name.txt");
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
