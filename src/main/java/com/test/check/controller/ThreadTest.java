package com.test.check.controller;

public class ThreadTest implements Runnable {
    private String areaList;
    public  ThreadTest(String areaList){
        this.areaList=areaList;

    }


    @Override
    public void run() {
        System.out.println(areaList);
    }
}
