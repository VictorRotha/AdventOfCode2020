package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day17_v2 {

    public static void main(String[] args) {

        String path = "src/input/Day17_input.txt";
        ArrayList<int[]> activeCubes = readInput(path);

        long now = System.currentTimeMillis();
        System.out.printf("Part 01: Active Cubes after 6 cycles: %s in %s ms\n",
                cycle3D(activeCubes), (System.currentTimeMillis()-now));
        now = System.currentTimeMillis();
        System.out.printf("Part 02: Active Cubes after 6 cycles: %s in %s ms\n",
                cycle4D(activeCubes), (System.currentTimeMillis()-now));
    }

    public static int neighbours3D(ArrayList<int[]> activeCubes, int[] cube) {
        int result = 0;
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    if (isInList(activeCubes, new int[]{i+cube[0], j+cube[1], k+cube[2], 0})) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static int neighbours4D(ArrayList<int[]> activeCubes, int[] cube) {
        int result = 0;
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) continue;
                        if (isInList(activeCubes, new int[]{i + cube[0], j + cube[1], k + cube[2], l + cube[3]})) {
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean isInList(ArrayList<int[]> al, int[] a) {
        for (int[] ar : al) {
            if (Arrays.equals(ar, a)) {
                return (Arrays.equals(ar, a));
            }
        }
        return false;
    }

    public static void removeFromList(ArrayList<int[]> al, int[] a) {
        for (int i = 0; i < al.size(); i++) {
            if (Arrays.equals(al.get(i), a)) {
                al.remove(i);
                i--;
            }
        }
    }


    public static int cycle3D(ArrayList<int[]> activeCubes) {
        int minX = 0, maxX = 0, minZ = 0, maxZ = 0, minY = 0, maxY = 0;
        for (int i = 0; i < 6; i++) {
            for (int[] cube : activeCubes) {
                minX = Math.min(cube[0], minX);
                maxX = Math.max(cube[0], maxX);
                minY = Math.min(cube[1], minY);
                maxY = Math.max(cube[1], maxY);
                minZ = Math.min(cube[2], minZ);
                maxZ = Math.max(cube[2], maxZ);
            }
            ArrayList<int[]> nextActives = new ArrayList<>(activeCubes);
            for (int x = minX - 1; x <= maxX + 1; x++) {
                for (int y = minY - 1; y <= maxY + 1; y++) {
                    for (int z = minZ - 1; z <= maxZ + 1; z++) {
                        int[] cube = new int[] {x, y, z, 0};
                        int counter = neighbours3D(activeCubes, cube);
                        if (isInList(activeCubes, cube) && !(counter == 2 || counter == 3)) {
                            removeFromList(nextActives, cube);
                        }
                        if (!isInList(activeCubes, cube) && counter == 3) {
                            nextActives.add(cube);
                        }
                    }
                }
            }
            activeCubes = nextActives;
        }

        return activeCubes.size();
    }


    public static int cycle4D(ArrayList<int[]> activeCubes) {
        int minX = 0, maxX = 0, minZ = 0, maxZ = 0, minY = 0, maxY = 0;
        int minW = 0, maxW = 0;
        for (int i = 0; i < 6; i++) {
            for (int[] cube : activeCubes) {
                minX = Math.min(cube[0], minX);
                maxX = Math.max(cube[0], maxX);
                minY = Math.min(cube[1], minY);
                maxY = Math.max(cube[1], maxY);
                minZ = Math.min(cube[2], minZ);
                maxZ = Math.max(cube[2], maxZ);
                minW = Math.min(cube[3], minW);
                maxW = Math.max(cube[3], maxW);
            }
            ArrayList<int[]> nextActives = new ArrayList<>(activeCubes);
            for (int x = minX - 1; x <= maxX + 1; x++) {
                for (int y = minY - 1; y <= maxY + 1; y++) {
                    for (int z = minZ - 1; z <= maxZ + 1; z++) {
                        for (int w = minW - 1; w <= maxW + 1; w++) {
                            int[] cube = new int[]{x, y, z, w};
                            int counter = neighbours4D(activeCubes, cube);
                            if (isInList(activeCubes, cube) && !(counter == 2 || counter == 3)) {
                                removeFromList(nextActives, cube);
                            }
                            if (!isInList(activeCubes, cube) && counter == 3) {
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

    public static ArrayList<int[]> readInput(String path) {
        ArrayList<int[]> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.strip().length(); col++) {
                    if (line.charAt(col) == '#') {
                        result.add(new int[]{col, row, 0, 0});
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
