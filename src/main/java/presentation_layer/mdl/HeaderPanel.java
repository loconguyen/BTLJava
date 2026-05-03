package presentation_layer.mdl;

import presentation_layer.Login.loginFrame;
import presentation_layer.Style.SetColor;
import presentation_layer.Style.SetFont;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JLabel lblUsername;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnLogout;

    private JTable targetTable;
    private DefaultTableModel targetModel;
    private TableRowSorter<DefaultTableModel> sorter;

    private String username;


    public HeaderPanel(String un, JTable table, DefaultTableModel tableModel) {
        this.username = un;
        this.targetTable = table;
        this.targetModel = tableModel;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 80));

        JPanel header = createHeader();
        add(header, BorderLayout.CENTER);

        btnLogout.addActionListener(e -> logout(this));

    }

    public void setTarget(JTable table, DefaultTableModel tableModel) {
        this.targetTable = table;
        this.targetModel = tableModel;

        if (tableModel != null && table != null) {
            sorter = new TableRowSorter<>(tableModel);
            table.setRowSorter(sorter);
        } else {
            sorter = null;
        }
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout(20, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        headerPanel.setBackground(SetColor.xanh1);
        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(SetColor.xanh1);
        lblUsername = new JLabel("Xin chào, " + username);
        lblUsername.setFont(SetFont.heading3);
        lblUsername.setForeground(SetColor.nen);
        leftPanel.add(lblUsername);

        //search
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        centerPanel.setBackground(SetColor.xanh1);
        txtSearch = new JTextField(28);
        txtSearch.setFont(SetFont.normal);

        btnSearch = new JButton("Tìm kiếm");
       
        btnSearch.setFont(SetFont.heading5);
        btnSearch.setBackground(SetColor.cam1);
        btnSearch.setForeground(SetColor.nen);

        sorter = null;
        if (targetModel != null && targetTable != null) {
            sorter = new TableRowSorter<>(targetModel);
            targetTable.setRowSorter(sorter);
        }

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { search(); }
            @Override
            public void removeUpdate(DocumentEvent e) { search(); }
            @Override
            public void changedUpdate(DocumentEvent e) { search(); }

            private void search() {
                if (sorter == null) return; // nothing to search against
                String text = txtSearch.getText();
                if (text == null || text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        centerPanel.add(txtSearch);
        centerPanel.add(btnSearch);


        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(SetColor.xanh1);
        btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(SetFont.heading5);
        btnLogout.setPreferredSize(new Dimension(130, 40));
        btnLogout.setBackground(SetColor.cam1);
        btnLogout.setForeground(SetColor.nen);
        rightPanel.add(btnLogout);

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(centerPanel, BorderLayout.CENTER);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private void logout(Component parent) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc muốn đăng xuất không?",
                "Xác nhận đăng xuất",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Window window = SwingUtilities.getWindowAncestor(parent);
            if (window != null) {
                window.dispose(); // đóng frame hiện tại
            }

            new loginFrame().setVisible(true);
        }
    }
}