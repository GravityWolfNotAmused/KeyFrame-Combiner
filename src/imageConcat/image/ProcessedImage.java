package imageConcat.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProcessedImage {

    private int width;
    private int height;
    private int rows;
    private int columns;
    private int widthMax;
    private int heightMax;
    private File[] pngFiles;
    private String savePath;

    private JProgressBar progressBar;

    public ProcessedImage(String widthInput, String heightInput, String rowsInput, String columnsInput, String savePath, File[] fileArray, JProgressBar progressBar) {
        this.progressBar = progressBar;
        try {
            this.width = Integer.parseInt(widthInput);
            this.height = Integer.parseInt(heightInput);
            this.rows = Integer.parseInt(rowsInput);
            this.columns = Integer.parseInt(columnsInput);

            this.widthMax = rows * width;
            this.heightMax = columns * height;
            this.pngFiles = fileArray;
            this.savePath = savePath;
        } catch (IllegalArgumentException e) {
            width = 0;
            height = 0;

            rows = 0;
            columns = 0;
            pngFiles = null;
            progressBar.setString("Error Parsing inputs, please check your input.");
        }
    }
    public boolean checkForValidInput() {
        if (width <= 0 || height <= 0 || rows <= 0 || columns <= 0) {
            progressBar.setString("Input's are empty or set to 0 and the program will not save instructed file.");
            return false;
        }
        if (pngFiles == null) {
            progressBar.setString("Image list is empty and program will not save instructed file.");
            return false;
        }
        return true;
    }
    public void drawImage() {
        SwingUtilities.invokeLater(new Thread(() -> {
            BufferedImage bi;
            BufferedImage result;
            Graphics g;
            int x = 0;
            int y = 0;

            progressBar.setString("Running...");
            progressBar.setValue(0);

            result = new BufferedImage(widthMax, heightMax, BufferedImage.TYPE_INT_ARGB_PRE);
            g = result.getGraphics();

            for (File file : pngFiles) {
                progressBar.setString("Creating Photo");
                progressBar.setValue(progressBar.getValue() + (100 / pngFiles.length));

                try {
                    bi = ImageIO.read(file);
                } catch (IOException e) {
                    bi = null;
                    result = null;
                    g = null;

                    x = 0;
                    y = 0;

                    progressBar.setValue(0);
                    progressBar.setString("System could not read image/images at specified open path.\n");
                    break;
                }

                System.out.printf("Image Drawn at (%s, %s)\n", x, y);

                g.drawImage(bi, x, y, null);
                x += bi.getWidth();

                if (x >= widthMax) {
                    x = 0;
                    y += bi.getHeight();
                }
            }

            try {
                ImageIO.write(result, "png", new File(savePath));
            } catch (IOException e) {
                progressBar.setString("Invalid save path.");
            }

            progressBar.setString("Done");
            progressBar.setValue(0);
        }));
    }
}
