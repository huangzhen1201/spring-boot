package com.hz.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import java.io.File;
import java.time.Clock;

/**
 * 文件上传
 * Created by HZ-PC on 2018/4/2.
 */
public class FileUtil {

    // 头像
    public static final String HEAD_IMAGES_FOLDER = "head_img";
    // 其它照片
    public static final String OTHER_IMAGES_FOLDER = "other_img";

    @Value("${web.base.upload-path}")
    private String baseFileUploadPath;


    /**
     * 上传头像图片
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadHeadImg(File file) throws Exception {
        return upload(file, HEAD_IMAGES_FOLDER);
    }

    /**
     * 上传其它图片
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadOtherImg(File file) throws Exception {
        return upload(file, OTHER_IMAGES_FOLDER);
    }

    /**
     * 上传文件，返回文件名
     * @param file
     * @return
     * @throws Exception
     */
    public String upload(File file, String folderName) throws Exception {
        if (file != null) {
            String oldFileName = file.getName();
            StringBuilder newFileName = new StringBuilder(folderName);
            newFileName.append(File.separator).append(Clock.systemUTC().millis()).append(".");
            if (oldFileName.contains(".")) {
                newFileName.append(oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length()));
            } else {
                newFileName.append("jpg");
            }
            FileCopyUtils.copy(file, new File(baseFileUploadPath + File.separator + newFileName));
            return newFileName.toString();
        }
        return null;
    }
}