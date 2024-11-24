import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("huffman.in"));
        PriorityQueue<Long> code = new PriorityQueue<>();
        int n = Integer.parseInt(reader.readLine());
        String[] values = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            code.add(Long.parseLong(values[i]));
        }
        reader.close();
        long binCode = 0;
        while (code.size() > 1) {
            long b = code.poll() + code.poll();
            code.add(b);
            binCode += b;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("huffman.out"));
        writer.write(String.valueOf(binCode));
        writer.close();
    }
}