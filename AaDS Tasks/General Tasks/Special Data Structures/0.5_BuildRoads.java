import java.io.*;
import java.util.*;

public class Main {
    static int[] prev, size;

    static int findDSU(int v) {
        if (prev[v] == v) return v;
        return prev[v] = findDSU(prev[v]);
    }

    static void setUnion(int a, int b) {
        a = findDSU(a);
        b = findDSU(b);
        if (size[a] < size[b])
            prev[a] = b;
        else {
            prev[b] = a;
            if (size[a] == size[b])
                size[a]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int q = Integer.parseInt(line[1]);

        prev = new int[n + 1];
        size = new int[n + 1];
        int counter = n;
        for (int i = 1; i <= n; i++) {
            prev[i] = i;
        }
        Arrays.fill(size, 0);

        for (int i = 0; i < q; i++) {
            line = reader.readLine().split(" ");
            int valueU = Integer.parseInt(line[0]);
            int valueV = Integer.parseInt(line[1]);
            if (findDSU(valueU) != findDSU(valueV)) {
                setUnion(valueU, valueV);
                counter--;
            }
            writer.write(counter + "\n");
        }

        reader.close();
        writer.close();
    }
}