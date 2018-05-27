package com.hz.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 进程控制
 * <p>
 * Created by HZ-PC on 2018/5/28.
 */
public class OSExecute {

    private static final String CHARSET_NAME = "GBK";

    public static void main(String[] args) {
        command("echo This is a echo command!");
        command("java -version");
        command("wrongCommand This is a wrong command!");
    }

    /**
     * 执行系统指令
     *
     * @param command
     */
    public static void command(String command) {
        String osName = System.getProperty("os.name");
        if (osName != null && osName.toLowerCase().contains("windows")) {
            // Windows系统要加CMD，不让会报错
            command = "CMD /C " + command;
        }
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream(), CHARSET_NAME));
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(), CHARSET_NAME));
            while ((s = errors.readLine()) != null) {
                System.out.println(s);
                if (!err) {
                    err = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (err) {
            System.out.println(command + " 该指令执行失败");
        }
    }
}
