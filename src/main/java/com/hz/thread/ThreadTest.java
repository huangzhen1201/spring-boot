package com.hz.thread;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by HZ-PC on 2018/3/30.
 */
public class ThreadTest {

    public static void main(String[] args){
        System.out.print("Enter a Char:");
        char i = 0;
        try {
            i = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("your char is :"+i);
    }

    /**
     * 多线程线程测试
     */
    @Test
    public void threadTest(){
        ExtendsThread et1 = new ExtendsThread();
        ExtendsThread et2 = new ExtendsThread();
        ExtendsThread et3 = new ExtendsThread();
        et1.setDaemon(true); // 设置守护线程
        et1.setPriority(9); // 设置优先级
        et1.start();
        et2.setPriority(5);
        et2.start();
        et3.setPriority(2);
        et3.start();
        System.out.println("et1 isAlive: " + et1.isAlive());
        System.out.println("et2 isAlive: " + et2.isAlive());
        System.out.println("et3 isAlive: " + et3.isAlive());
        // new Thread(ImplementsThread::new).start();
        // 要判断所有线程是否执行完成，不然主程序完成后子线程也不会执行（这里是Junit,所有test完成之后就代表程序测试完成，线程啥的就不会继续执行了）
        /*while (!et1.getState().equals(Thread.State.TERMINATED) || !et2.getState().equals(Thread.State.TERMINATED) || !et3.getState().equals(Thread.State.TERMINATED)) {
        }*/
        while (et1.isAlive() || et2.isAlive() || et3.isAlive()){
            // 还有线程活着
        }
        System.out.println("所有线程执行完毕！");
    }

    @Test
    public void threadPool() throws IOException {
        int taskSize = 3;
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            final int callableNumber = i + 1;
            Callable c = () -> {
                Thread.sleep(1000);
                return "线程 " + callableNumber + " 返回结果。";
            };
            list.add(pool.submit(c));
        }
        pool.shutdown();
        //获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            try {
                System.out.println(">>>" + f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
