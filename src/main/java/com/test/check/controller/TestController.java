package com.test.check.controller;

import com.test.check.utils.GetIp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class TestController {
    public static String getAddressByIP(String strIP) {
        try {
            URL url = new URL("http://freeapi.ipip.net/"+strIP);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//	            conn.addHeader("x-forwarded-for","150.109.235.95");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            String ipAddr = result.toString();
            return ipAddr;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "读取失败";
        }
    }

    public static void main(String[] args) {
        int count = 100;
        for (int i = 0; i < count; i++) {
            System.out.println(getAddressByIP(GetIp.getRandomIp()));
        }

    }

}


