public class Grid {

    private Tile[][] grid;
    private int width, height;

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        grid = new Tile[width][height];

        // instantiate grid
    }

}
