package data;

public class Day24Tile {

    public int x;
    public int y;
    public boolean black;
    public int nbs;

    public Day24Tile(int _x, int _y) {
        x = _x;
        y = _y;
        black = true;
        nbs = 0;
    }

    public void flip() {
        black = !black;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", x, y);
    }
}
