package com.hz;

import org.junit.Test;
import org.springframework.boot.system.JavaVersion;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by HZ-PC on 2018/3/29.
 */

public class TestUtil {
    public static void main(String[] args){
        System.out.println("使用JAVA版本：" + JavaVersion.getJavaVersion());
        //streamTest();
        annotationTest();
    }

    /**
     * 并行数组
     */
    public static void parallexXxx() {
        long[] arrayOfLong = new long [ 20000 ];

        // 添加20000个元素
        Arrays.parallelSetAll( arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt( 1,10000000 ) );

        // 取前15个字符输出
        Arrays.stream( arrayOfLong ).limit( 15 ).forEach(
                i -> System.out.print( i + ", " ) );
        System.out.println();

        // 排序
        Arrays.parallelSort( arrayOfLong );

        // 取前15个字符输出
        Arrays.stream( arrayOfLong ).limit( 15 ).forEach(System.out::println);
        System.out.println();
    }

    /**
     * base64 加密解密
     *
     */
    @Test
    public void base64Test() {
        // 文字解密加密 Start
        String hz = "黄振";
        String encodeHz = Base64.getEncoder().encodeToString(hz.getBytes(StandardCharsets.UTF_8));
        System.out.println(hz + " ， 长度=" + hz.length() + ", 加密后：" + encodeHz + ", 长度=" + encodeHz.length());

        String decodeHz = new String(Base64.getDecoder().decode(encodeHz.getBytes(StandardCharsets.UTF_8)));
        System.out.println(encodeHz + ", 解密后=" + decodeHz);
        // 文字解密加密 End

        // 图片解密加密 Start

        // 原图片绝对路径
        String imgPath = "C:\\Users\\HZ-PC\\Pictures\\Saved Pictures\\ChMkJllpytWITJfoAO7DcypuYNIAAerLwDi86cA7sOL007.jpg";
        Path path = Paths.get(imgPath);
        try {
            // 读取路径文件的字节
            byte[] imgBytes = Files.readAllBytes(path);
            // 获取图片的加密字符
            String imgEncodeStr = Base64.getEncoder().encodeToString(imgBytes);
            System.out.println(imgEncodeStr.substring(0, 20) + "…………" +
                    imgEncodeStr.substring(imgEncodeStr.length() - 20, imgEncodeStr.length())
                    + " 等" + imgEncodeStr.length() + "个字符");
            // 加密完成

            // 解密开始
            // 获取加密字符的字节
            byte[] decodeBytes = Base64.getDecoder().decode(imgEncodeStr.getBytes());

            // 目标图片路径
            String newPath = "C:\\Users\\HZ-PC\\Pictures\\Saved Pictures\\decode_new.jpg";
            Path decodePath = Paths.get(newPath);
            // 写入图片文件
            Files.write(decodePath, decodeBytes);
            File newFile = new File(newPath);
            if (newFile.exists()) {
                System.out.println(newFile.getPath() +"__"+ newFile.length() / 1024 + " KB");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getImageStr(String imgPath) {
        File file = new File(imgPath);
        if (!file.exists()) {
            // 加载默认图片
            imgPath = "C:\\Users\\HZ-PC\\Pictures\\Saved Pictures\\decode_new.png";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }
    /**
     * Stream测试
     */
    public static void streamTest() {
        List<ErrorInfo> errorList = Arrays.asList(new ErrorInfo(11, "a"), new ErrorInfo(3, "b") ,
                new ErrorInfo(51, "a"), new ErrorInfo(7, "b"));

        errorList.sort((a, b) -> b.getReturnCode() - a.getReturnCode());
        System.out.print("倒序排序一 : ");
        errorList.forEach(ei -> System.out.print(ei.getReturnCode() + " "));

        errorList.sort(Comparator.comparingInt(ErrorInfo::getReturnCode));
        System.out.print("\n顺序排序二 : ");
        errorList.forEach(ei -> System.out.print(ei.getReturnCode() + " "));

        System.out.println("\n筛选returnText为b的returnCode统计:");
        int sumCodeByTextIsA = errorList.stream().filter(ei -> ei.getReturnText().equals("a")).mapToInt(ErrorInfo::getReturnCode).sum();
        System.out.println(sumCodeByTextIsA);

        Map<String, List<ErrorInfo>> groupMap = errorList.stream().collect(Collectors.groupingBy(ErrorInfo::getReturnText));
        System.out.println("分组统计:");
        System.out.println(groupMap);

        groupMap.forEach((k, v) -> {
            System.out.print(k +":");
            System.out.print(v.stream().parallel().mapToInt(ErrorInfo::getReturnCode).reduce(0,Integer::sum)+" ");
        });

        System.out.println("\n统计所有:");
        int allCodeSum =  errorList.stream().parallel().map(ei -> ei.getReturnCode()).reduce(0, Integer::sum);
        System.out.println(allCodeSum);

        // Lambda表达式 -- 匿名内部类
        LongAccumulator accumulatorByLambda = new LongAccumulator((left, right) -> left + right, 0);
        // 普通内部类，等同于上面
        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
                @Override
                public long applyAsLong(long left, long right) {
                    return left + right;
                }
            }, 0);
        int[] intArray = new int[]{1,3,5,7,9};
        System.out.println("intArray");
        System.out.println(Arrays.stream(intArray).sum());
        errorList.stream().filter(ei -> ei.getReturnText().equals("b")).forEach(ei -> System.out.print(ei.getReturnCode() + ", "));

    }

    public static void annotationTest() {
        TestAnoBean testobj = new TestAnoBean();
        Class clazz = testobj.getClass();
        Method[] method = clazz.getDeclaredMethods();
        //用来记录测试产生的 log 信息
        StringBuilder log = new StringBuilder();
        // 记录异常的次数
        int errornum = 0;
        for ( Method m: method ) {
            // 只有被 @AnnotationTest 标注过的方法才进行测试
            if ( m.isAnnotationPresent(AnnotationTest.class)) {
                try {
                    m.setAccessible(true);
                    m.invoke(testobj, null);

                } catch (Exception e) {
                    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }


        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errornum);
        log.append(" error.");

        // 生成测试报告
        System.out.println(log.toString());

    }
}
