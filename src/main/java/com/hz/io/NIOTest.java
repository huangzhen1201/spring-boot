package com.hz.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 新IO 从jdk 1.4版本开始引入，目的在于提高速度，旧的I/O包其实也已经被重新实现过了，即使不显式的用nio编码，也能从中受益
 * Created by HZ-PC on 2018/5/28.
 */
public class NIOTest {

    /**
     * 旧I/O中有三个类被修改了，可以产生通道channel, FileOutputStream、FileInputStream、RandomAccessFile
     *
     * @throws Exception
     */
    @Test
    public void channelTest() throws Exception{
        String text = "你好\nHello\nMojo\n123";
        Charset charset = Charset.forName("UTF-8");
        String fileName = "D:/test_file/channel.txt";
        FileChannel fc;
        fc = new FileOutputStream(fileName).getChannel();
        // 写入
        fc.write(ByteBuffer.wrap(text.getBytes(charset)));
        fc.close();
        fc = new RandomAccessFile(fileName, "rw").getChannel();
        fc.position(fc.size()); // 移动到最后
        fc.write(ByteBuffer.wrap("\nAppend Line".getBytes(charset)));
        fc.close();
        fc = new FileInputStream(fileName).getChannel();
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.allocate(1024);
        //byteBuffer = ByteBuffer.allocateDirect(1024); // allocateDirect速度更快，但是开支会更大
        fc.read(byteBuffer); // fc向byteBuffer存储字节
        byteBuffer.flip(); // 完成缓冲，必须调用
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
        fc.close();
    }

    /**
     * 两个通道（Channel）相连
     *
     * @throws Exception
     */
    @Test
    public void channelTransferTo() throws Exception {
        String fileName1 = "F:/图片/tencentnews/1fa69ab673acab93b843a988abe73b63.jpg";
        String fileName2 = "D:/test_file/channel_transfer.jpg";
        FileChannel inChannel = new FileInputStream(fileName1).getChannel(),
                    outChannel = new FileOutputStream(fileName2).getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        // 或者 outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }
}
