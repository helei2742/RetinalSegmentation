package org.helei.retinalsegmentation.service.impl;

import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.service.ImgResolveService;
import org.helei.retinalsegmentation.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImgResolveServiceImpl implements ImgResolveService {


    @Override
    public Result imgSegmentation(String srcImgPath, String targetPath) {
        String resFilePath = targetPath + "/" + FileUtil.getResFileNameFromSrcPath(srcImgPath);

        //todo 调用脚本，处理图像，返回处理结果
        Path source = Paths.get(srcImgPath);
        Path target = Paths.get(targetPath);
        try {
            Files.createDirectories(target);
            Files.copy(source, Paths.get(resFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Result.ok(resFilePath);
    }
}
