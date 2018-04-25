package com.hz.proxy;

/**
 * Created by HZ-PC on 2018/3/30.
 */
public class StopMovie implements Movies {

    @Override
    public void play() {
        System.out.println("欢迎收看《复仇者联盟》TV秀！");
        System.out.println("哎呀！影片停止了！");
    }
}
