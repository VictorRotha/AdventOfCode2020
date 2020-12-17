package data;

public class Day17Cube {

    public int x;
    public int y;
    public int z;
    public int w;

    public Day17Cube(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public String toString() {
        return String.format("(x=%s, y=%s, z=%s, w=%s)", x, y, z, w);
    }

    @Override
    public boolean equals(Object obj) {
        Day17Cube other = (Day17Cube) obj;
        return (this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w);


    }
}
