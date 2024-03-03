package ru.dponyashov;

import java.awt.image.*;

public interface GraphicFilter {
    BufferedImage filteredImage(BufferedImage image);
}