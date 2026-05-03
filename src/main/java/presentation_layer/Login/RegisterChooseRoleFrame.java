package presentation_layer.Login;

import javax.swing.*;

import presentation_layer.Style.SetColor;
import presentation_layer.Style.SetFont;
import presentation_layer.Style.StyledButton;

import java.awt.*;

public class RegisterChooseRoleFrame extends JFrame {
    private JButton btnCustomer;
    private JButton btnShop;
    private JButton btnShipper;
    private JButton btnBack;

    public RegisterChooseRoleFrame() {
        initUI();
    }

    private void initUI() {
    	setTitle("RegisterChooseRoleFrame");
    	setSize(600, 700);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	JPanel mainPanel = new JPanel(new GridBagLayout());
    	mainPanel.setBackground(Color.WHITE);
    	add(mainPanel);

    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
    	gbc.weightx = 1;
    	gbc.anchor = GridBagConstraints.CENTER;

    	int y = 0;

    	// Title
    	JLabel lblTitle = new JLabel("CHỌN LOẠI TÀI KHOẢN");
    	lblTitle.setFont(SetFont.heading1);
    	lblTitle.setForeground(Color.white);
    	

    	gbc.gridy = y++;
    	gbc.insets = new Insets(0, 0, 25, 0);
    	mainPanel.add(lblTitle, gbc);

    	// Customer
    	btnCustomer = createRoleButton("Khách hàng");

    	gbc.gridy = y++;
    	gbc.insets = new Insets(0, 0, 18, 0);
    	mainPanel.add(btnCustomer, gbc);

    	// Shop
    	btnShop = createRoleButton("Shop");

    	gbc.gridy = y++;
    	gbc.insets = new Insets(0, 0, 18, 0);
    	mainPanel.add(btnShop, gbc);

    	// Shipper
    	btnShipper = createRoleButton("Shipper");

    	gbc.gridy = y++;
    	gbc.insets = new Insets(0, 0, 45, 0);
    	mainPanel.add(btnShipper, gbc);

    	// Back button
    	btnBack = new JButton("Trở lại");
    	btnBack.setPreferredSize(new Dimension(120, 45));
    	btnBack.setBackground(SetColor.xanh1);
    	btnBack.setForeground(Color.WHITE);
    	StyledButton.Button3(btnBack);
    	

    	gbc.gridy = y++;
    	gbc.insets = new Insets(0, 0, 0, 0);
    	mainPanel.add(btnBack, gbc);
        btnCustomer.addActionListener(e -> {
            new RegisterCustomerFrame().setVisible(true);
            dispose();
        });

        btnShop.addActionListener(e -> {
            new RegisterShopFrame().setVisible(true);
            dispose();
        });

        btnShipper.addActionListener(e -> {
            new RegisterShipperFrame().setVisible(true);
            dispose();
        });

        btnBack.addActionListener(e -> {
            new loginFrame().setVisible(true);
            dispose();
        });
    }

	private JButton createRoleButton(String text) {
		 JButton button = new JButton(text);

		    button.setFont(SetFont.heading3);
		    button.setPreferredSize(new Dimension(280, 85));
		    button.setBackground(SetColor.xanh2);
		    button.setForeground(SetColor.den);

		    button.setFocusPainted(false);
		    button.setBorderPainted(false);
		    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    button.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseEntered(java.awt.event.MouseEvent evt) {
		            button.setBackground(SetColor.cam2); // khi hover
		            button.setForeground(SetColor.den);
		        }

		        public void mouseExited(java.awt.event.MouseEvent evt) {
		            button.setBackground(SetColor.xanh2); // khi rời chuột
		            button.setForeground(SetColor.den);
		        }
		    });

		    return button;
		
	}
}