import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int m = in.nextInt();

        long[][] a = new long[n][n];
        for (int i = 0; i < m; i++) {
            int point1 = in.nextInt() - 1;
            int point2 = in.nextInt() - 1;
            int pathLength = in.nextInt();
            a[point1][point2] = pathLength;
            a[point2][point1] = pathLength;
        }

        ArrayList<Long> bestPoint = new ArrayList<>();
        bestPoint.add(dijkstra(a, 0));
        long minRoad = bestPoint.get(0);
        int bestHouse = 1;
        for (int i = 1; i < n; i++) {
            bestPoint.add(dijkstra(a, i));
            if (bestPoint.get(i) <= minRoad) {
                minRoad = bestPoint.get(i);
                bestHouse = i + 1;
            }
        }
        System.out.println(bestPoint);
        FileWriter fileWriter = new FileWriter("output.out");
        fileWriter.write(bestHouse + " " + minRoad);
        fileWriter.close();
    }


    public static long dijkstra(long[][] arr, int begin) {
        int n = arr.length;
        long[] path = new long[n];
        boolean[] pos = new boolean[n];
        Arrays.fill(path, Integer.MAX_VALUE);
        path[begin] = 0;
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(begin, 0));

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int v = pair.nextPoint;
            if (pos[v]) continue;
            pos[v] = true;

            for (int i = 0; i < n; i++) {
                if (arr[v][i] != 0 && !pos[i]) {
                    if (path[v] + arr[v][i] < path[i]) {
                        path[i] = path[v] + arr[v][i];
                        q.add(new Pair(i, path[i]));
                    }
                }
            }
        }
        long sum = 0;
        for (Long w : path) {
            if (w != Integer.MAX_VALUE) sum += w;
        }
        return sum;
    }

    static class Pair implements Comparable<Pair> {
        int nextPoint;
        long pathLength;

        public Pair(int nextPoint, long pathLength) {
            this.nextPoint = nextPoint;
            this.pathLength = pathLength;
        }

        @Override
        public int compareTo(Pair other) {
            return Long.compare(this.pathLength, other.pathLength);
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("input.in"));
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