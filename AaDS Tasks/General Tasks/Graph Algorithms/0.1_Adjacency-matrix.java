import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][n];
        int u;
        int v;
        for (int i = 0; i < m; i++) {
            u = scanner.nextInt() - 1;
            v = scanner.nextInt() - 1;
            matrix[u][v] = matrix[v][u] = 1;
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fileWriter.write(matrix[i][j] + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}