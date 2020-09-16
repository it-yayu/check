package com.test.check.controller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class XMLpaseForHB {
    public static void main(String[] args) {
        SAXReader saxReader = new SAXReader();
        Document document;
        String str="";
        try {
            //获取document对象
            document = saxReader.read(new File("F:\\schema.xml"));
            //获取根元素
            Element rootElement = document.getRootElement();
            //获取version属性
            String version = rootElement.attributeValue("version");
            //#3 获得所有子元素。例如：<servlet>/<servlet-mapping>
            List<Element> allChildElement = rootElement.elements();
            //#4 遍历所有
            int i=0;
            BufferedWriter rd=new BufferedWriter(new FileWriter("D:\\check\\777.txt"));
            for (Element childElement : allChildElement) {
                // #5.1 打印元素名
                String childEleName = childElement.getName();
//                    处理所有的dataHost元素里面的内容
                    if ("dataNode".equals(childEleName)){
                        String name = childElement.attributeValue("name");
                        String url=childElement.attributeValue("dataHost");
                        String database = childElement.attributeValue("database");
                        str="<ip:"+url+",user:"+"glog"+",password:"+"KD5@pw89Q"+",database:"+database+">";
                        rd.write(str);
                        rd.newLine();
                        System.out.println(str);

                    }

            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }
}
