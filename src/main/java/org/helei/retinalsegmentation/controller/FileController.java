package org.helei.retinalsegmentation.controller;

import cn.hutool.core.util.StrUtil;
import org.helei.retinalsegmentation.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/fileDownload")
    public void downloadResImage(String contextPath,
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
