package org.helei.retinalsegmentation.service;


import org.helei.retinalsegmentation.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface InitService extends FileService{
    public void getCaptchaImg(HttpServletResponse response);

    Result getIdNumber();

    Result verifyValidCode(String validCode);

}
