import java.io.*;

public class Main {
    static long[] leftSide, rightSide;
    static int n;
    static boolean[] side;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("bst.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("bst.out"));

        n = Integer.parseInt(reader.readLine());
        long[] values = new long[n];
        side = new boolean[n];
        int[] parent = new int[n];

        side[0] = false;
        parent[0] = 0;
        values[0] = Integer.parseInt(reader.readLine());

        for (int i = 1; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            values[i] = Integer.parseInt(line[0]);
            parent[i] = Integer.parseInt(line[1]) - 1;
            if (line[2].equals("L")) {
                side[i] = true;
            }

        }
        reader.close();

        leftSide = new long[n];
        rightSide = new long[n];
        leftSide[0] = Long.MIN_VALUE;
        rightSide[0] = Long.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            if (side[i]) {
                leftSide[i] = leftSide[parent[i]];
                rightSide[i] = values[parent[i]];
            } else {
                leftSide[i] = values[parent[i]];
                rightSide[i] = rightSide[parent[i]];
            }

            if (!choice(values[i], leftSide[i], rightSide[i])) {
                writer.write("NO");
                writer.close();
                return;
            }
        }

        writer.write("YES");
        writer.close();
    }

    static boolean choice(long value, long ls, long rs) {
        for (int i = 1; i < n; i++) {
            if (side[i] && value >= ls && value < rs) {
                return true;
            }
            if (!side[i] && value >= ls && value < rs) {
                return true;
            }

        }
        return false;
    }
}