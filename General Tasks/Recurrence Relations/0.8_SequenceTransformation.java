import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SequenceTransformation {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("in.txt"));

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        String A = scanner.next();
        String B = scanner.next();

        int[][] dp = new int[A.length() + 1][B.length() + 1];

        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j * y;
                } else if (j == 0) {
                    dp[i][j] = i * x;
                } else {
                    int costReplace = A.charAt(i - 1) == B.charAt(j - 1) ? 0 : z;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + x, dp[i][j - 1] + y), dp[i - 1][j - 1] + costReplace);
                }
            }
        }
        int answer = dp[A.length()][B.length()];
        FileWriter fileWriter = new FileWriter("out.txt");
        fileWriter.write(answer + "\n");
        fileWriter.close();
    }
}