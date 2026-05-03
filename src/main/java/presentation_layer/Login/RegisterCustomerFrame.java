package presentation_layer.Login;

import presentation_layer.Login.RegisterChooseRoleFrame;
import presentation_layer.Style.SetColor;
import presentation_layer.Style.SetFont;
import presentation_layer.Style.StyledButton;
import service_layer.AuthService;

import javax.swing.*;
import java.awt.*;

public class RegisterCustomerFrame extends JFrame {
	private JTextField txtUsername;
    private JTextField txtPhone;
    private JTextField txtBirthday;
    private JTextField txtAddress;
    private JComboBox<String> cbGender;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnBack;

    private AuthService authService;

    public RegisterCustomerFrame() {
        authService = new AuthService();
        initUI();
    }

    private void initUI() {
        setTitle("Register Customer");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(SetColor.xanh1);

        JLabel lblTitle = new JLabel("ĐĂNG KÍ TÀI KHOẢN");
        lblTitle.setFont(SetFont.heading1);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(80, 25, 420, 45);
        add(lblTitle);

        JLabel lblSubTitle = new JLabel("Khách hàng");
        lblSubTitle.setFont(SetFont.heading3);
        lblSubTitle.setForeground(Color.WHITE);
        lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubTitle.setBounds(80, 75, 420, 30);
        add(lblSubTitle);

        int x = 130;
        int labelW = 320;
        int inputW = 320;
        int labelH = 20;
        int inputH = 35;

        addLabel("Tên đăng nhập:", x, 135);
        txtUsername = addTextField(x, 160);

        addLabel("Ngày sinh (yyyy-mm-dd):", x, 205);
        txtBirthday = addTextField(x, 230);

        addLabel("Giới tính:", x, 275);
        cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cbGender.setFont(SetFont.normal);
        cbGender.setBounds(x, 300, inputW, inputH);
        add(cbGender);

        addLabel("Địa chỉ:", x, 345);
        txtAddress = addTextField(x, 370);

        addLabel("Số điện thoại:", x, 415);
        txtPhone = addTextField(x, 440);

        addLabel("Tạo mật khẩu:", x, 485);
        txtPassword = new JPasswordField();
        txtPassword.setFont(SetFont.normal);
        txtPassword.setBounds(x, 510, inputW, inputH);
        add(txtPassword);

        btnRegister = new JButton("Đăng kí");
        btnRegister.setBounds(x, 560, 200, 42);
        StyledButton.Button2(btnRegister);
        add(btnRegister);

        btnBack = new JButton("Trở lại");
        btnBack.setBounds(x + 220, 560, 100, 42);
        StyledButton.Button2(btnBack);
        add(btnBack);
        
        btnRegister.addActionListener(e -> handleRegister());
        btnBack.addActionListener(e -> {
            new RegisterChooseRoleFrame().setVisible(true);
            dispose();
        });
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(SetFont.normal);
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 320, 20);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setFont(SetFont.normal);
        textField.setBounds(x, y, 320, 35);
        textField.setBorder(null);
        add(textField);
        return textField;
    }

    private void handleRegister() {
        String phone = txtPhone.getText().trim();
        String birthday = txtBirthday.getText().trim();
        String address = txtAddress.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        int gender = cbGender.getSelectedIndex() == 0 ? 1 : 0;

        if ( phone.isEmpty() || birthday.isEmpty()
                || address.isEmpty()
                || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
//        String username, int gender, String birthdate,
//        String address,
//        String phone, String password
        String result = authService.registerCustomer(
        		username,
                gender,
                birthday,
                address,
                phone,
                password
        );

        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(this, "Đăng ký customer thành công");
            new loginFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, result);
        }
    }
}