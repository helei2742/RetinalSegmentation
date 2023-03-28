package org.helei.retinalsegmentation.service.impl;

import cn.hutool.json.JSONUtil;
import org.helei.retinalsegmentation.common.threadlocal.IdNumberHolder;
import org.helei.retinalsegmentation.config.RabbitMQConfig;
import org.helei.retinalsegmentation.dto.MQDTO;
import org.helei.retinalsegmentation.dto.Result;
import org.helei.retinalsegmentation.entity.User;
import org.helei.retinalsegmentation.entity.UserUploadRecord;
import org.helei.retinalsegmentation.service.IUserService;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.helei.retinalsegmentation.service.ImgResolveService;
import org.helei.retinalsegmentation.service.PythonService;
import org.helei.retinalsegmentation.utils.DetectionUtil;
import org.helei.retinalsegmentation.utils.FileUtil;
import org.helei.retinalsegmentation.utils.RedisConstants;
import org.helei.retinalsegmentation.utils.ValidateCodeUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;


@Service
public class ImgResolveServiceImpl implements ImgResolveService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserUploadRecordService uploadRecordService;


    @Override
    public Result imgSegmentation(String srcImgPath) {

        String trueSrcPath = FileUtil.getTruePath(srcImgPath);
        String idNumber = IdNumberHolder.getIdNumber();

        String resultBase = FileUtil.getTempResultImgPath(idNumber);
        String resImgPath = resultBase + "/" + FileUtil.getResFileNameFromSrcPath(srcImgPath);

        MQDTO mq = new MQDTO(false, trueSrcPath, resImgPath);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_NAME,
                "info", JSONUtil.toJsonStr(mq));

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


    @Override
    public Result userImgSegmentation(Long userId, Long recordId) {
        //参数校验
        User one = userService.query().eq("id", userId).select("is_valid","username").one();
        if(one == null || !one.getIsValid()) {
            return Result.fail("当前用户不存在或非法");
        }

        UserUploadRecord record = uploadRecordService.query().eq("id", recordId).one();

        if(record == null) {
            return Result.fail("不存在该记录");
        }

        //TODO 用消息队列异步处理 注意事物，，麻烦就懒得写了
        boolean update = uploadRecordService.update()
                .eq("id", recordId).set("state", 1).update();
        if(!update) {
            return Result.fail("请求分割失败");
        }

        //调用脚本，处理图像，返回处理结果path
        String srcLocation = FileUtil.getTruePath(record.getSrcLocation());
        String resImageBase = FileUtil.getUserUploadResImagePath(one.getUsername());
        String resImgPath = resImageBase + "/" + FileUtil.getResFileNameFromSrcPath(record.getSrcLocation());

        MQDTO mq = new MQDTO(true, srcLocation, resImgPath);
        mq.setRecordId(recordId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE_NAME,
                "info", JSONUtil.toJsonStr(mq));


        return Result.ok(record);
    }


    @Override
    public void imgDetection(String imgPath, HttpServletResponse response) {
        try {
//            System.out.println(imgPath);
            BufferedImage img = DetectionUtil.imgDetection(FileUtil.getTruePath(imgPath));
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ImageIO.write(img, "PNG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
