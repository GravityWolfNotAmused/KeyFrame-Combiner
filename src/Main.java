import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {


    private static BufferedImage result;
    private static Graphics g;
    private static UIFrame ui;

    private static int width;
    private static int height;
    private static int rows;
    private static int columns;
    private static int widthMax;
    private static int heightMax;

    public static void main(String[] args) {
        new Thread(() -> ui = new UIFrame()).run();
    }



    static void drawImage() {

        if (ui.getWidthString().equals("")) {
            ui.getProgressBar().setString("Width's Input is empty and the program will not save instructed file.");
            return;
        }

        if (ui.getHeightString().equals("")) {
            ui.getProgressBar().setString("Height's Input is empty and the program will not save instructed file.");
            return;
        }

        if (ui.getRowString().equals("")) {
            ui.getProgressBar().setString("Row's Input is empty and the program will not save instructed file.");
            return;
        }

        if (ui.getColumnString().equals("")) {
            ui.getProgressBar().setString("Column's Input is empty and the program will not save instructed file.");
            return;
        }

        if (ui.getImageList() == null) {
            ui.getProgressBar().setString("Image list is empty and program will not save instructed file.");
            return;
        }


        try {
            rows = Integer.parseInt(ui.getRowString());
            columns = Integer.parseInt(ui.getColumnString());

            width = Integer.parseInt(ui.getWidthString());
            height = Integer.parseInt(ui.getHeightString());

            widthMax = (rows * width);
            heightMax = (columns * height);
        } catch (IllegalArgumentException e) {
            rows = 0;
            columns = 0;

            width = 0;
            height = 0;
            ui.getProgressBar().setString("Could not parse: Rows, Columns, Width, or height. Please check your input.");
            return;
        }

        SwingUtilities.invokeLater(new Thread(() -> {
            ui.getProgressBar().setString("Running...");
            ui.getProgressBar().setValue(0);

            BufferedImage bi;
            int x = 0;
            int y = 0;

            result = new BufferedImage(widthMax, heightMax, BufferedImage.TYPE_INT_ARGB_PRE);
            g = result.getGraphics();

            for (File file : ui.getImageList()) {
                ui.getProgressBar().setString("Creating Photo");
                ui.getProgressBar().setValue(ui.getProgressBar().getValue() + (100 / ui.getImageList().length));

                try {
                    bi = ImageIO.read(file);
                } catch (IOException e) {
                    bi = null;
                    ui.getProgressBar().setValue(0);

                    ui.getProgressBar().setString("System could not read image/images at specified open path.\n");
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
                ImageIO.write(result, "png", new File(ui.getSavePathString()));
            } catch (IOException e) {
                ui.getProgressBar().setString("Invalid save path.");
            }

            ui.getProgressBar().setString("Done");
            ui.getProgressBar().setValue(0);
        }));
    }
}
