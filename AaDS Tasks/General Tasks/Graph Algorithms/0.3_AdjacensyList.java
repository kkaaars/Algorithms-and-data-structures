import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Queue<Integer>[] answer = new Queue[n];
        for (int i = 0; i < n; i++) {
            answer[i] = new LinkedList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            answer[x - 1].add(y - 1);
            answer[y - 1].add(x - 1);
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        for (Queue<Integer> k : answer) {
            fileWriter.write(k.size() + " ");
            int p = k.size();
            for (int i = 0; i < p; i++) {
                fileWriter.write(k.peek() + 1 + " ");
                k.remove(k.peek());
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}