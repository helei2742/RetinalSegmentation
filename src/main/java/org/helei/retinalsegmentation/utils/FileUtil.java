package org.helei.retinalsegmentation.utils;


import cn.hutool.core.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class FileUtil {
    /**
     * 获取文件名
     * @param url
     * @return
     */
    public static String getFileFromUrl(String url){
        String[] split = separator.equals("/")?url.split(separator):url.split("\\\\");
        return split[split.length-1];
    }


    /**
     * 保存文件
     * @param file 文件
     * @param path
     * @return  返回保存路径
     * @throws IOException
     */
    public static String saveFile(MultipartFile file, String path, String fileName) throws IOException {

        if(file==null) return "";
//        String fileName = file.getOriginalFilename();
//
//        fileName = getSaveFileName(fileName);
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        f = new File(f, fileName);
        file.transferTo(f);
        return f.getAbsolutePath();
    }


    public static void createPath(String path){
        File file = new File(path);
        if(!file.exists()) file.exists();
    }

    /**
     * 获取保存时的文件名
     * @param fileName
     * @return
     */
    public static String getSaveFileName(String fileName){
        return "Src_"+ RandomUtil.randomNumbers(4) +"_" + System.currentTimeMillis() + fileName;
    }

    /**
     * 清除文件夹
     * @param file
     */
    public static void deleteFile(File file){
        if (file == null || !file.exists()){
            return;
        }
        File[] files = file.listFiles();
        if(files != null){
            for (File f: files){
                if (f.isDirectory()){
                    deleteFile(f);
                }else {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    /**
     * base64转换为byte[]
     * @param base64
     * @return
     */
    public static byte[] base64ToByte(String base64){
        if (base64 == null) // 图像数据为空
            return null;
        base64 = base64.split("base64,")[1];
        return Base64.getDecoder().decode(base64);
    }

    public static boolean generateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        imgData = imgData.split("base64,")[1];
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = Base64.getDecoder().decode(imgData);

            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(out !=null){
                out.flush();
                out.close();
            }
        }
        return true;
    }


    public static String contextPath;
    public static String separator;
    static {
        contextPath = System.getProperty("user.dir");
        separator =  File.separator;
    }

    /**
     * 从保存的原图片路径，得到处理结果的文件名
     * @param src
     * @return
     */
    public static String getResFileNameFromSrcPath(String src) {

        String[] split = separator.equals("/")?src.split(separator):src.split("\\\\");
        return split[split.length-1].replaceFirst("Src", "Res");
    }

    /**
     * 获取临时用户上传图片路径
     * @param idNumber
     * @return
     */
    public static String getTempUploadSrcImagePath(String idNumber) {
        return contextPath + separator+"images"+separator+"temp"+separator+"srcImages"+separator+ idNumber;
    }

    /**
     * 获取临时用户结果图片路径
     * @param idNumber
     * @return
     */
    public static String getTempResultImgPath(String idNumber) {
        return contextPath + separator+"images"+separator+"temp"+separator+"resImages"+separator+ idNumber;
    }


    /**
     * 获取注册用户上传图片路径
     * @param username
     * @return
     */
    public static String getUserUploadSrcImagePath(String username) {
        return contextPath + separator+ "images"+separator+"user"+separator+username+separator+"srcImages";
    }

    /**
     * 获取组册用户结果图片路径
     * @param username
     * @return
     */
    public static String getUserUploadResImagePath(String username) {
        return contextPath +separator+"images"+separator+"user"+separator+username+separator+"resImages";
    }

    /**
     * 获取磁盘路径
     * @param path
     * @return
     */
    public static String getFilePathFromCTP(String path) {
        return contextPath + separator + path;
    }

    /**
     *  /images/temp/srcImages/67177330073665542/Src_167198654608353DX.jpg
     *  这样的url路径转换为物理路径
     * @param srcImgPath
     * @return
     */
    public static String getTruePath(String srcImgPath) {
        return contextPath + srcImgPath;
    }

    /**
     * 物理路径换成 /images/temp/srcImages/67177330073665542/Src_167198654608353DX.jpg 这样的路径
     * @param truePath
     * @return
     */
    public static String getSourcePath(String truePath) {
        String substring = truePath.substring(contextPath.length());
        System.out.println(substring);
        return truePath.substring(contextPath.length());
    }
}
