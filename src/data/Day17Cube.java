package data;

import java.util.ArrayList;

public class Day17Cube {

    public int x;
    public int y;
    public int z;

    public Day17Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ArrayList<Day17Cube> getNeighbours() {
        ArrayList<Day17Cube> result = new ArrayList<>();
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    result.add(new Day17Cube(x+i, y+j, z+k));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("(x=%s, y=%s, z=%S)", x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        Day17Cube other = (Day17Cube) obj;
        return (this.x == other.x && this.y == other.y && this.z == other.z);


    }
}
