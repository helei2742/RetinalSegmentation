package org.helei.retinalsegmentation.controller;


import org.helei.retinalsegmentation.common.threadlocal.IdNumberHolder;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.service.ImgResolveService;
import org.helei.retinalsegmentation.service.InitService;
import org.helei.retinalsegmentation.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private InitService initService;

    @Autowired
    private ImgResolveService imgResolveService;

    /**
     * 获取临时用户id， 该id就代表了当前链接的浏览器客户端
     * @return
     */
    @GetMapping("/idNumber")
    public Result getIdNumber(){
        return initService.getIdNumber();
    }

    /**
     * 获取验证码
     * @param response
     */
    @GetMapping("/validCode")
    public void getValidCode(HttpServletResponse response){
       initService.getCaptchaImg(response);
    }

    /**
     * 验证验证码
     * @return
     */
    @PostMapping("/verifyValidCode")
    public Result verifyValidCode(@RequestBody Map<String,String> params) {
        String code = params.get("validCode");

        return initService.verifyValidCode(code);
    }

    /**
     * 上传图片文件
     * @param file
     * @return
     */
    @PostMapping("/uploadSrcImage")
    public Result uploadSrcImage(MultipartFile file){
        return initService.uploadSrcImage(file);
    }


    /**
     * 获取临时用户上传图片url
     * @return
     */
    @PostMapping("/getSrcImgURL")
    public Result getSrcImgURL(){
        return initService.getSrcImageList();
    }

    @PostMapping("/downloadImg")
    public void downloadImg(String path, HttpServletResponse response){
        initService.downloadImage(path, response);
    }

    /**
     * 临时用户处理图像
     * @param srcImgPath
     * @return
     */
    @PostMapping("/tempSegmentation")
    public Result imgSegmentation(String srcImgPath) {
        return imgResolveService.imgSegmentation(srcImgPath);
    }


    /**
     * 临时用户查看原图是否有切割完的图像
     */
    @PostMapping("/isHaveSegmentation")
    public Result isHaveSegmentation(String srcImgPath) {
        return imgResolveService.isHaveSegmentation(srcImgPath);
    }
}
