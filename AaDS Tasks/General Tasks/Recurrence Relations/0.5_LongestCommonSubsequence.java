import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LongestCommonSubsequence {
    public static int[][] answer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] firstSequence = new int[n];
        int[] secondSequence = new int[n];
        for (int i = 0; i < firstSequence.length; i++) {
            firstSequence[i] = scanner.nextInt();
        }
        for (int i = 0; i < secondSequence.length; i++) {
            secondSequence[i] = scanner.nextInt();
        }
        answer = findTheMatrixOfTheLCS(firstSequence, secondSequence);
        System.out.println(answer[firstSequence.length][secondSequence.length]);
        indexRecovery(answer, firstSequence, secondSequence);
    }


    public static int[][] findTheMatrixOfTheLCS(int[] x, int[] y) {
        int[][] answer = new int[x.length + 1][y.length + 1];
        answer[0][0] = 0;
        for (int i = 1; i < x.length + 1; i++) {
            for (int j = 1; j < y.length + 1; j++) {
                answer[i][0] = 0;
                answer[0][j] = 0;
                if (x[i - 1] == y[j - 1]) {
                    answer[i][j] = answer[i - 1][j - 1] + 1;
                } else {
                    answer[i][j] = Math.max(answer[i][j - 1], answer[i - 1][j]);
                }
            }
        }
        return answer;
    }

    public static void indexRecovery(int[][] arr, int[] x, int[] y) {
        ArrayList<Integer> indexRecoveryFirst = new ArrayList<>();
        ArrayList<Integer> indexRecoverySecond = new ArrayList<>();
        int i = arr.length - 1;
        int j = arr[0].length - 1;
        while (i > 0 && j > 0) {
            if (x[i - 1] == y[j - 1]) {
                indexRecoveryFirst.add(i - 1);
                indexRecoverySecond.add(j - 1);
                i--;
                j--;
            } else if (answer[i - 1][j] == answer[i][j]) {
                i--;
            } else j--;

        }
        Collections.reverse(indexRecoveryFirst);
        Collections.reverse(indexRecoverySecond);
        for (Integer firstIndexes : indexRecoveryFirst) {
            System.out.print(firstIndexes + " ");
        }
        System.out.println();
        for (Integer secondIndexes : indexRecoverySecond) {
            System.out.print(secondIndexes + " ");
        }
    }


}