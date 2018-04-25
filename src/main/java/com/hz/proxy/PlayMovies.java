package com.hz.proxy;

/**
 * Created by HZ-PC on 2018/3/30.
 */
public class PlayMovies implements Movies {
    @Override
    public void play() {
        System.out.println("开始放映《爱乐之城》！请大家文明观影！");
    }
}
