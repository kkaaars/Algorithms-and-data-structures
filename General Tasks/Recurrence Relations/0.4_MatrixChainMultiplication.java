import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixChainMultiplication {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int s = scanner.nextInt();
        int[] p = new int[s + 1];
        for (int i = 0; i < s; i++) {
            p[i] = scanner.nextInt();
            p[i + 1] = scanner.nextInt();
        }
        scanner.close();

        int[][] dp = new int[s][s];
        for (int l = 1; l < s; l++) {
            for (int i = 0; i < s - l; i++) {
                int j = i + l;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(String.valueOf(dp[0][s - 1]));
        fileWriter.close();
    }
}