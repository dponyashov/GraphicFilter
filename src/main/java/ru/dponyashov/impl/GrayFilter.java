package ru.dponyashov.impl;

import ru.dponyashov.GraphicFilter;

import java.awt.*;
import java.awt.image.*;

public class GrayFilter implements GraphicFilter {
    @Override
    public BufferedImage filteredImage(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color point = new Color(image.getRGB(j, i));
                int newColorValue = GetGrayForPoint(point);
                Color grayPoint = new Color(newColorValue, newColorValue, newColorValue);
                grayImage.setRGB(j, i, grayPoint.getRGB());
            }
        }
        return grayImage;
    }

    public static int GetGrayForPoint(Color point){
        return (int)(point.getRed() * 0.3 + point.getGreen() * 0.59 + point.getBlue() * 0.11);
    }
}
