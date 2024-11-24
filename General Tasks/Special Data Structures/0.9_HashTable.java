import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashTable {
    private final int[] table;
    private final int m;
    private final int c;

    public HashTable(int m, int c) {
        this.m = m;
        this.c = c;
        this.table = new int[m];
        Arrays.fill(this.table, -1);
    }

    public void insert(int key) {
        for (int i = 0; i < m; i++) {
            int j = (key % m + c * i) % m;
            if (table[j] == -1 || table[j] == key) {
                table[j] = key;
                break;
            }
        }
    }

    public void printTable() throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 0; i < m; i++) {
            fileWriter.write(table[i] + " ");
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int m = scanner.nextInt();
        int c = scanner.nextInt();
        int n = scanner.nextInt();

        HashTable hashTable = new HashTable(m, c);

        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            hashTable.insert(key);
        }

        hashTable.printTable();
    }
}