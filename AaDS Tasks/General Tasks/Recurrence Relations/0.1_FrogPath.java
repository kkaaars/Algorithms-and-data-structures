import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 2 || n < 0) {
            System.out.println(-1);
            System.exit(0);
        }
        int[] array = new int[n + 1];
        int[] F = new int[n + 1];
        int[] steps = new int[n + 1];


        for (int i = 1; i < n + 1; i++) {
            array[i] = scanner.nextInt();
        }
        if (n == 1){
            System.out.println(array[1]);
            System.out.println(1);

            System.exit(0);
        }
        F[1] = array[1];
        F[2] = (int) Double.NEGATIVE_INFINITY;
        for (int i = 1; i < n + 1; i++) {
            if (i + 2 <= n && F[i + 2] < F[i] + array[i + 2]) {
                F[i + 2] = F[i] + array[i + 2];
                steps[i + 2] = i;
            }
            if (i + 3 <= n && F[i + 3] < F[i] + array[i + 3]) {
                F[i + 3] = F[i] + array[i + 3];
                steps[i + 3] = i;
            }
        }

        System.out.println(F[n]);
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = n; i != 0; i = steps[i]) {
            path.add(i);
        }
        Collections.reverse(path);
        for (Integer p: path) {
            System.out.print(p + " ");
        }
    }
}