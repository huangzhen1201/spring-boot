package com.hz.thread;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 实现Runnable接口创建线程
 * Created by HZ-PC on 2018/3/30.
 */
public class ImplementsThread implements Runnable {
    private static int threadCount = 0;
    private int threadNumber;
    private int[] intArrays = new int[5];
    public ImplementsThread() {
        threadCount ++ ;
        threadNumber = threadCount;
        System.out.println("创建第 " + threadNumber + " 个线程……");
        Arrays.parallelSetAll(intArrays, index -> ThreadLocalRandom.current().nextInt(1, 20));
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < intArrays.length; i++) {
                System.out.println("第 " + threadNumber + " 个线程 第" + (i + 1) + "次执行，休眠" + intArrays[i] + "秒。");
                Thread.sleep(intArrays[i] * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*Arrays.stream(intArrays).forEach(i -> {
            System.out.print("第 " + threadNumber + " 个线程");
            try {
                sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        System.out.println("第 " + threadNumber + " 个线程执行完成！");
    }
}
