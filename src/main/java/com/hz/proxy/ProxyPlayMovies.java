package com.hz.proxy;

/**
 * 静态代理
 * Created by HZ-PC on 2018/3/30.
 */
public class ProxyPlayMovies implements Movies {
    private PlayMovies mv;

    public ProxyPlayMovies(PlayMovies mv){
        this.mv = mv;
    }

    public void play(){
        System.out.println("爆米花啦，98折卖啦！");
        mv.play();
        System.out.println("电影好看吗？给个好评哦！");
    }
}
