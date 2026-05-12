package presentation_layer.mdl;

import javax.swing.*;
import java.awt.*;

public class RatioSplitPanel extends JPanel {

    public RatioSplitPanel(JPanel mainPanel, JPanel sidePanel) {
        setLayout(new BorderLayout());

        mainPanel.setBorder(BorderFactory.createTitledBorder(""));
        sidePanel.setBorder(BorderFactory.createTitledBorder(""));

        // JSplitPane chia ngang
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainPanel, sidePanel);

        // chia
        splitPane.setResizeWeight(0.8); // 80% cho panel bên trái
        splitPane.setDividerSize(5);     // độ dày đường chia
        splitPane.setContinuousLayout(true); // cập nhật liên tục khi kéo

        add(splitPane, BorderLayout.CENTER);
    }
}