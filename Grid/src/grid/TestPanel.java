package grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class TestPanel extends JPanel {

    private Grid mGridNodes = null;
    private static final Color DRAWING_RECT_COLOR = Color.red;

    public TestPanel() {
        mGridNodes = new Grid();
        GridMouseAdapter mouseAdapter = new GridMouseAdapter();
        mouseAdapter.setGridNodes(mGridNodes);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(DRAWING_RECT_COLOR);
        mGridNodes.draw(g2);
        this.repaint();
    }

    private class TestPanelMouseAdapter extends MouseAdapter {

        private Point mousePress = null;
        private boolean isBeginDrawing = false;
        private boolean isDrawed = false;
        private boolean isStartDragInGrid = false;
        private boolean isStartScale = false;

        @Override
        public void mousePressed(MouseEvent e) {
            mousePress = e.getPoint();
            // start scale grid
            if (mGridNodes.isPointInBottomRightRectangle(mousePress)) {
                isStartScale = true;
                isStartDragInGrid = false;
            } else if (mGridNodes.isPointInGrid(mousePress)) {
                isStartScale = false;
                isStartDragInGrid = true;
            } else {
                isStartScale = false;
                isStartDragInGrid = false;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point cPoint = e.getPoint();
            if (!isDrawed) {
                int x = Math.min(mousePress.x, cPoint.x);
                int y = Math.min(mousePress.y, cPoint.y);
                int width = Math.abs(mousePress.x - cPoint.x);
                int height = Math.abs(mousePress.y - cPoint.y);
                mGridNodes.drawGrid(x, y, width, height);
            } else {
                if (isStartDragInGrid) {
                    mGridNodes.translateGrid(cPoint, mousePress);
                }
                if (isStartScale) {
                    mGridNodes.scaleGrid(cPoint, mousePress);
                }
            }
            isBeginDrawing = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (isBeginDrawing) {
                isDrawed = true;
            }
            mGridNodes.mouseReleased();
        }
    }
}
