package grid;

import geometry.GPoint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class TestPanel extends JPanel {

    private Grid mGridNodes = null;
    private GPoint mGPoint = null;
    private static final Color DRAWING_RECT_COLOR = Color.red;

    public TestPanel() {
        // test grid
        mGridNodes = new Grid();
        GridMouseAdapter mouseAdapter = new GridMouseAdapter();
        mouseAdapter.setGridNodes(mGridNodes);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        
        // test point
        mGPoint = new GPoint(10, 10);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(DRAWING_RECT_COLOR);
        mGridNodes.draw(g2);
        mGPoint.draw(g2);
        this.repaint();
    }
}
