package geometry;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class GPoint extends Point{
    private int squareLength;
    private final boolean draw;
    private final Rectangle mGPointModel;
            
    public GPoint (int x, int y) {
        this.x = x;
        this.y = y;
        squareLength = 10;
        mGPointModel = new Rectangle();
        
        int halfSquareLength = squareLength / 2;
        mGPointModel.width = squareLength; 
        mGPointModel.height = squareLength;
        mGPointModel.x = x - halfSquareLength;
        mGPointModel.y = y - halfSquareLength;
        
        draw = true;
    }
    
    public void draw(Graphics2D g2) {
        if (draw) {
            g2.draw(mGPointModel);
        }
    }
    
    public void setSquareLength(int squareLength) {
        this.squareLength = squareLength;
    }
    
}
