package org.helei.retinalsegmentation.service;

import cn.hutool.core.util.StrUtil;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.utils.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public interface FileService {
    /**
     * 上传原图片
     * @param file
     * @return
     */
    Result uploadSrcImage(MultipartFile file);

    /**
     * 获取上传图片url列表
     * @return
     */
    default Result getSrcImageList(){
        return null;
    }

    /**
     * 下载图片
     * @param contextPath
     * @param response
     */
    default void downloadImage(String contextPath,
                                 HttpServletResponse response){
        if(StrUtil.isBlank(contextPath)) {
            return;
        }

        String filePath = FileUtil.getFilePathFromCTP(contextPath);

        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try {
            fis = new FileInputStream(filePath);

            String filename = "Result_" + FileUtil.getFileFromUrl(filePath);

            String type = new MimetypesFileTypeMap().getContentType(filename);
            response.setHeader("Content-type",type);
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
            response.setHeader("Access-Control-Expose-Headers", "filename");
            response.setHeader("filename", URLEncoder.encode(filename,"utf-8"));
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename,"utf-8"));

            sos = response.getOutputStream();
            int len=0;
            byte[] bytes=new byte[1024];
            while((len=fis.read(bytes))!=-1){
                sos.write(bytes,0,len);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(fis != null)
                    fis.close();
                if(sos != null)
                    sos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
