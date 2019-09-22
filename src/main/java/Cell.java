import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private boolean state;

    public Cell() {
        state = false;
    }


    public Cell(boolean alive) {
        state = alive;
    }

    public boolean getAlive() {
        return state;
    }

    public void reset() {
        state = false;
    }

    public void setAlive(boolean alive) {
        state = alive;
    }

    @Override
    public String toString() {
        if (state) {
            return "0";
        } else {
            return " ";
        }
    }

    public void draw(Graphics g, int x, int y, int scale) {
        g.fillOval(x, y, scale, scale);

        if (state) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
    }

    public void updateState(ArrayList<Cell> neighbors) {
        int alive = 0;
        for (Cell c :
                neighbors) {
            if (c.getAlive())
                alive++;
        }

        if ((state && (alive == 2 || alive == 3)) || (!state && alive == 3))
            state = true;
        else
            state = false;
    }

}
