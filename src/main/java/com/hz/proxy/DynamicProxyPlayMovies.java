package com.hz.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * Created by HZ-PC on 2018/3/30.
 */
public class DynamicProxyPlayMovies implements InvocationHandler {

    private Object movies;

    public DynamicProxyPlayMovies(Object mv) {
        this.movies = mv;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("瞧一瞧看一看了啊，电影要开始啦");
        Object res = method.invoke(movies, args);
        System.out.println("观影完毕！");
        return res;
    }
}
