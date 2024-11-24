import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Node {
    long key;
    Node left, right;

    public Node(long item) {
        key = item;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() throws IOException {
        root = null;
    }

    void insert(long key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, long key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }

    void inorder(FileWriter fileWriter) throws IOException {
        inorderRec(root, fileWriter);
    }

    void inorderRec(Node node, FileWriter fileWriter) throws IOException {
        if (node != null) {
            fileWriter.write(node.key + "\n");
            inorderRec(node.left, fileWriter);
            inorderRec(node.right, fileWriter);
        }
    }

}


public class Solution implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Solution(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("output.txt");
        
            BinaryTree tree = null;

            tree = new BinaryTree();

            Scanner scanner = null;

            scanner = new Scanner(new File("input.txt"));

            while (scanner.hasNext()) {
                tree.insert(scanner.nextLong());
            }

            tree.inorder(fileWriter);


            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}