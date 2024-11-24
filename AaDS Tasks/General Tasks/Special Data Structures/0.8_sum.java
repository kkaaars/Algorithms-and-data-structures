import java.io.*;
import java.util.*;

public class Main {
    static long[] sequence;
    static long[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(System.out);
        int n = Integer.parseInt(buffer.readLine());
        sequence = new long[n];
        segmentTree = new long[4 * n];
        StringTokenizer tokenizer = new StringTokenizer(buffer.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Long.parseLong(tokenizer.nextToken());
        }
        constructTree(1, 0, n - 1);
        int q = Integer.parseInt(buffer.readLine());
        while (q-- > 0) {
            tokenizer = new StringTokenizer(buffer.readLine());
            String queryType = tokenizer.nextToken();
            if (queryType.equals("Add")) {
                int index = Integer.parseInt(tokenizer.nextToken());
                long value = Long.parseLong(tokenizer.nextToken());
                modifyTree(1, 0, n - 1, index, value);
            } else {
                int left = Integer.parseInt(tokenizer.nextToken());
                int right = Integer.parseInt(tokenizer.nextToken()) - 1;
                output.println(sumQuery(1, 0, n - 1, left, right));
            }
        }
        buffer.close();
        output.close();
    }

    static void constructTree(int node, int start, int end) {
        if (start == end) {
            segmentTree[node] = sequence[start];
        } else {
            int mid = (start + end) / 2;
            constructTree(2 * node, start, mid);
            constructTree(2 * node + 1, mid + 1, end);
            segmentTree[node] = segmentTree[2 * node] + segmentTree[2 * node + 1];
        }
    }

    static void modifyTree(int node, int start, int end, int index, long value) {
        if (start == end) {
            sequence[index] += value;
            segmentTree[node] += value;
        } else {
            int mid = (start + end) / 2;
            if (start <= index && index <= mid) {
                modifyTree(2 * node, start, mid, index, value);
            } else {
                modifyTree(2 * node + 1, mid + 1, end, index, value);
            }
            segmentTree[node] = segmentTree[2 * node] + segmentTree[2 * node + 1];
        }
    }

    static long sumQuery(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segmentTree[node];
        }
        int mid = (start + end) / 2;
        long sum1 = sumQuery(2 * node, start, mid, left, right);
        long sum2 = sumQuery(2 * node + 1, mid + 1, end, left, right);
        return sum1 + sum2;
    }
}