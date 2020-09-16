package com.test.check.api;

import org.junit.Test;

public class BcTest1 {
    // 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？   
    //这是一个菲波拉契数列问题
    @Test
    public void test1() {
        System.out.println("第1个月的兔子对数:1");
        System.out.println("第2个月的兔子对数:1");
        int f1 = 1, f2 = 1, f, M = 24;
        for (int i = 3; i <= M; i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println("第" + i + "个月的兔子对数: " + f2);
        }
    }
//    题目：判断101-200之间有多少个素数，并输出所有素数。
//    程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除， 则表明此数不是素数，反之是素数。
    @Test
    public void test2() {
        int count = 0;



    }

}
