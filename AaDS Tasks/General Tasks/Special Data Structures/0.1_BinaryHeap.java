import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(isGreatest(arr) ? "Yes" : "No");
        fileWriter.close();
    }

    public static boolean isGreatest(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            if (2 * i + 1 < n && arr[i] > arr[2 * i + 1]) {
                return false;
            }
            if (2 * i + 2 < n && arr[i] > arr[2 * i + 2]) {
                return false;
            }

        }
        return true;
    }


}