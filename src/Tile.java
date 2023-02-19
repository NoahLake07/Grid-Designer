import freshui.graphics.FRect;

import java.awt.*;

public class Tile extends FRect {

    public final static int STARTING_WIDTH = 50;
    public final static int STARTING_HEIGHT = 50;

    private Color fill, outline;

    public Tile(){
        this(STARTING_WIDTH,STARTING_HEIGHT);
    }

    public Tile(int w, int h){
        super(w,h);
        this.setOutlineVisible(true);
        fill = (new Color(245, 245, 245));
        outline = Color.BLACK;
        repaint();
    }

    private void repaint(){
        this.setOutlineVisible(true);
        this.setColor(fill);
        this.setOutlineColor(outline);
    }

    @Override
    public void setLocation(double x, double y){
        repaint();
        super.setLocation(x,y);
        repaint();
    }

}
