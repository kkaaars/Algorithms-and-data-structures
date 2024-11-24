import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class Node {
    long level;
    long key;
    Node left, right;
    long amount = 0;

    public Node(long item) {
        key = item;
        left = right = null;
    }

}

class BinaryTree {
    Node root;
    long maxDeep = 0;

    ArrayList<Integer> deeps = new ArrayList<>();


    BinaryTree() {
        root = null;
    }

    void deepCalc(Node node, long deep) {
        if (node != null) {
            node.level = deep;
            if (maxDeep < deep) {
                maxDeep = deep;
            }
            deep++;
            deepCalc(node.right, deep);
            deepCalc(node.left, deep);

            node.amount = 0;
            node.amount += node.right != null ? node.right.amount + 1 : 0;
            node.amount += node.left != null ? node.left.amount + 1 : 0;
        }
    }

    void findVertex(Node node) {
        if (node != null) {
            findVertex(node.right);
            findVertex(node.left);
            if ((maxDeep - node.level) == maxDeep >> 1) {
                if (node.left == null) {
                    return;
                }
                if (node.right == null) {
                    deeps.add((int) node.key);
                } else if (node.right.amount < node.left.amount) {
                    deeps.add((int) node.key);
                }
            }
        }
    }

    void delete() {
        Collections.sort(deeps);
        if (deeps.size() == 0) {
            return;
        }
        if (deeps.size() % 2 != 0) {

            removeRight(deeps.get(deeps.size() >> 1));
        }
    }


    void insert(long key) {
        root = insertRec(root, key);
    }

    void removeRight(long key) {
        root = removeRight(key, root);
    }


    Node removeRight(long key, Node node) {
        if (node == null) {
            return node;

        } else if (key < node.key) {
            node.left = removeRight(key, node.left);
        } else if (key > node.key) {
            node.right = removeRight(key, node.right);
        } else if (node.left != null && node.right != null) {
            node.key = getMin(node.right).key;
            node.right = removeRight((int) node.key, node.right);
        } else if (node.left != null || node.right != null) {
            node = node.left != null ? node.left : node.right;
        } else {
            node = null;
        }
        return node;

    }

    Node getMin(Node node) {
        return node.left == null ? node : getMin(node.left);
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


public class Solution {

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = null;

        fileWriter = new FileWriter("out.txt");

        BinaryTree tree = null;

        tree = new BinaryTree();

        Scanner scanner = null;

        scanner = new Scanner(new File("in.txt"));

        while (scanner.hasNext()) {
            tree.insert(scanner.nextLong());
        }
        tree.deepCalc(tree.root, 0);
        tree.findVertex(tree.root);
        tree.delete();
        tree.inorder(fileWriter);


        fileWriter.close();

    }


}