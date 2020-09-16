package com.test.check.utils;

import java.util.ArrayList;
import java.util.List;

public class DeleteSql {
    public  static  List<String> deleteSql() {
        List<String> sqlList = new ArrayList<>();
        //item_change_log
        String sql1 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190608 ";
        String sql2 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190616 ";
        String sql3 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190624 ";
        String sql4 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190701 ";
        String sql5 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190708 ";
        String sql6 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190716 ";
        String sql7 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190724 ";
        String sql8 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_201908 ";
        String sql9 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190801 ";
        String sql10 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190808 ";
        String sql11 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190816 ";
        String sql12 = "ALTER TABLE item_change_log DROP PARTITION item_change_log_20190824 ";
        //money_change_log
        String sql13 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_201905 ";
        String sql14 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190608 ";
        String sql15 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190616 ";
        String sql16 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190624 ";
        String sql17 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190701 ";
        String sql18 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190708 ";
        String sql19 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190716 ";
        String sql20 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190724 ";
        String sql21 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_201908 ";
        String sql22 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190801 ";
        String sql23 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190808 ";
        String sql24 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190816 ";
        String sql25 = "ALTER TABLE money_change_log DROP PARTITION money_change_log_20190824 ";
//            String sql33="ALTER TABLE money_change_log DROP PARTITION money_change_log_20190901 " ;
//            String sql34="ALTER TABLE money_change_log DROP PARTITION money_change_log_20190908 " ;
        //role_time_limit_activity_log
        String sql26 = "ALTER TABLE role_time_limit_activity_log DROP PARTITION role_time_limit_activity_log_201907 ";
        String sql27 = "ALTER TABLE role_time_limit_activity_log DROP PARTITION role_time_limit_activity_log_201908 ";
        sqlList.add(sql1);
        sqlList.add(sql2);
        sqlList.add(sql3);
        sqlList.add(sql4);
        sqlList.add(sql5);
        sqlList.add(sql6);
        sqlList.add(sql7);
        sqlList.add(sql8);
        sqlList.add(sql9);
        sqlList.add(sql10);
        sqlList.add(sql11);
        sqlList.add(sql12);
        sqlList.add(sql13);
        sqlList.add(sql14);
        sqlList.add(sql15);
        sqlList.add(sql16);
        sqlList.add(sql17);
        sqlList.add(sql18);
        sqlList.add(sql19);
        sqlList.add(sql20);
        sqlList.add(sql21);
        sqlList.add(sql22);
        sqlList.add(sql23);
        sqlList.add(sql24);
        sqlList.add(sql25);
        sqlList.add(sql26);
        sqlList.add(sql27);

        return sqlList;

    }}
