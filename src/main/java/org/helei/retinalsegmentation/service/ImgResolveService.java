package org.helei.retinalsegmentation.service;

import org.helei.retinalsegmentation.dto.Result;

public interface ImgResolveService {

    /**
     * 传入初始图像路径，调用python脚本，处理完图像后返回Result包含结果路径
     * @param srcImgPath
     * @return
     */
    Result imgSegmentation(String srcImgPath);

    /**
     * 查看是否有切割完的结果
     * @param srcImgPath
     * @return
     */
    Result isHaveSegmentation(String srcImgPath);
}
