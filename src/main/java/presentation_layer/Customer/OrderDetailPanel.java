package presentation_layer.Customer;

import model_layer.CartItem;
import model_layer.addressCustomer;
import model_layer.methodPayment;
import presentation_layer.Style.SetColor;
import presentation_layer.Style.SetFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailPanel extends JPanel {

//    private final Font FONT_TITLE = new Font("Arial", Font.BOLD, 28);
//    private final Font FONT_BOLD_15 = new Font("Arial", Font.BOLD, 15);
//    private final Font FONT_PLAIN_14 = new Font("Arial", Font.PLAIN, 14);
//    private final Font FONT_PLAIN_13 = new Font("Arial", Font.PLAIN, 13);
//    private final Font FONT_BOLD_13 = new Font("Arial", Font.BOLD, 13);

    private final JLabel lblSelectedShop;
    private final JPanel billListPanel;
    private final JLabel lblTotalAmount;
    private final JComboBox<methodPayment> cbPaymentMethod;
    private final JComboBox<addressCustomer> cbAddress;
    private final JButton btnPlaceOrder;

    private String selectedShopID;
    private String selectedShopName;
    private String summaryText = "";
    private boolean hasBill;

    public OrderDetailPanel() {
       // setPreferredSize(new Dimension(360, 0));
    	setPreferredSize(new Dimension(430, 0));
    	setMinimumSize(new Dimension(430, 0));
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(12, 8, 12, 8));

        JLabel lblTitle = new JLabel("Giỏ hàng");
        lblTitle.setFont(SetFont.heading2);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblSelectedShop = new JLabel("Chưa chọn shop");
        lblSelectedShop.setFont(SetFont.heading5);
        lblSelectedShop.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel billHeader = createBillHeader();
        billHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        billListPanel = new JPanel();
        billListPanel.setBackground(Color.WHITE);
        billListPanel.setLayout(new BoxLayout(billListPanel, BoxLayout.Y_AXIS));
        billListPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JScrollPane billScroll = new JScrollPane(billListPanel);
        billScroll.setBorder(null);
        billScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
       // billScroll.setPreferredSize(new Dimension(350, 240));
        //billScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 240));
        billScroll.setPreferredSize(new Dimension(410, 260));
        billScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
        billScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        billScroll.getVerticalScrollBar().setUnitIncrement(16);

        JLabel lblTotalTitle = new JLabel("Tổng số tiền");
        lblTotalTitle.setFont(SetFont.heading5);
        lblTotalTitle.setForeground(SetColor.den);
        lblTotalTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
// bi chồng 1 cái j đó lên
        lblTotalAmount = new JLabel("0 VND");
        lblTotalAmount.setFont(SetFont.heading5);
        lblTotalAmount.setForeground(SetColor.xanh1);
        lblTotalAmount.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblPayment = new JLabel("Phương thức thanh toán");
        lblPayment.setFont(SetFont.heading5);
        lblPayment.setAlignmentX(Component.LEFT_ALIGNMENT);

        cbPaymentMethod = new JComboBox<>();
        cbPaymentMethod.setFont(new Font("Segoe UI",Font.BOLD, 13));;;
        cbPaymentMethod.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        cbPaymentMethod.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbPaymentMethod.setEnabled(false);

        JLabel lblAddress = new JLabel("Địa chỉ nhận hàng");
        lblAddress.setFont(new Font("Segoe UI",Font.BOLD, 13));
        lblAddress.setAlignmentX(Component.LEFT_ALIGNMENT);

        cbAddress = new JComboBox<>();
        cbAddress.setFont(SetFont.normal);
        cbAddress.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        cbAddress.setAlignmentX(Component.LEFT_ALIGNMENT);
        cbAddress.setEnabled(false);
// check
        btnPlaceOrder = new JButton("Đặt hàng");
        btnPlaceOrder.setFont(SetFont.heading5);
        btnPlaceOrder.setBackground(SetColor.cam1);
        btnPlaceOrder.setForeground(Color.white);
        btnPlaceOrder.setFocusPainted(false);
        btnPlaceOrder.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPlaceOrder.setMaximumSize(new Dimension(220, 44));
        btnPlaceOrder.setEnabled(false);
        btnPlaceOrder.setOpaque(true);
        btnPlaceOrder.setContentAreaFilled(true);
        btnPlaceOrder.setBorderPainted(false);
        

        cbPaymentMethod.addActionListener(e -> updatePlaceOrderButtonState());
        cbAddress.addActionListener(e -> updatePlaceOrderButtonState());

        add(lblTitle);
        add(Box.createVerticalStrut(6));

        add(lblSelectedShop);
        add(Box.createVerticalStrut(12));

        add(billHeader);
        add(Box.createVerticalStrut(6));

        add(billScroll);  

        add(Box.createVerticalStrut(10));
        add(createBottomSection(lblTotalTitle, lblTotalAmount, lblPayment, cbPaymentMethod, lblAddress, cbAddress, btnPlaceOrder));

        add(Box.createVerticalGlue());

        clearBill();
    }

    private JPanel createBottomSection(JLabel lblTotalTitle,
            JLabel lblTotalAmount,
            JLabel lblPayment,
            JComboBox<methodPayment> cbPaymentMethod,
            JLabel lblAddress,
            JComboBox<addressCustomer> cbAddress,
            JButton btnPlaceOrder) {

JPanel wrapper = new JPanel();
wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
wrapper.setBackground(Color.WHITE);
wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
wrapper.setBorder(new EmptyBorder(0, 0, 0, 0));

JSeparator line = new JSeparator();
line.setForeground(SetColor.nen);// check
line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));
line.setAlignmentX(Component.LEFT_ALIGNMENT);
// part2 của panel orderdetail
JPanel card = new JPanel();
card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
card.setBackground(Color.white);//check
card.setAlignmentX(Component.LEFT_ALIGNMENT);
card.setBorder(BorderFactory.createCompoundBorder(
BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
new EmptyBorder(14, 14, 14, 14)
));
card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

lblTotalTitle.setFont(SetFont.heading5);
lblTotalTitle.setForeground(SetColor.xanh1);
lblTotalTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

lblTotalAmount.setFont(SetFont.heading5);
lblTotalAmount.setForeground(SetColor.cam1);
lblTotalAmount.setAlignmentX(Component.LEFT_ALIGNMENT);

lblPayment.setFont(new Font("Segoe UI", Font.BOLD, 13));
lblPayment.setForeground(SetColor.den);
lblPayment.setAlignmentX(Component.LEFT_ALIGNMENT);

lblAddress.setFont(new Font("Arial", Font.BOLD, 13));
lblAddress.setForeground(new Color(30, 41, 59));
lblAddress.setAlignmentX(Component.LEFT_ALIGNMENT);

cbPaymentMethod.setFont(new Font("Arial", Font.PLAIN, 13));
cbPaymentMethod.setBackground(Color.WHITE);
cbPaymentMethod.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
cbPaymentMethod.setPreferredSize(new Dimension(0, 36));
cbPaymentMethod.setAlignmentX(Component.LEFT_ALIGNMENT);

cbAddress.setFont(new Font("Arial", Font.PLAIN, 13));
cbAddress.setBackground(Color.WHITE);
cbAddress.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
cbAddress.setPreferredSize(new Dimension(0, 36));
cbAddress.setAlignmentX(Component.LEFT_ALIGNMENT);


btnPlaceOrder.setFocusPainted(false);
btnPlaceOrder.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
btnPlaceOrder.setCursor(new Cursor(Cursor.HAND_CURSOR));
btnPlaceOrder.setAlignmentX(Component.LEFT_ALIGNMENT);
btnPlaceOrder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
btnPlaceOrder.setPreferredSize(new Dimension(0, 42));

card.add(lblTotalTitle);
card.add(Box.createVerticalStrut(6));
card.add(lblTotalAmount);
card.add(Box.createVerticalStrut(16));
card.add(lblPayment);
card.add(Box.createVerticalStrut(6));
card.add(cbPaymentMethod);
card.add(Box.createVerticalStrut(12));
card.add(lblAddress);
card.add(Box.createVerticalStrut(6));
card.add(cbAddress);
card.add(Box.createVerticalStrut(16));
card.add(btnPlaceOrder);

wrapper.add(line);
wrapper.add(Box.createVerticalStrut(12));
wrapper.add(card);

return wrapper;
}

	private JPanel createBillHeader() {
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        header.setBorder(new EmptyBorder(0, 0, 0, 0));
        header.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(createCellLabel("Sản phẩm", 130, SwingConstants.LEFT, true));
        header.add(Box.createHorizontalStrut(6));
        header.add(createCellLabel("SL", 28, SwingConstants.CENTER, true));
        header.add(Box.createHorizontalStrut(6));
        header.add(createCellLabel("Đơn giá", 78, SwingConstants.RIGHT, true));
        header.add(Box.createHorizontalStrut(6));
        header.add(createCellLabel("Thành tiền", 92, SwingConstants.RIGHT, true));

        return header;
    }

	private JPanel createBillRow(CartItem item) { 
	    JPanel card = new JPanel();
	    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
	    card.setBackground(Color.WHITE);
	    card.setAlignmentX(Component.LEFT_ALIGNMENT);
	    card.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(new Color(230, 230, 230)),
	            new EmptyBorder(8, 10, 8, 10)
	    ));

	    // TÊN SẢN PHẨM (xuống dòng tự nhiên)
	    JTextArea txtName = new JTextArea(item.getProduct().getName());
	    txtName.setFont(SetFont.normal);
	    txtName.setLineWrap(true);
	    txtName.setWrapStyleWord(true);
	    txtName.setEditable(false);
	    txtName.setFocusable(false);
	    txtName.setOpaque(false);
	    txtName.setBorder(null);
	    txtName.setAlignmentX(Component.LEFT_ALIGNMENT);

	    // DÒNG INFO
	    JPanel infoRow = new JPanel(new GridLayout(1, 3, 10, 0));
	    infoRow.setOpaque(false);
	    infoRow.setAlignmentX(Component.LEFT_ALIGNMENT);
	    infoRow.setFont(SetFont.normal);

	    infoRow.add(createInfoBlock("SL", String.valueOf(item.getQuantity())));
	    infoRow.add(createInfoBlock("Đơn giá", formatMoney(item.getProduct().getUnitPrice())));
	    infoRow.add(createInfoBlock("Thành tiền", formatMoney(item.getLineTotal())));

	    card.add(txtName);
	    card.add(Box.createVerticalStrut(6));
	    card.add(infoRow);

	    return card;
	}
	private JPanel createInfoBlock(String title, String value) {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    panel.setOpaque(false);

	    JLabel lblTitle = new JLabel(title);
	    lblTitle.setFont(SetFont.heading5);
	    lblTitle.setForeground(SetColor.den);

	    JLabel lblValue = new JLabel(value);
	    lblValue.setFont(new Font("Segoe UI",Font.BOLD, 13));
	    lblValue.setForeground(SetColor.den);

	    panel.add(lblTitle);
	    panel.add(Box.createVerticalStrut(2));
	    panel.add(lblValue);

	    return panel;
	}
    private JLabel createCellLabel(String text, int width, int align, boolean bold) {
        String html = "<html><div style='width:" + width + "px;'>"
                + text +
                "</div></html>";

        JLabel label = new JLabel(html);
        label.setHorizontalAlignment(align);
        label.setFont(bold ? SetFont.heading5: SetFont.normal);

        Dimension size = new Dimension(width, 40); // tăng height để chứa nhiều dòng
        label.setPreferredSize(size);
        label.setMaximumSize(size);
        label.setMinimumSize(size);

        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    public void showBill(String shopID, String shopName, List<CartItem> items) {
        if (items == null || items.isEmpty()) {
            clearBill();
            return;
        }

        this.selectedShopID = shopID;
        this.selectedShopName = shopName;
        this.hasBill = true;
        this.lblSelectedShop.setText(shopName);

        billListPanel.removeAll();

        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (CartItem item : items) {
        	billListPanel.add(createBillRow(item));
        	billListPanel.add(Box.createVerticalStrut(8));
            total += item.getLineTotal();

            sb.append(item.getProduct().getName())
              .append(" | SL: ").append(item.getQuantity())
              .append(" | Đơn giá: ").append(formatMoney(item.getProduct().getUnitPrice()))
              .append(" | Thành tiền: ").append(formatMoney(item.getLineTotal()))
              .append("\n");
        }

        setSummaryText(sb.toString());
        setTotalAmount(total);

        billListPanel.revalidate();
        billListPanel.repaint();
        updatePlaceOrderButtonState();
    }

    public void clearBill() {
        this.selectedShopID = null;
        this.selectedShopName = null;
        this.hasBill = false;
        this.summaryText = "";

        lblSelectedShop.setText("Chưa chọn shop");

        billListPanel.removeAll();
        setTotalAmount(0);

        billListPanel.revalidate();
        billListPanel.repaint();
        updatePlaceOrderButtonState();
    }

    public void setSummaryText(String text) {
        this.summaryText = (text == null) ? "" : text;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setTotalAmount(double amount) {
        lblTotalAmount.setText(formatMoney(amount));
    }
// sua hien thi phuong thuc thanh toan
    public void setPaymentMethodData(List<methodPayment> methods) {
        cbPaymentMethod.removeAllItems();

        if (methods != null) {
            for (methodPayment method : methods) {
                cbPaymentMethod.addItem(method);
            }
        }

        cbPaymentMethod.setEnabled(cbPaymentMethod.getItemCount() > 0);
        if (cbPaymentMethod.getItemCount() > 0) {
            cbPaymentMethod.setSelectedIndex(0);
        }

        updatePlaceOrderButtonState();
    }
// sua hien thi dia chi nhan hang
    public void setAddressData(List<addressCustomer> addresses) {
        cbAddress.removeAllItems();

        if (addresses != null) {
            for (addressCustomer address : addresses) {
                cbAddress.addItem(address);
            }
        }

        cbAddress.setEnabled(cbAddress.getItemCount() > 0);
        if (cbAddress.getItemCount() > 0) {
            cbAddress.setSelectedIndex(0);
        }

        updatePlaceOrderButtonState();
    }

    public methodPayment getSelectedPaymentMethod() {
        return (methodPayment) cbPaymentMethod.getSelectedItem();
    }

    public addressCustomer getSelectedAddress() {
        return (addressCustomer) cbAddress.getSelectedItem();
    }

    public JButton getBtnPlaceOrder() {
        return btnPlaceOrder;
    }

    public String getSelectedShopID() {
        return selectedShopID;
    }

    public String getSelectedShopName() {
        return selectedShopName;
    }

    public boolean hasBill() {
        return hasBill;
    }
// nut dat hang ( co chua dieu kien dat hàng
    private void updatePlaceOrderButtonState() {
        boolean canPlaceOrder = hasBill
                && cbPaymentMethod.getItemCount() > 0
                && cbAddress.getItemCount() > 0
                && cbPaymentMethod.getSelectedItem() != null
                && cbAddress.getSelectedItem() != null;

        btnPlaceOrder.setEnabled(canPlaceOrder);

        if (canPlaceOrder) {
        	// can sua
            btnPlaceOrder.setBackground(SetColor.cam1);
            btnPlaceOrder.setForeground(Color.WHITE);
        } else {
        	// can sua
            btnPlaceOrder.setBackground(SetColor.cam2);
            btnPlaceOrder.setForeground(Color.white);
        }
    }

    private String formatMoney(double value) {
        return new DecimalFormat("#,##0").format(value) + " VND";
    }
}