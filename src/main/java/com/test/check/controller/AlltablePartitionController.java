package com.test.check.controller;

import com.test.check.utils.GetFileContextUtil;
import com.test.check.utils.IsCon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class AlltablePartitionController {
    List<String> dblist= null;
    List<String> list = new ArrayList<>();
    File file=null;
    @RequestMapping("/AlltablePartition")
    public String AlltablePartition(int time){
        ExecutorService pool = Executors.newCachedThreadPool();
        try {
            dblist = GetFileContextUtil.getFileContext("D:\\name.txt");
            file = new File("D:/check/out.txt");
            if (!file.exists()){
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            int i=0;
            for (final String d : dblist) {
//                Runnable run = new Runnable() {
//                    public void run() {
                        Map m = (Map) GetFileContextUtil.getValue(d);
                        String ip = m.get("ip").toString();
                        String database = m.get("database").toString();
                        String username = m.get("user").toString();
                        String password = m.get("password").toString();
                        String URL = "jdbc:mysql://" + ip + ":3306/" + database + "?useSSL=false";
                        try {
                            String mes = IsCon.isCon(URL, username, password, time,database);
                            if (mes != null && !"".equals(mes)) {
                                System.out.println(mes + "===============");
                                list.add(mes + ":---> " + ip + ":" + database);
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
                        i++;
                    }
//                };
//                pool.execute(run);
//            }
//            pool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return "执行完成!";
    }
}
