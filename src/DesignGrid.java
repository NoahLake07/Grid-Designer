import freshui.program.FreshProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DesignGrid extends FreshProgram {

    private Grid grid;
    private int prevX, prevY;

    public DesignGrid(){
        grid = new Grid(20,20,40,40);
    }

    public void init(){
        addGrid(grid);
        addMouseListeners();
    }

    public void mouseMoved(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();
    }

    public void mouseClicked(MouseEvent e){
        if (e.isPopupTrigger()) {
            // secondary click detected, effect tile
        }
    }

    public void mouseDragged(MouseEvent e) {

        if (e.isPopupTrigger()) {
            // secondary click detected
        } else {
            // primary click detected
            int currX = e.getX();
            int currY = e.getY();
            int deltaX = currX - prevX;
            int deltaY = currY - prevY;
            int newX = grid.getX() + deltaX;
            int newY = grid.getY() + deltaY;
            grid.setLocation(newX, newY);
            prevX = currX;
            prevY = currY;
        }
    }

    private void addGrid(Grid g){
        g.addTo(this, 0, 0);
    }

    public class Grid {

        private Tile[][] grid;
        private int width, height;
        private int tileWidth, tileHeight;
        private int xOffset, yOffset;

        public Grid(int width, int height, int tW, int tH){
            this.width = width;
            this.height = height;
            grid = new Tile[width][height];
            this.tileWidth = tW;
            this.tileHeight = tH;
            this.xOffset = 0;
            this.yOffset = 0;

            // * instantiate grid
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    grid[j][i] = new Tile(tileWidth, tileHeight);
                }
            }
        }

        public void addTo(FreshProgram parent, int x, int y){
            this.xOffset = x;
            this.yOffset = y;

            for (int h = 0; h < this.height-1; h++) {
                for (int w = 0; w < this.width-1; w++) {
                    parent.add(grid[w][h], this.tileWidth*w+xOffset,this.tileHeight*h+yOffset);
                }
            }
        }

        public void setLocation(int x, int y){
            this.xOffset = x;
            this.yOffset = y;
            updateTileLocations();
        }

        public void updateTileLocations(){
            for (int h = 0; h < this.height-1; h++) {
                for (int w = 0; w < this.width-1; w++) {
                    grid[w][h].setLocation(this.tileWidth*w+xOffset,this.tileHeight*h+yOffset);
                }
            }
        }

        public int getX(){
            return xOffset;
        }

        public int getY(){
            return yOffset;
        }
    }

    public static void main(String[] args) {
        new DesignGrid().start();
    }
}
