package presentation_layer.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
    public StyledButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setBackground(SetColor.xanh1);
        setForeground(Color.white);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(SetColor.xanh2);
                setForeground(SetColor.den);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(SetColor.xanh1);
                setForeground(Color.white);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo góc
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Bo góc 15px

        super.paintComponent(g2);
        g2.dispose();
    }

    // 1. Nút tiêu chuẩn (Size 100x35) - Dùng cho Lưu, Thêm, Hủy...
    public static final void Button3(JButton button) {
        button.setFont(SetFont.heading5);
        button.setBackground(SetColor.cam1);
        button.setForeground(Color.white);
        button.setPreferredSize(new Dimension(100, 35));

        applyModernStyle(button); // Gọi hàm style chung
        addHoverEffect(button, SetColor.cam1, SetColor.cam2, Color.white, SetColor.nen);
    }

    // 2. Nút to (Size 150x50) - Dùng cho các thao tác chính nổi bật
    public static final void Button2(JButton button) {
        button.setFont(SetFont.heading4);
        button.setBackground(SetColor.cam1);
        button.setForeground(Color.white);
        button.setPreferredSize(new Dimension(150, 50));

        applyModernStyle(button);
        addHoverEffect(button, SetColor.cam1, SetColor.cam2, Color.white, SetColor.nen);
    }

    // 3. Nút chức năng (Thêm sản phẩm...) - Size tự động dãn theo chữ hoặc icon
    public static final void button4(JButton button) {
        button.setFont(SetFont.heading4);
        button.setBackground(SetColor.cam1);
        button.setForeground(Color.white);

        button.setBorder(new EmptyBorder(8, 20, 8, 20));

        applyModernStyle(button);
        addHoverEffect(button, SetColor.cam1, SetColor.cam2, Color.white, SetColor.nen);
    }

    private static void applyModernStyle(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }


    private static void addHoverEffect(JButton button, Color bgNormal, Color bgHover, Color fgNormal, Color fgHover) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgHover);
                button.setForeground(fgHover);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgNormal);
                button.setForeground(fgNormal);
            }
        });
    }
}