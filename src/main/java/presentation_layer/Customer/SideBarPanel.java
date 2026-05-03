package presentation_layer.Customer;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presentation_layer.Style.SetColor;
import presentation_layer.Style.SetFont;
import presentation_layer.Style.StyledButton;

public class SideBarPanel extends JPanel {

    private JButton btnHome;
    private JButton btnOrder;
    private JButton btnHistory;
    private JButton btnAccount;

    public SideBarPanel() {
        initUI();
    }

    private void initUI() {
        setPreferredSize(new Dimension(220, 0));

        // 
        setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        setBorder(new EmptyBorder(15, 10, 15, 10));
        setBackground(SetColor.nen);

        btnHome = createMenuButton("Trang chủ");
        btnOrder = createMenuButton("Đơn hàng");
        btnHistory = createMenuButton("Lịch sử");
        btnAccount = createMenuButton("Tài khoản");

        add(btnHome);
        add(btnOrder);
        add(btnHistory);
        add(btnAccount);
    }

    private JButton createMenuButton(String text) {
        StyledButton button = new StyledButton(text);

        button.setFont(SetFont.heading3);


        return button;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    public JButton getBtnOrder() {
        return btnOrder;
    }

    public JButton getBtnHistory() {
        return btnHistory;
    }

    public JButton getBtnAccount() {
        return btnAccount;
    }
}