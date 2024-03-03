package ru.dponyashov.impl;

import ru.dponyashov.GraphicFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SobelFilter implements GraphicFilter {
    private final int[] mask = {1, 2, 1};
    private int[][] image;
    private int height = 0;
    private int width = 0;

    @Override
    public BufferedImage filteredImage(BufferedImage image) {
        height = image.getHeight();
        width = image.getWidth();
        createMapImage(image);
        BufferedImage sobelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color = Math.min(gradient(i, j), 255);
                Color point = new Color(color, color, color);
                sobelImage.setRGB(j, i, point.getRGB());
            }
        }
        return sobelImage;
    }

    private void createMapImage(BufferedImage image){
        this.image = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Color point = new Color(image.getRGB(j, i));
                this.image[i][j] = GrayFilter.GetGrayForPoint(point);
            }
        }
    }

    private int gradient(int x, int y){
        int gx = getGX(x, y);
        int gy = getGY(x, y);
        return (int)Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
    }

    private int getGX(int x, int y){
        int gx = 0;
        for(int i = 0; i < 3; i++){
            if(((y + i - 1) >= 0) && ((y + i - 1)) < width){
                if((x - 1) >= 0){
                    gx -= image[x - 1][y + i - 1] * mask[i];
                } else {
                    gx -= image[x][y + i - 1] * mask[i];
                }
                if((x + 1) < height){
                    gx += image[x + 1][y + i - 1] * mask[i];
                } else {
                    gx += image[x][y + i - 1] * mask[i];
                }
            } else{
                if((x - 1) >= 0){
                    gx -= image[x - 1][y] * mask[i];
                } else {
                    gx -= image[x][y] * mask[i];
                }
                if((x + 1) < height){
                    gx += image[x + 1][y] * mask[i];
                } else {
                    gx += image[x][y] * mask[i];
                }
            }
        }
        return gx;
    }

    private int getGY(int x, int y){
        int gy = 0;
        for(int i = 0; i < 3; i++){
            if(((x + i - 1) >= 0) && ((x + i - 1)) < height){
                if((y - 1) >= 0){
                    gy -= image[x + i - 1][y - 1] * mask[i];
                } else {
                    gy -= image[x + i - 1][y] * mask[i];
                }
                if((y + 1) < width){
                    gy += image[x + i - 1][y + 1] * mask[i];
                } else {
                    gy += image[x + i - 1][y] * mask[i];
                }
            }
            else{
                if((y - 1) >= 0){
                    gy -= image[x][y - 1] * mask[i];
                } else {
                    gy -= image[x][y] * mask[i];
                }
                if((y + 1) < width){
                    gy += image[x][y + 1] * mask[i];
                } else {
                    gy += image[x][y] * mask[i];
                }
            }
        }
        return gy;
    }
}