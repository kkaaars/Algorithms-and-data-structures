import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BinaryTree {
    public static void main(String[] args) throws IOException {
        Scanner file = new Scanner(new FileReader("input.txt"));
        Set<Long> nodes = new TreeSet<>();
        while (file.hasNext()) {
            nodes.add(file.nextLong());
        }
        long sum = 0;
        for (Long node : nodes) {
            sum += node;
        }
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(String.valueOf(sum));
        fileWriter.close();
    }
}