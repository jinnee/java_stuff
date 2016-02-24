package grid;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridMouseAdapter extends MouseAdapter {

    private Point mousePress = null;
    private boolean isBeginDrawing = false;
    private boolean isDrawed = false;
    private boolean isStartDragInGrid = false;
    private boolean isStartScale = false;
    private Grid mGridNodes = null;

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

    public void setGridNodes(Grid mGridNodes) {
        this.mGridNodes = mGridNodes;
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
