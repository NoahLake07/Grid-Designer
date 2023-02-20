package data.single;

import freshui.graphics.FRect;

import java.awt.*;
import java.io.Serializable;

public class Tile extends FRect implements Serializable {

    public final static int STARTING_WIDTH = 50;
    public final static int STARTING_HEIGHT = 50;
    public final static Material BLANK = new Material(Color.WHITE,Color.BLACK, true,"Blank");

    private Material myMaterial;

    public Tile(){
        this(STARTING_WIDTH,STARTING_HEIGHT);
    }

    public Tile(int w, int h){
        super(w,h);
        myMaterial = BLANK;
        repaint();
    }

    private void repaint(){
        this.setOutlineVisible(myMaterial.outlineVisible);
        this.setColor(myMaterial.color);
        this.setOutlineColor(myMaterial.outlineColor);
    }

    @Override
    public void setLocation(double x, double y){
        repaint();
        super.setLocation(x,y);
        repaint();
    }

    public void setMaterial(Material ma){
        this.setOutlineVisible(ma.outlineVisible);
        this.setColor(ma.color);
        this.setOutlineColor(ma.outlineColor);
    }

    public Material getMaterial(){
        return myMaterial;
    }

}
