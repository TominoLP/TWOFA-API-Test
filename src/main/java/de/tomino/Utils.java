package de.tomino;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static void openQrCodeWindow(String secretKey, String issuer, String account) {
        final Image qrCodeImage = AuthSys.generateQrCode(secretKey, issuer, account);
        final JFrame frame = new JFrame("QR Code");
        final Image image = qrCodeImage.getScaledInstance(512, 512,  java.awt.Image.SCALE_SMOOTH);
        final JLabel qrCodeLabel = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(qrCodeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512, 512);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
