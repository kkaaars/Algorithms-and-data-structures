import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int[] upPrev, downPrev;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("report.in"));
        FileWriter fileWriter = new FileWriter("report.out");
        int n = scanner.nextInt();
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = scanner.nextInt();
        }
        scanner.close();
        if (n == 1) {
            fileWriter.write(0 + "\n" + 1);
            fileWriter.close();
        } else {
            if (n < 1) {
                fileWriter.write(0 + "\n");
                fileWriter.close();
            } else {
                int[] pathUp, pathDown;
                ArrayList<Integer> pathK = new ArrayList<>();
                pathUp = upLIS(path);
                pathDown = downLIS(path);
                for (int i = 0; i < n; i++) {
                    pathK.add(Math.min(pathUp[i], pathDown[i]));
                }

                int max = pathK.get(0);
                int indOfMax = 0;
                for (int i = 1; i < n; i++) {
                    if (pathK.get(i) > max) {
                        max = pathK.get(i);
                        indOfMax = i;
                    }
                }

                int k = pathK.get(indOfMax) - 1;
                ArrayList<Integer> answer = new ArrayList<>();
                int i = indOfMax;
                while (answer.size() != k + 1) {
                    answer.add(i + 1);
                    i = upPrev[i];
                    if (i == -1) {
                        break;
                    }
                }
                Collections.reverse(answer);
                i = indOfMax;
                while (answer.size() != 2 * k + 1 && i != path.length) {

                    if (i == -1) {
                       break;
                    }
                    i = downPrev[i];
                    answer.add(i + 1);
                }

                fileWriter.write(k + "\n");

                for (Integer a : answer) {
                    fileWriter.write(a + " ");
                }
                fileWriter.close();
                System.out.println(Arrays.toString(upPrev));
                System.out.println(Arrays.toString(downPrev));
            }
        }


    }

    static int[] upLIS(int[] arr) {
        int n = arr.length;
        upPrev = new int[n];
        Arrays.fill(upPrev, -1);
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            path[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && path[j] + 1 > path[i]) {
                    path[i] = path[j] + 1;
                    upPrev[i] = j;
                }
            }
        }
        return path;
    }

    static int[] downLIS(int[] arr) {
        int n = arr.length;
        downPrev = new int[n];
        Arrays.fill(downPrev, -1);
        int[] path = new int[n];
        for (int i = n - 1; i > 0; i--) {
            path[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[j] < arr[i] && path[j] + 1 > path[i]) {
                    path[i] = path[j] + 1;
                    downPrev[i] = j;
                }
            }
        }
        return path;
    }

}