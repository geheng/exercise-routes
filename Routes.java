import java.util.List;
import java.util.ArrayList;

public class Routes {
    public static int[][] Routes(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        List<int[]> routes = new ArrayList<int[]>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (x > 1)                          routes.add(new int[]{map[y][x], map[y][x-1], map[y][x-2]});
                if (x > 0 && y > 0)                 routes.add(new int[]{map[y][x], map[y][x-1], map[y-1][x-1]});
                if (x > 0 && y < rows - 1)          routes.add(new int[]{map[y][x], map[y][x-1], map[y+1][x-1]});
                if (x < cols - 2)                   routes.add(new int[]{map[y][x], map[y][x+1], map[y][x+2]});
                if (x < cols - 1 && y > 0)          routes.add(new int[]{map[y][x], map[y][x+1], map[y-1][x+1]});
                if (x < cols - 1 && y < rows - 1)   routes.add(new int[]{map[y][x], map[y][x+1], map[y+1][x+1]});
                if (y > 1)                          routes.add(new int[]{map[y][x], map[y-1][x], map[y-2][x]});
                if (y > 0 && x > 0)                 routes.add(new int[]{map[y][x], map[y-1][x], map[y-1][x-1]});
                if (y > 0 && x < cols - 1)          routes.add(new int[]{map[y][x], map[y-1][x], map[y-1][x+1]});
                if (y < rows - 2)                   routes.add(new int[]{map[y][x], map[y+1][x], map[y+2][x]});
                if (y < rows - 1 && x > 0)          routes.add(new int[]{map[y][x], map[y+1][x], map[y+1][x-1]});
                if (y < rows - 1 && x < cols - 1)   routes.add(new int[]{map[y][x], map[y+1][x], map[y+1][x+1]});
            }
        }
        return routes.toArray(new int[routes.size()][3]);
    }

    public static void main(String... args) {
        int[][] m = {
                {11, 12, 13},
                {21, 22, 23},
                {31, 32, 33},
                {41, 42, 43}
        };

        int[][] r = Routes(m);
        for (int i = 0; i < r.length; i++) {
             System.out.printf("[%d, %d, %d]\n", r[i][0], r[i][1], r[i][2]);
        }
    }
}