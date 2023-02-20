package data.group;

import data.single.Tile;

import java.io.Serializable;

public class Grid  implements Serializable {

    private Tile[][] grid;
    private int width, height;

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        grid = new Tile[width][height];

        // instantiate grid
    }

}
