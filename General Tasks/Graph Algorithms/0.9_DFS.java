import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int counter = 0;
    static ArrayList<Integer>[] vertex;
    static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        vertex = new ArrayList[n];
        answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            vertex[i] = new ArrayList<>();
            answer.add(-1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    vertex[i].add(j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (answer.get(i) == -1) {
                dfs(i);
            }
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        for (Integer k : answer) {
            fileWriter.write(k + " ");
        }
        fileWriter.close();
    }

    static void dfs(int value) {
        counter++;
        answer.set(value, counter);
        for (int i = 0; i < vertex[value].size(); i++) {
            if (answer.get(vertex[value].get(i)) == -1) {
                dfs(vertex[value].get(i));
            }
        }

    }

}