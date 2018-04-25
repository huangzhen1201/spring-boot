package com.hz.proxy;


import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理
 * Created by HZ-PC on 2018/3/30.
 */
public class TestClass {

    /**
     * 静态代理
     */
    @Test
    public void staticProxyTest(){
        PlayMovies playMovies = new PlayMovies();

        Movies movies = new ProxyPlayMovies(playMovies);
        movies.play();
    }

    /**
     * 动态代理
     */
    @Test
    public void dynamicProxyTest() {
        PlayMovies playMovies = new PlayMovies();
        InvocationHandler dppm = new DynamicProxyPlayMovies(playMovies);
        Movies startMv = (Movies) Proxy.newProxyInstance(PlayMovies.class.getClassLoader(), PlayMovies.class.getInterfaces(), dppm);
        startMv.play();

        StopMovie stopMovie = new StopMovie();
        InvocationHandler stop = new DynamicProxyPlayMovies(stopMovie);
        Movies stopMv = (Movies) Proxy.newProxyInstance(PlayMovies.class.getClassLoader(), PlayMovies.class.getInterfaces(), stop);
        stopMv.play();

        System.out.println("startMv class name:" + startMv.getClass().getName());
        System.out.println("stopMv class name:" + stopMv.getClass().getName());
    }
}
