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
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int q = Integer.parseInt(line[2]);
        prev = new int[n + 1];
        size = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prev[i] = i;
        }
        Arrays.fill(size, 0);
        int counter = n;
        int[] valueU = new int[m];
        int[] valueV = new int[m];
        int[] vq = new int[m];
        Arrays.fill(vq, 0);
        ArrayList<Integer> valueQ = new ArrayList<>(q);
        for (int i = 0; i < m; i++) {
            line = reader.readLine().split(" ");
            valueU[i] = Integer.parseInt(line[0]);
            valueV[i] = Integer.parseInt(line[1]);
        }
        int value;
        for (int i = 0; i < q; i++) {
            value = Integer.parseInt(reader.readLine());
            valueQ.add(value);
            vq[value - 1] = value;
        }
        reader.close();
        Collections.reverse(valueQ);
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (vq[i] == 0 && (findDSU(valueU[i]) != findDSU(valueV[i]))) {
                setUnion(valueU[i], valueV[i]);
                counter--;
            }
        }
        for (int i = 0; i < q; i++) {
            if (counter != 1) {
                answer.add(0);
            } else answer.add(1);
            if (findDSU(valueU[valueQ.get(i) - 1]) != findDSU(valueV[valueQ.get(i) - 1])) {
                setUnion(valueU[valueQ.get(i) - 1], valueV[valueQ.get(i) - 1]);
                counter--;
            }
        }
        Collections.reverse(answer);
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (Integer a : answer) {
            writer.write(String.valueOf(a));
        }
        writer.close();

    }
}