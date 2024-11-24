import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int lengthOfProgression(int[] elem) {
        int[] tails = new int[elem.length];
        int size = 0;

        for (int x : elem) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[i] = x;
            if (i == size) {
                ++size;
            }
        }

        return size;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int rank = scanner.nextInt();
        int[] elem = new int[rank];
        for (int i = 0; i < rank; i++) {
            elem[i] = scanner.nextInt();
        }

        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(String.valueOf(lengthOfProgression(elem)));
        fileWriter.close();
    }
}