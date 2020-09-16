package com.test.check.controller;


import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        for (int i = 6; i < 360; i++) {
//            if(i==91||i==121||i==151) {
//                i+=29;
//            }
//            if (i==181) {
//                i=360;
//            }
            System.out.println("nCell = nRow.createCell("+i+");");
            System.out.println("nCell.setCellValue(au.get(i).getDayLtv"+(i-4)+"().toString());");
        }
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);


    }



}
