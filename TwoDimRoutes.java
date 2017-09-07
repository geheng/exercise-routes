/**
 * Created by gh on 2017/1/18.
 */
import java.util.*;

public class TwoDimRoutes {
    public static int[][] DFSRoutes(int[][] map, int hops) {
        List<int[]> routes = new ArrayList<int[]>();

        Stack<ArrayList<int[]>> pathes = new Stack<ArrayList<int[]>>();
        for (int y = map.length; --y >= 0; ) {
            for (int x = map[y].length; --x >= 0; ) {
                int[] coord = new int[]{y, x};
                ArrayList<int[]> path = new ArrayList<int[]>(){{add(coord);}};
                pathes.push(path);
            }
        }
        while (!pathes.empty()) {
            ArrayList<int[]> path = pathes.pop();
            int hop = path.size() - 1;
            if (hop >= hops) {
                int[] route = new int[hop + 1];
                for (int i = 0; i <= hop; i ++) {
                    route[i] = map[path.get(i)[0]][path.get(i)[1]];
                }
                routes.add(route);
                continue;
            }
            int y = path.get(hop)[0];
            int x = path.get(hop)[1];
            if (y > 0 && x < map[y - 1].length)
                DFSaddHop(y - 1, x, path, pathes);
            if (x > 0)
                DFSaddHop(y, x - 1, path, pathes);
            if (y < map.length - 1 && x < map[y + 1].length)
                DFSaddHop(y + 1, x, path, pathes);
            if (x < map[y].length - 1)
                DFSaddHop(y, x + 1, path, pathes);
        }

        return routes.toArray(new int[routes.size()][]);
    }

    private static void DFSaddHop(int y, int x, ArrayList<int[]> path, Stack<ArrayList<int[]>> pathes) {
        for (int i = 0; i < path.size() - 1; i ++) {
            if (y == path.get(i)[0] && x == path.get(i)[1])
                return;
        }
        ArrayList<int[]> newPath = new ArrayList<int[]>(path);
        newPath.add(new int[]{y, x});
        pathes.push(newPath);
    }

    public static int[][] BFSRoutes(int[][] map, int hops) {
        List<int[]> routes = new ArrayList<int[]>();

        Queue<ArrayList<int[]>> pathes = new LinkedList<ArrayList<int[]>>();
        for (int y = 0; y < map.length; y ++) {
            for (int x = 0; x < map[y].length; x ++) {
                int[] coord = new int[]{y, x};
                ArrayList<int[]> path = new ArrayList<int[]>(){{add(coord);}};
                pathes.add(path);
            }
        }
        while (!pathes.isEmpty()) {
            ArrayList<int[]> path = pathes.poll();
            int hop = path.size() - 1;
            if (hop >= hops) {
                int[] route = new int[hop + 1];
                for (int i = 0; i <= hop; i ++) {
                    route[i] = map[path.get(i)[0]][path.get(i)[1]];
                }
                routes.add(route);
                continue;
            }
            int y = path.get(hop)[0];
            int x = path.get(hop)[1];
            if (x < map[y].length - 1)
                BFSaddHop(y, x + 1, path, pathes);
            if (y < map.length - 1 && x < map[y + 1].length)
                BFSaddHop(y + 1, x, path, pathes);
            if (x > 0)
                BFSaddHop(y, x - 1, path, pathes);
            if (y > 0 && x < map[y - 1].length)
                BFSaddHop(y - 1, x, path, pathes);
        }

        return routes.toArray(new int[routes.size()][]);
    }

    private static void BFSaddHop(int y, int x, ArrayList<int[]> path, Queue<ArrayList<int[]>> pathes) {
        for (int i = 0; i < path.size() - 1; i ++) {
            if (y == path.get(i)[0] && x == path.get(i)[1])
                return;
        }
        ArrayList<int[]> newPath = new ArrayList<int[]>(path);
        newPath.add(new int[]{y, x});
        pathes.add(newPath);
    }

    public static void main(String... args) {
        int[][] m = {
                {11, 12, 13},
                {21, 22},
                {31, 32, 33, 34},
                {41, 42, 43},
                {51, 52, 53, 54, 55, 56}
        };

        int[][] rd = DFSRoutes(m, 4);
        for (int i = 0; i < rd.length; i++) {
            for (int j = 0; j < 5; j ++)
                System.out.printf("%d, ", rd[i][j]);
            System.out.printf("\n");
        }

        int[][] rb = BFSRoutes(m, 4);
        for (int i = 0; i < rb.length; i++) {
            for (int j = 0; j < 5; j ++)
                System.out.printf("%d, ", rb[i][j]);
            System.out.printf("\n");
        }
        System.out.printf("rd = %d, rb = %d\n", rd.length, rb.length);
    }
}