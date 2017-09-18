package com.test.parking.core.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.test.parking.core.models.tickets.Ticket;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Yuriy Yugay on 9/17/2017.
 *
 * @author Yuriy Yugay
 */
public class QRCodeGenerator {

    public static BufferedImage qrCodeFileGenerator(String info) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(info, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("QRCode is created successfully...");

        } catch (WriterException ex) {
            System.err.println(QRCodeGenerator.class.getName() + " WriterException: " + ex.getMessage());
        }
        return bufferedImage;
    }
}
