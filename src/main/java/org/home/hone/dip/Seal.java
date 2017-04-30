package org.home.hone.dip;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Seal {
    public static int seal(File seal, File source, File dest, int marginX, int marginY, float intense) {
        try {
            BufferedImage sourceImage = ImageIO.read(source);
            BufferedImage sealImage = ImageIO.read(seal);

            // initializes necessary graphic properties
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, intense);
            g2d.setComposite(alphaChannel);

            // calculates the coordinate where the image is painted
            int posX = sourceImage.getWidth() - sealImage.getWidth() - marginX;
            int posY = sourceImage.getHeight() - sealImage.getHeight() - marginY;

            // paints the image watermark
            g2d.drawImage(sealImage, posX, posY, null);

            ImageIO.write(sourceImage, "jpg", dest);
            g2d.dispose();
            return 1;
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "true");
        File seal = new File("/Users/justin/work/quicksand/pdfcut/seal.png");
        File src = new File("/Users/justin/work/quicksand/pdfcut/sa_sheet.jpg");
        File dest = new File("/Users/justin/work/quicksand/pdfcut/sa_sheet-java-sealed.jpg");
        Seal.seal(seal, src, dest, 200, 240, 1.0f);
    }
}
