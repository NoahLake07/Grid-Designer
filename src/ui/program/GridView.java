package ui.program;

import data.group.MaterialCollection;
import data.single.Material;
import data.single.Tile;
import freshui.program.FreshProgram;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.filechooser.FileFilter;
import java.awt.event.MouseEvent;
import java.io.*;

public class GridView extends FreshProgram implements Serializable {

    private Grid grid;
    private int prevX, prevY;
    private MaterialCollection materials = new MaterialCollection();
    public JMenuBar menuBar = new JMenuBar();

    public GridView(){
        grid = new Grid(20,20,40,40);
    }

    public GridView(int wid, int hei){
        grid = new Grid(wid,hei,40,40);
    }

    public void init(){
        this.setName("Design Grid Project");

        addGrid(grid);
        addMouseListeners();

        JMenu fileMenu = new JMenu("File");
            JMenuItem load = new JMenuItem("Load");
            fileMenu.add(load);
            load.addActionListener(m -> loadGrid());

            JMenuItem save = new JMenuItem("Save");
            fileMenu.add(save);
            save.addActionListener(m -> saveGrid());

        menuBar.add(fileMenu);
        add(menuBar,NORTH);
    }

    public void mouseMoved(MouseEvent e) {
        prevX = e.getX();
        prevY = e.getY();
    }

    public void mouseClicked(MouseEvent e){
        if (!e.isPopupTrigger()) {
            // secondary click detected, effect tile
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (!e.isShiftDown()) {
            // secondary click detected

        } else {
            // primary click detected
            int currX = e.getX();
            int currY = e.getY();
            int deltaX = currX - prevX;
            int deltaY = currY - prevY;
            grid.move(deltaX, deltaY);
            prevX = currX;
            prevY = currY;
        }
    }

    private void addGrid(Grid g){
        g.addTo(this, 0, 0);
    }

    private void loadGrid(){
        System.out.println("LOAD");
    }

    public void saveGrid(){
        saveGrid(this);
    }

    public void saveGrid(GridView grid){
        System.out.println("SAVE");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Choose a Save Location");
        fileChooser.setName("Choose Grid Save Location");
        fileChooser.showDialog(null,"Choose Save Location");

        File selected = null;
        selected = fileChooser.getSelectedFile();

        try {
            FileOutputStream fileOut = new FileOutputStream(selected.getPath()+"/"+grid.getName()+".grs/");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(grid);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

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

            // instantiate grid
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    grid[j][i] = new Tile(tileWidth, tileHeight);
                    add(grid[j][i]); // Add tiles directly to program
                }
            }
            updateTileLocations(); // update tiles location only once
        }

        public void addTo(FreshProgram parent, int x, int y){
            this.xOffset = x;
            this.yOffset = y;
        }

        public void move(int deltaX, int deltaY){
            this.xOffset += deltaX;
            this.yOffset += deltaY;
            updateTileLocations(); // update tiles location on move
        }

        private void updateTileLocations(){
            for (int h = 0; h < this.height; h++) {
                for (int w = 0; w < this.width; w++) {
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
        new GridView().start();
    }
}
