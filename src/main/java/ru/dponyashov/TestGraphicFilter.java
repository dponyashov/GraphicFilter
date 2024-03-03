package ru.dponyashov;

import ru.dponyashov.impl.GrayFilter;
import ru.dponyashov.impl.SobelFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestGraphicFilter {
    private final static String fileFormat = "jpg";
    private final static String sourceFileName = "./sources/sourceFile.jpg";
    private final static String grayImageFileName = "./sources/grayImage.jpg";
    private final static String sobelImageFileName = "./sources/sobelImage.jpg";
    public static void main(String[] args) throws IOException {

        BufferedImage sourceImage = readFromFile();

        applyFilter(sourceImage, new GrayFilter(), grayImageFileName);
        applyFilter(sourceImage, new SobelFilter(), sobelImageFileName);
    }

    private static BufferedImage readFromFile() throws IOException {
        File sourceFile = new File(sourceFileName);
        return ImageIO.read(sourceFile);
    }

    private static void applyFilter(BufferedImage image, GraphicFilter filter, String fileName) throws IOException {
        BufferedImage sobelImage = filter.filteredImage(image);
        saveFile(sobelImage, fileName);
    }

    private static void saveFile(BufferedImage image, String fileName) throws IOException {
        File imageFile = new File(fileName);
        ImageIO.write(image, fileFormat, imageFile);
    }
}