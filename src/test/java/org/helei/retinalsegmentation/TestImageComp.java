package org.helei.retinalsegmentation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestImageComp {

    public static void main(String[] args) throws IOException {
        String srcPath = "/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/src_img.png";
        String resPath = "/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/res_img.png";
        BufferedImage bufferedImage = imgComp(srcPath, resPath);
        ImageIO.write(bufferedImage, "png",
                new File("/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/comp_img.png"));
    }

    public static void before() throws IOException {
        String img = "/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/img.png";
        BufferedImage srcImg = ImageIO.read(new File(img));
        int sW = srcImg.getWidth(), sH = srcImg.getHeight();
        BufferedImage src = new BufferedImage(sW/4, sH, BufferedImage.TYPE_INT_RGB);
        BufferedImage res = new BufferedImage(sW/4, sH, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < sW / 4; i++) {
            for (int j = 0; j < sH; j++) {
                src.setRGB(i, j, srcImg.getRGB(i, j));
            }
        }
        for (int i = 0; i < sW / 4 ; i++) {
            for (int j = 0; j < sH; j++) {
                res.setRGB(i, j, srcImg.getRGB(i + sW/2, j));
            }
        }

        ImageIO.write(src, "png",
                new File("/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/src_img.png"));
        ImageIO.write(res, "png",
                new File("/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/res_img.png"));
    }
    public static BufferedImage imgComp(String srcPath, String resPath) throws IOException {
        BufferedImage srcImg = ImageIO.read(new File(srcPath));
        BufferedImage resImg = ImageIO.read(new File(resPath));

        int sW = srcImg.getWidth(), sH = srcImg.getHeight();
        int rW = srcImg.getWidth(), rH = srcImg.getHeight();
        if (!(sW == rW && sH == rH)) {
            throw new RuntimeException("图片大小有误");
        }

        for (int i = 0; i < sW; i++) {
            for (int j = 0; j < sH; j++) {
                int rgbRes = resImg.getRGB(i, j);
                if (rgbRes == 0xFFFFFFFF) {
                    srcImg.setRGB(i, j, new Color(0, 255, 0).getRGB());
                }
            }
        }

        return srcImg;
    }
}
