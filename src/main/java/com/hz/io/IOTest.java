package com.hz.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;

/**
 * IO 学习
 *
 * Created by HZ-PC on 2018/5/27.
 */
public class IOTest {
    private static final String FILE_NAME = "D:/UNNGroup/test.txt";

    private static final String FILE_NAME2 = "D:/UNNGroup/test2.txt";

    private static final String FILE_NAME3 = "D:/UNNGroup/test3.txt";

    private static final String FILE_NAME_RANDOMACCESSFILE = "D:/UNNGroup/RandomAccessFile.txt";

    public static void main(String[] args) {
        try {
            System.out.println("------写字符串到文件--------");
            bufferedWriterTest();
            System.out.println("------快捷输出到文件------");
            printWriterTest();
            System.out.println("------输出文件的字符串------");
            bufferedReaderTest();
            System.out.println("------输出字符串------");
            stringReaderTest();
            System.out.println("------读取字节------");
            dataInputStreamTest();
            System.out.println("------存储和恢复数据------");
            dataOutputStreamTest();
            System.out.println("------RandomAccessFile------");
            randomAccessFileTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedReader 读取文件
     *
     * @throws Exception
     */
    public static void bufferedReaderTest() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        br.close();
    }

    /**
     * BufferedWriter 写入文件 new FileWriter(FILE_NAME, true) 追加方式写入文件
     *
     * @throws Exception
     */
    public static void bufferedWriterTest() throws Exception {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        // 虽然FileWriter 也能直接写入文件，但是通常用BufferedWriter缓冲来包装FileWriter以获得更好的I/O性能,FileReader亦是如此
        BufferedWriter bw = new BufferedWriter(fw);

        bw.newLine();
        bw.write("这里有很棒的美食-Apple");
        bw.newLine();
        bw.write("这里有很棒的美食-Banana");
        bw.flush();
        bw.close();
    }

    /**
     * PrintWriter 快捷输入到文件，也用到了缓冲，效果和bufferedWriterTest一样
     *
     * @throws Exception
     */
    public static void printWriterTest() throws Exception {
        String str = "PrintWriter\nTest\n好好学习\n天天向上";
        File file = new File(FILE_NAME2);
        PrintWriter pw = new PrintWriter(file);
        pw.print(str); // print 最终还是调用write函数，只是如果str为null的时候print会输出字符串null,而write回报错
        pw.flush();
        pw.close();
    }

    /**
     * StringReader 从内存输出字符串
     *
     * @throws Exception
     */
    public static void stringReaderTest() throws Exception {
        StringReader sr = new StringReader("我是一个字符串");
        int c;
        while ((c = sr.read()) != -1) {
            System.out.println((char) c);
        }
        sr.close();
    }

    /**
     * DataInputStream
     *
     * @throws Exception
     */
    public static void dataInputStreamTest() throws Exception {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream("DataInputStream Test 嘿嘿".getBytes("UTF-8")));
        while(dis.available() != 0) {
            System.out.println((char) dis.readByte());
        }
        dis.close();
    }

    /**
     * DataOutputStream and DataInputStream 存储和恢复数据，此时文件打开看到的数据是乱码状态，但是可以读取正常
     *
     * @throws Exception
     */
    public static void dataOutputStreamTest() throws Exception {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME3)));
        dos.writeDouble(123.123);
        dos.writeInt(11);
        dos.writeUTF("DataOutputStream");
        dos.writeUTF("汉字");
        dos.flush();
        dos.close();
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME3)));
        System.out.println(dis.readDouble());
        System.out.println(dis.readInt());
        System.out.println(dis.readUTF());
        System.out.println(dis.readUTF());
        dis.close();
    }

    /**
     * RandomAccessFile 使用，类似组合的DataInputStream和DataOutputStream
     *
     * @throws Exception
     */
    public static void randomAccessFileTest() throws Exception {
        // rw应该是表示操作文件的权限 有 r, rw, rws, rwd 四种权限类型
        RandomAccessFile raf = new RandomAccessFile(FILE_NAME_RANDOMACCESSFILE, "rw");
        raf.writeDouble(123456.123456);
        raf.writeInt(99);
        raf.writeDouble(1.1);
        raf.writeChar((int) 'v');
        raf.writeUTF("RandomAccessFile结束字符");
        raf.close();
        raf = new RandomAccessFile(FILE_NAME_RANDOMACCESSFILE, "r");
        System.out.println(raf.readDouble());
        System.out.println(raf.readInt());
        System.out.println(raf.readDouble());
        System.out.println(raf.readChar());
        System.out.println(raf.readUTF());
        raf.close();
        raf = new RandomAccessFile(FILE_NAME_RANDOMACCESSFILE, "rw");
        raf.seek(8 + 4); // 一个double是8个字节，一个int是4个字节，修改第三个double类型就是移动到12的位置
        // 修改1.1为111.111
        raf.writeDouble(111.111);
        raf.close();
        raf = new RandomAccessFile(FILE_NAME_RANDOMACCESSFILE, "r");
        System.out.println(raf.readDouble());
        System.out.println(raf.readInt());
        System.out.println(raf.readDouble());
        System.out.println(raf.readChar());
        System.out.println(raf.readUTF());
        raf.close();
    }

}
