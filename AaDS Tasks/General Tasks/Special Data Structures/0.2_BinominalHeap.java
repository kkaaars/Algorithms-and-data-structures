import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        long n = scanner.nextLong();
        List<Integer> steps = new ArrayList<>();
        int k = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                steps.add(k);
            }
            n >>= 1;
            k++;
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int heap : steps) {
            fileWriter.write(heap + "\n");
        }
        fileWriter.close();
    }
}