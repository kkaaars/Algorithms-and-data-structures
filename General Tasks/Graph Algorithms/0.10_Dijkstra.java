import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Pair>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }
        int point1, point2, pathLength;
        for (int i = 0; i < m; i++) {
            point1 = in.nextInt() - 1;
            point2 = in.nextInt() - 1;
            pathLength = in.nextInt();
            a.get(point1).add(new Pair(point2, pathLength));
            a.get(point2).add(new Pair(point1, pathLength));
        }
        long[] path = dijkstra(a, n);
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(String.valueOf(path[n - 1]));
        fileWriter.close();
    }

    static long[] dijkstra(List<List<Pair>> a, int n) {
        long[] path = new long[n];
        boolean[] pos = new boolean[n];
        Arrays.fill(path, Integer.MAX_VALUE);
        PriorityQueue<Pair> q = new PriorityQueue<>(Comparator.comparingLong(p -> p.pathLength));
        path[0] = 0;
        q.add(new Pair(0, 0));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (pos[pair.nextPoint]) continue;
            pos[pair.nextPoint] = true;
            path[pair.nextPoint] = pair.pathLength;

            for (Pair p : a.get(pair.nextPoint)) {
                q.add(new Pair(p.nextPoint, p.pathLength + pair.pathLength));
            }
        }
        return path;
    }

    static class Pair {
        int nextPoint;
        long pathLength;

        public Pair(int nextPoint, long pathLength) {
            this.nextPoint = nextPoint;
            this.pathLength = pathLength;
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("input.txt"));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}