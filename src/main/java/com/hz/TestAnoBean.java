package com.hz;

/**
 * Created by HZ-PC on 2018/3/30.
 */
public class TestAnoBean {

    @AnnotationTest
    public void test1(){
        System.out.println("1+1=" + (1+1));
    }

    @AnnotationTest
    public void test2(){
        String xx = null;
        System.out.println("null" + xx.length());
    }

    @AnnotationTest
    public void test3(){
        System.out.println("1+1=" + (1/0));
    }

    @AnnotationTest
    public void test4(){
        System.out.println("1*1=" + (1*1));
    }

    public void test5(){
        System.out.println("HaHa");
    }

    public void test6(){
        System.out.println("WoWo");
    }
}
