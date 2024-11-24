import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        String x = scanner.nextLine();
        int n = x.length();
        String[] A = x.split("");
        String[] B = new String[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[n - 1];
            n--;
        }
        n = x.length();
        int[][] answer = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (Objects.equals(A[i - 1], B[j - 1])) {
                    answer[i][j] = answer[i - 1][j - 1] + 1;
                } else {
                    answer[i][j] = Math.max(answer[i - 1][j], answer[i][j - 1]);
                }
            }
        }

        int k = answer[n][n];
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(k + "\n");

        List<Integer> indA = new ArrayList<>();
        int i = n, j = n;

        while (i > 0 && j > 0) {
            if (Objects.equals(A[i - 1], B[j - 1])) {
                indA.add(i - 1);
                i--;
                j--;
            } else if (answer[i - 1][j] > answer[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        Collections.reverse(indA);

        for (int indexA : indA) {
            fileWriter.write(A[indexA]);
        }
        fileWriter.close();
    }
}