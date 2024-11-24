import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("huffman.in"));
        int n = Integer.parseInt(reader.readLine());
        long[] a = new long[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        reader.close();
        long ans = HuffmanCoding(a);

        BufferedWriter writer = new BufferedWriter(new FileWriter("huffman.out"));
        writer.write(String.valueOf(ans));
        writer.close();
    }

    public static long HuffmanCoding(long[] a) {
        long[] b = new long[a.length];
        int i = 0, j = 0;
        long x, y;
        long ans = 0;
        for (int k = 0; k < a.length; k++) {
            b[k] = Long.MAX_VALUE;
        }

        for (int k = 0; k < a.length - 1; k++) {
            if (i < a.length && (j >= a.length || a[i] < b[j])) {
                x = a[i++];
            } else {
                x = b[j++];
            }
            if (i < a.length && (j >= a.length || a[i] < b[j])) {
                y = a[i++];
            } else {
                y = b[j++];
            }
            b[k] = x + y;
            ans += b[k];

        }

        return ans;
    }
}