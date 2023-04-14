package org.helei.retinalsegmentation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Test {
    static class UnionFind {
        private final int[] father;
        private final int[] size;
        private int count;

        UnionFind(int size, int count) {
            this.count = count;
            this.father = new int[size];
            this.size = new int[size];
            for (int i = 0; i < size; i++) {
                this.size[i] = 1;
                this.father[i] = i;
            }
        }

        public int findF(int x) {
            if (father[x] != x) father[x] = findF(father[x]);
            return father[x];
        }

        public void union(int a, int b) {
            int fA = findF(a);
            int fB = findF(b);
            if (fA == fB) return;
            this.size[fB] += this.size[fA];
            father[fA] = fB;
            count--;
        }

        public int getSize(int index) {
            int f = findF(index);
            return this.size[f];
        }

        public int getCount() {
            return this.count;
        }
        public HashSet<Integer> getFathers(int[][] grid) {
            HashSet<Integer> res = new HashSet<>();
            for (int idx = 0; idx < this.size.length-1; idx++) {
                int x = idx/height;
                int y = idx%height;
                if(grid[x][y] == 1 && father[idx] == idx && this.size[idx] <= 2000 && this.size[idx] >= 20) {
                    System.out.println(this.size[idx]);
                    res.add(idx);
                }
            }
            return res;
        }

        public int[] getBorder(int fIdx) {
            int[] res = new int[4];
            res[0] = res[2] = fIdx/height;
            res[1] = res[3] = fIdx%height;
            int c = 0;
            for (int i = 0; i < this.size.length-1; i++) {
                if(findF(i) == fIdx) {
                    c++;
                    int x = i/height;
                    int y = i%height;
                    if(x < res[0]) res[0] = x;
                    if(x > res[2]) res[2] = x;
                    if(y < res[1]) res[1] = y;
                    if(y > res[3]) res[3] = y;
                }
            }
            System.out.println(c);
            return res;
        }
    }
    static int width = 0;
    static int height = 0;
    private final static int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};

    public void binaryImage() throws IOException {
        File file = new File("D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\testB.png");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File("D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\test.png");
        ImageIO.write(grayImage, "jpg", newFile);

    }

    public static void main(String[] args) throws IOException {
        BufferedImage bimg = ImageIO.read(new File("D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\test2.png"));

        width = bimg.getWidth();
        height = bimg.getHeight();
        System.out.println(width+"---"+height);
        int[][] data = new int[width][height];

        int count = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = bimg.getRGB(i,j);
                if(data[i][j] == 0xFFFFFFFF) count++;
            }
        }
        BufferedImage rgb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        UnionFind uf = new UnionFind(width * height + 1, count);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                rgb.setRGB(i, j, bimg.getRGB(i, j));
                int index = i * height + j;

                if(data[i][j] == 0xFFFFFFFF) {
                    data[i][j] = 1;
                    for (int[] direction : DIRECTIONS) {
                        int nextX = i + direction[0];
                        int nextY = j + direction[1];
                        if(nextX >= 0 && nextX < width && nextY >= 0 && nextY < height
                                && data[nextX][nextY] == 0xFFFFFFFF){
                            uf.union(index, nextX * height + nextY);
                        }
                    }
                }
            }
        }
        System.out.println(uf.getCount());

        HashSet<Integer> fathers = uf.getFathers(data);

        System.out.println("================");


        Graphics graphics = rgb.getGraphics();
        graphics.setColor(new Color(0, 255,0));
        for (Integer father : fathers) {
            int[] border = uf.getBorder(father);
            System.out.println(father/height +"---"+father%height+"--"+Arrays.toString(border));
            graphics.drawLine(border[0], border[1], border[2], border[1]);
            graphics.drawLine(border[0], border[1], border[0], border[3]);
            graphics.drawLine(border[0], border[3], border[2], border[3]);
            graphics.drawLine(border[2], border[1], border[2], border[3]);
        }

        ImageIO.write(rgb, "png", new File("D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\res.png"));
    }



    private static int getImageRgb(int i) {
        String argb = Integer.toHexString(i);// 将十进制的颜色值转为十六进制
        // argb分别代表透明,红,绿,蓝 分别占16进制2位
        int r = Integer.parseInt(argb.substring(2, 4),16);//后面参数为使用进制
        int g = Integer.parseInt(argb.substring(4, 6),16);
        int b = Integer.parseInt(argb.substring(6, 8),16);
        int result=(int)((r+g+b)/3);
        return result;
    }
}
