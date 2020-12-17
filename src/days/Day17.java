package days;

import data.Day17Cube;
import data.Day17HyperCube;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day17 {
    public static void main(String[] args) {
        String path = "src/input/Day17_input.txt";
        ArrayList<Day17Cube> activeCubes = readInput(path);
        System.out.printf("Part 01: Active Cubes after 6 cycles: %s\n", runCycle(activeCubes));

        ArrayList<Day17HyperCube> activeHCubes = new ArrayList<>();
        for (Day17Cube cube : activeCubes) {
            activeHCubes.add(new Day17HyperCube(cube));
        }
        System.out.printf("Part 02: Active Cubes after 6 cycles: %s\n", runHyperCycle(activeHCubes));


    }

    public static int runHyperCycle(ArrayList<Day17HyperCube> activeCubes) {
        int minX = 0, maxX = 0, minZ = 0, maxZ = 0, minY = 0, maxY = 0, minW = 0, maxW = 0;

        for (int i = 0; i < 6; i++) {
            for (Day17HyperCube cube : activeCubes) {
                minX = Math.min(cube.x, minX);
                maxX = Math.max(cube.x, maxX);
                minY = Math.min(cube.y, minY);
                maxY = Math.max(cube.y, maxY);
                minZ = Math.min(cube.z, minZ);
                maxZ = Math.max(cube.z, maxZ);
                minW = Math.min(cube.w, minW);
                maxW = Math.max(cube.w, maxW);
            }

            ArrayList<Day17HyperCube> nextActives = new ArrayList<>(activeCubes);
            for (int x = minX - 1; x <= maxX + 1; x++) {
                for (int y = minY - 1; y <= maxY + 1; y++) {
                    for (int z = minZ - 1; z <= maxZ + 1; z++) {
                        for (int w = minW - 1; w <= maxW + 1; w++) {

                            Day17HyperCube cube = new Day17HyperCube(x, y, z, w);
                            ArrayList<Day17HyperCube> nbs = cube.getNeighbours();
                            int counter = 0;

                            for (Day17HyperCube nb : nbs) {
                                if (activeCubes.contains(nb)) counter++;
                            }
                            if (activeCubes.contains(cube) && !(counter == 2 || counter == 3)) {
                                nextActives.remove(cube);
                            }
                            if (!activeCubes.contains(cube) && counter == 3) {
                                nextActives.add(cube);
                            }
                        }
                    }
                }
            }
            activeCubes = nextActives;

        }
        return activeCubes.size();
    }

    public static int runCycle(ArrayList<Day17Cube> activeCubes) {

        int minX = 0, maxX = 0, minZ = 0, maxZ = 0, minY = 0, maxY = 0;
        for (Day17Cube cube : activeCubes) {
            minX = Math.min(cube.x, minX);
            maxX = Math.max(cube.x, maxX);
            minY = Math.min(cube.y, minY);
            maxY = Math.max(cube.y, maxY);
        }

        for (int i = 0; i < 6; i++) {
            ArrayList<Day17Cube> nextActives = new ArrayList<>(activeCubes);
            for (int x = minX - 1; x <= maxX + 1; x++) {
                for (int y = minY - 1; y <= maxY + 1; y++) {
                    for (int z = minZ - 1; z <= maxZ + 1; z++) {
                        Day17Cube cube = new Day17Cube(x, y, z);
                        ArrayList<Day17Cube> nbs = cube.getNeighbours();
                        int counter = 0;

                        for (Day17Cube nb : nbs) {
                            if (activeCubes.contains(nb)) counter++;
                        }
                        if (activeCubes.contains(cube) && !(counter == 2 || counter == 3)) {
                            nextActives.remove(cube);
                        }
                        if (!activeCubes.contains(cube) && counter == 3) {
                            nextActives.add(cube);
                        }

                    }
                }
            }
            activeCubes = nextActives;
            minX--;
            maxX++;
            minY--;
            maxY++;
            minZ--;
            maxZ++;

        }
        return activeCubes.size();
    }

    public static ArrayList<Day17Cube> readInput(String path) {
        ArrayList<Day17Cube> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.strip().length(); col++) {
                    if (line.charAt(col) == '#') {
                        result.add(new Day17Cube(col, row, 0));
                    }
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
