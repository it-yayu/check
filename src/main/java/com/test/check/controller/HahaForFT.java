package com.test.check.controller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HahaForFT {
    public static void main(String[] args) {

        SAXReader saxReader = new SAXReader();
        Document document;
        File file=null;
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
                    for (Element childElement : allChildElement) {
                    // #5.1 打印元素名
                    String childEleName = childElement.getName();
//                    处理所有的dataHost元素里面的内容
//                    if ("dataNode".equals(childEleName)){
//                        String name = childElement.attributeValue("name");
//                        String database = childElement.attributeValue("database");
//                       System.out.println(database);
//
//                    }
                    if("dataHost".equals(childEleName)){
                        // 方式1：获得元素对象，然后获得文本
                        List<Element> dataHostNameElement = childElement.elements("writeHost");
                        System.out.println(dataHostNameElement.size());
                        for (Element dateElement : dataHostNameElement) {
                            String url = dateElement.attributeValue("url").split(":")[0];
                            String user=dateElement.attributeValue("user");
                            String password=dateElement.attributeValue("password");
                            str="<ip:"+url+",user:"+user+",password:"+password+",database:"+getFileContext().get(i)+">";
                            System.out.println(str);
                            i++;

                        }
                    }


                }


            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }


    }

//获取数据库
public static List<String> getFileContext() throws Exception {
    FileReader fileReader = new FileReader("F:\\databases.txt");
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

