import java.awt.*;
import java.util.ArrayList;

public class Landscape {
    private int rows;
    private int cols;
    Cell[][] grid;

    class Pos {
        int i;
        int j;

        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        boolean withInBoundary(int rows, int cols) {
             return i >= 0 && i < rows && j >= 0 && j < cols;
        }
    }

    public Landscape(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].reset();
            }
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Cell getCell(int row, int col) {
        assert(row >= 0 && row < rows && col >= 0 && col < cols);
        return grid[row][col];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.append(grid[i][j].toString());
            }
            result.append('\n');
        }

        return result.toString();
    }

    public ArrayList<Cell> getNeighbors(int row, int col) {
        ArrayList<Pos> positions = new ArrayList<Pos>();
        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        positions.add(new Pos(row-1, col-1));
        positions.add(new Pos(row-1, col));
        positions.add(new Pos(row-1, col+1));
        positions.add(new Pos(row, col-1));
        positions.add(new Pos(row, col+1));
        positions.add(new Pos(row+1, col-1));
        positions.add(new Pos(row+1, col));
        positions.add(new Pos(row+1, col+1));

        for (Pos p :
                positions) {
            if (p.withInBoundary(rows, cols)) {
                neighbors.add(grid[p.i][p.j]);
            }
        }

        return neighbors;
    }

    public void draw(Graphics g, int gridScale) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].draw(g, i*gridScale, j*gridScale, gridScale);
            }
        }
    }

    public void advance() {
        Cell[][] tempGrid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ArrayList<Cell> neighbors = getNeighbors(i, j);
                tempGrid[i][j] = new Cell(grid[i][j].getAlive());
                tempGrid[i][j].updateState(neighbors);
            }
        }

        grid = tempGrid;
    }

}
