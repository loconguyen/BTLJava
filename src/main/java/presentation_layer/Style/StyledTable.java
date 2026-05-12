package presentation_layer.Style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class StyledTable extends JTable {

    public StyledTable(DefaultTableModel model) {
        super(model);
        initTableStyle();
    }

    private void initTableStyle() {
        // Font, Chiều cao, Chọn 1 dòng
        setFont(new Font("Arial", Font.PLAIN, 13));
        setRowHeight(30);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setShowGrid(false); // vien trong
        setIntercellSpacing(new Dimension(0, 0));
        setBorder(BorderFactory.createEmptyBorder()); // vien ngoai

        setSelectionBackground(new Color(90, 172, 224));
        setSelectionForeground(Color.BLACK);

        // header
        getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        getTableHeader().setReorderingAllowed(false); // keo tha
        getTableHeader().setBackground(new Color(240, 240, 240));
        getTableHeader().setBorder(BorderFactory.createEmptyBorder()); // Xóa viền header
    }

    // highlight chan le
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);
        // k chon moi to
        if (!isCellSelected(row, column)) {
            comp.setBackground(row % 2 == 0 ? Color.WHITE : Color.decode("#D9D9D9"));
            comp.setForeground(Color.BLACK);
        }
        return comp;
    }

    //scroll
    @Override
    protected void configureEnclosingScrollPane() {
        super.configureEnclosingScrollPane();
        if (getParent() instanceof javax.swing.JViewport && getParent().getParent() instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) getParent().getParent();
            scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Xóa viền của JScrollPane
            scrollPane.getViewport().setBackground(Color.decode("#F5F5F5")); // Màu nền của vùng cuộn
        }
    }
}