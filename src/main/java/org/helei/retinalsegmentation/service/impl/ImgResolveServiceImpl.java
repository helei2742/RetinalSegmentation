package org.helei.retinalsegmentation.service.impl;

import org.helei.retinalsegmentation.common.threadlocal.IdNumberHolder;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.service.ImgResolveService;
import org.helei.retinalsegmentation.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImgResolveServiceImpl implements ImgResolveService {


    @Override
    public Result imgSegmentation(String srcImgPath) {

        String trueSrcPath = FileUtil.getTruePath(srcImgPath);
        String idNumber = IdNumberHolder.getIdNumber();

        String resultBase = FileUtil.getTempResultImgPath(idNumber);
        String resImgPath = resultBase + "/" + FileUtil.getResFileNameFromSrcPath(srcImgPath);

        //todo 调用脚本，处理图像，返回处理结果
        Path source = Paths.get(trueSrcPath);
        Path target = Paths.get(resultBase);
        try {
            Files.createDirectories(target);
            Files.copy(source, Paths.get(resImgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok(resImgPath.split(FileUtil.contextPath)[1]);
    }

    @Override
    public Result isHaveSegmentation(String srcImgPath) {
        String idNumber = IdNumberHolder.getIdNumber();
        String resultBase = FileUtil.getTempResultImgPath(idNumber);
        String resImgPath = resultBase + "/" + FileUtil.getResFileNameFromSrcPath(srcImgPath);
        File file = new File(resImgPath);
        if(file.isFile()) {
            return Result.ok(resImgPath.split(FileUtil.contextPath)[1]);
        }
        return Result.fail("没有该原图片的分割结果");
    }
}
