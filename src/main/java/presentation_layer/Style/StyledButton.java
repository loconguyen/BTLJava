package presentation_layer.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyledButton extends JButton {
//    private Color normalColor = new Color(70, 130, 180); // Màu xanh SteelBlue
//    private Color hoverColor = new Color(100, 149, 237); // Màu xanh CornflowerBlue

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
            public void mouseEntered(MouseEvent e) { setBackground(SetColor.xanh2);
                                                     setForeground(SetColor.den);}
            @Override
            public void mouseExited(MouseEvent e) { setBackground(SetColor.xanh1);
                                                    setForeground(Color.white);}
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

  //button size 100x35
    // sua lai mau ( cai nay su dung lai o btt them
    public static final void Button3(JButton button) {
     button.setFont(SetFont.heading5);
     button.setBackground(SetColor.cam1);
     button.setForeground(Color.white);
     button.setFocusPainted(false);
     button.setPreferredSize(new DimensionUIResource(100, 35));
     button.setCursor(new Cursor(Cursor.HAND_CURSOR));// khi dua chuot vao con tro-> ban tay
     button.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        button.setBackground(SetColor.cam2); // khi hover
        button.setForeground(SetColor.nen);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setBackground(SetColor.cam1); // khi rời chuột
        button.setForeground(Color.white);
    }
});
 }
  //button size 150x50
    public static final void Button2(JButton button) {
     button.setFont(SetFont.heading4);
     button.setBackground(SetColor.cam1);
     button.setForeground(Color.white);
     button.setFocusPainted(false);
     button.setPreferredSize(new DimensionUIResource(100, 35));
     button.setCursor(new Cursor(Cursor.HAND_CURSOR));// khi dua chuot vao con tro-> ban tay

 }
    // nut them san pham ....
    public static final void button4(JButton button) {
    	button.setFont(SetFont.heading4);
    	button.setBackground(SetColor.cam1);
    	button.setForeground(Color.white);
    }
}