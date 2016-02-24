package grid;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grid {

    private int x;
    private int y;
    private int width;
    private int height;
    private byte rows;
    private byte cols;
    private int rectangleCoef;
    private final int rectangleWidth;
    private final int rectangleHeight;

    private boolean draw;
    private boolean drag;
    private boolean scale;

    private final boolean isMouseReleased;
    private final GridModel mGridModel;
    private final GridModel mGridOldModel;

    public Grid() {
        rows = 4;
        cols = 6;
        mGridModel = new GridModel(rows, cols);
        mGridOldModel = new GridModel(rows, cols);

        rectangleCoef = 5;

        draw = false;
        drag = false;
        scale = false;

        rectangleWidth = cols * rectangleCoef;
        rectangleHeight = rows * rectangleCoef;
        isMouseReleased = false;
    }

    public void drawGrid(int x, int y, int width, int height) {
        draw = true;
        drag = false;
        scale = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void translateGrid(Point point, Point mousePress) {
        setOldData();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mGridModel.data[i][j].x += point.x - mousePress.x;
                mGridModel.data[i][j].y += point.y - mousePress.y;
            }
        }

        draw = false;
        drag = true;
        scale = false;
    }

    public void scaleGrid(Point point, Point mousePress) {
        setOldData();
        int dwidth = point.x - mousePress.x;
        int dheight = point.y - mousePress.y;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int w = dwidth / cols;
                int h = dheight / rows;
                mGridModel.data[i][j].x += j * w;
                mGridModel.data[i][j].y += i * h;
            }
        }
        scale = true;
        drag = false;
        draw = false;
    }

    private void setOldData() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mGridModel.data[i][j].x = mGridOldModel.data[i][j].x;
                mGridModel.data[i][j].y = mGridOldModel.data[i][j].y;
            }
        }
    }

    private void setNewData() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mGridOldModel.data[i][j].x = mGridModel.data[i][j].x;
                mGridOldModel.data[i][j].y = mGridModel.data[i][j].y;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (draw) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int w = width / cols;
                    int h = height / rows;

                    mGridModel.data[i][j].x = x + j * w;
                    mGridModel.data[i][j].y = y + i * h;
                    mGridModel.data[i][j].width = rectangleWidth;
                    mGridModel.data[i][j].height = rectangleHeight;

                    mGridOldModel.data[i][j].x = x + j * w;
                    mGridOldModel.data[i][j].y = y + i * h;
                    mGridOldModel.data[i][j].width = rectangleWidth;
                    mGridOldModel.data[i][j].height = rectangleHeight;

                    g2.draw(mGridModel.data[i][j]);
                }
            }
        } else if (drag) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    g2.draw(mGridModel.data[i][j]);
                }
            }
        } else if (scale) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    g2.draw(mGridModel.data[i][j]);
                }
            }
        }
    }

    public void setRows(byte rows) {
        this.rows = rows;
    }

    public void setCols(byte cols) {
        this.cols = cols;
    }

    public void setRectangleCoef(int rectangleCoef) {
        this.rectangleCoef = rectangleCoef;
    }

    public boolean isPointInLeftTopRectangle(Point point) {
        return mGridModel.data[0][0].contains(point);
    }

    public boolean isPointInBottomRightRectangle(Point point) {
        return mGridModel.data[rows - 1][cols - 1].contains(point);
    }

    public boolean isPointInGrid(Point point) {
        boolean result = false;

        Rectangle topLeftRect = mGridModel.data[0][0];
        Rectangle bottomRightRect = mGridModel.data[rows - 1][cols - 1];

        int w = bottomRightRect.x - topLeftRect.x + bottomRightRect.width;
        int h = bottomRightRect.y - topLeftRect.y + bottomRightRect.height;

        Rectangle gridRect = new Rectangle(topLeftRect.x, topLeftRect.y, w, h);

        if (gridRect.contains(point)) {
            result = true;
        }

        return result;
    }

    public void mouseReleased() {
        if (drag || scale) {
            setNewData();
        }
    }

    class GridModel {

        Rectangle[][] data;

        public GridModel(int rows, int cols) {
            data = new Rectangle[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = new Rectangle();
                }
            }
        }
    }

}
