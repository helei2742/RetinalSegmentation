package org.helei.retinalsegmentation.service;

import org.helei.retinalsegmentation.dto.Result;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * 注册用户处理图片
     * @param userId
     * @param recordId
     * @return
     */
    Result userImgSegmentation(Long userId, Long recordId);

    /**
     * 对分割结果进一步处理，画出可能有问题的区域
     */
    void imgDetection(String imgPath, HttpServletResponse response);

    /**
     * 将分割结果描绘到原图上
     * @param recordId
     */
    void imgCoincide(Long recordId, HttpServletResponse response);
}
