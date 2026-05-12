package presentation_layer.mdl;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ShowImage {

    public static JPanel showImg(URL imageUrl) {
        JLabel lblImage = new JLabel();

        loadImage(imageUrl, lblImage);

        JPanel pn = new JPanel(new BorderLayout());
        pn.add(lblImage, BorderLayout.CENTER);

        return pn;
    }

    private static void loadImage(URL imageUrl, JLabel lblImage) {
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);

            int width = icon.getIconWidth();
            int height = icon.getIconHeight();

            int maxW = 400, maxH = 400;
            if (width > maxW || height > maxH) {
                Image img = icon.getImage().getScaledInstance(maxW, maxH, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
                width = icon.getIconWidth();
                height = icon.getIconHeight();
            }

            lblImage.setIcon(icon);
            lblImage.setPreferredSize(new Dimension(width, height));
        } else {
            // Hiển thị báo lỗi nếu URL bị null
            lblImage.setText("Không tìm thấy ảnh (URL rỗng)");
            lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
}