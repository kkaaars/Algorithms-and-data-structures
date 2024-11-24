import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] answer = new int[n + 1];
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        ArrayList<Integer> u = new ArrayList<>();
        ArrayList<Integer> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    u.add(i + 1);
                    v.add(j + 1);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            int r1 = u.get(i);
            int r2 = v.get(i);
            answer[r2] = r1;
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 1; i < n + 1; i++) {
            fileWriter.write(answer[i] + " ");
        }
        fileWriter.close();
    }
}