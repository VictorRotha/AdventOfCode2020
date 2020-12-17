package data;

import java.util.ArrayList;

public class Day17HyperCube {

    public int x;
    public int y;
    public int z;
    public int w;

    public Day17HyperCube(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Day17HyperCube(Day17Cube cube) {
       this(cube.x, cube.y, cube.z, 0);
    }

    public ArrayList<Day17HyperCube> getNeighbours() {
        ArrayList<Day17HyperCube> result = new ArrayList<>();
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) continue;
                        result.add(new Day17HyperCube(x + i, y + j, z + k, w + l));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("(x=%s, y=%s, z=%S, w=%s)", x, y, z, w);
    }

    @Override
    public boolean equals(Object obj) {
        Day17HyperCube other = (Day17HyperCube) obj;
        return (this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w);


    }
}
