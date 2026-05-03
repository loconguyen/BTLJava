package presentation_layer.Login;

import model_layer.account;
import presentation_layer.Customer.customerFrame;
import presentation_layer.Shipper.shipperFrame;
import presentation_layer.Shop.shopFrame;
import presentation_layer.Style.SetColor;
import presentation_layer.Style.SetFont;
import presentation_layer.Style.StyledButton;
import service_layer.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class loginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRole;
    private JButton btnLogin;
    private JButton btnRegister;

    private AuthService authService;

    public loginFrame() {
        authService = new AuthService();
        initUI();
    }

    private void initUI() {
        setTitle("Login");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(SetColor.xanh1);
        add(mainPanel);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(500, 500));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(15, 30, 15, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        JLabel lblTitle = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblTitle.setFont(SetFont.heading1);
        lblTitle.setForeground(SetColor.den);

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(lblTitle, gbc);

        JLabel lblUsername = createLabel("Tên đăng nhập");
        txtUsername = createTextField();

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 6, 0);
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(lblUsername, gbc);

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 20, 0);
        formPanel.add(txtUsername, gbc);

        JLabel lblPassword = createLabel("Mật khẩu");
        txtPassword = createPasswordField();

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 6, 0);
        formPanel.add(lblPassword, gbc);

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 20, 0);
        formPanel.add(txtPassword, gbc);

        JLabel lblRole = createLabel("Bạn là");
        cbRole = new JComboBox<>(new String[]{"Khách hàng", "Chủ shop", "Shipper"});
        cbRole.setFont(SetFont.normal);
        cbRole.setPreferredSize(new Dimension(300, 35));
        cbRole.setBackground(Color.WHITE);

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 6, 0);
        formPanel.add(lblRole, gbc);

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 25, 0);
        formPanel.add(cbRole, gbc);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        actionPanel.setBackground(Color.WHITE);

        JLabel textConfirm = new JLabel("Bạn chưa có tài khoản?");
        textConfirm.setFont(SetFont.normal);
        btnRegister = new JButton("Đăng ký");
        StyledButton.Button3(btnRegister);

        actionPanel.add(textConfirm);
        actionPanel.add(btnRegister);

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 30, 0);
        formPanel.add(actionPanel, gbc);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(SetFont.heading3);
        btnLogin.setBackground(SetColor.xanh1);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnLogin.setPreferredSize(new Dimension(170, 45));

        gbc.gridy = y++;
        gbc.insets = new Insets(0, 0, 0, 0);
        formPanel.add(btnLogin, gbc);

        mainPanel.add(formPanel);

        btnLogin.addActionListener(e -> handleLogin());

        btnRegister.addActionListener(e -> {
            new RegisterChooseRoleFrame().setVisible(true);
            dispose();
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SetFont.heading4);
        label.setForeground(SetColor.den);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(SetFont.normal);
        textField.setPreferredSize(new Dimension(300, 35));
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(SetFont.normal);
        passwordField.setPreferredSize(new Dimension(300, 35));
        return passwordField;
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String roleUI = cbRole.getSelectedItem().toString();
        String role = "";

        switch (roleUI) {
            case "Khách hàng":
                role = "CUSTOMER";
                break;
            case "Chủ shop":
                role = "SHOP";
                break;
            case "Shipper":
                role = "SHIPPER";
                break;
            default:
                role = "";
        }
    

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        account acc = authService.login(username, password, role);

        if (acc == null) {
            JOptionPane.showMessageDialog(this, "Nhập sai thông tin");
            return;
        }

        JOptionPane.showMessageDialog(this, "Đăng nhập thành công ");

         switch (role) {
            case "CUSTOMER":
                new customerFrame(acc.getUsername(), acc.getCustomerID()).setVisible(true);
                break;

            case "SHOP":
                new shopFrame(acc.getUsername(), acc.getShopID()).setVisible(true);
                break;

            case "SHIPPER":
                new shipperFrame(acc.getUsername(), acc.getShipperID()).setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Sai thông tin");
                return;
        }

        dispose();
    }

    
}