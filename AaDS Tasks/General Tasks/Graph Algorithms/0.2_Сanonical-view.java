import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int r1, r2;
        int[] array = new int[n + 1];
        Arrays.fill(array, 0);
        for (int i = 0; i < n - 1; i++) {
            r1 = scanner.nextInt();
            r2 = scanner.nextInt();
            array[r2] = r1;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (Integer k : array) {
            answer.add(k);
        }
        answer.remove(0);
        FileWriter fileWriter = new FileWriter("output.txt");
        for (Integer p : answer) {
            fileWriter.write(p + " ");
        }
        fileWriter.close();
    }
}