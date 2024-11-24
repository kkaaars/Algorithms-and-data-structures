import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final int MAX_VALUE = (int)1e9 + 777;
    static final int SIZE = 777;
    static int source, sink;

    static List<Node> nodes = new ArrayList<>();
    static List<List<Integer>> network = new ArrayList<>();
    static List<Integer> visited = new ArrayList<>();
    static int time = 1;

    static void createNode(int from, int to, int capacity) {
        network.get(from).add(nodes.size());
        nodes.add(new Node(to, capacity));
        network.get(to).add(nodes.size());
        nodes.add(new Node(from, 0));
    }

    static int findPath(int thisPoint, int minCapacity) {
        if (thisPoint == sink) {
            return minCapacity;
        }
        visited.set(thisPoint, time);
        for (int index : network.get(thisPoint)) {
            if (nodes.get(index).getRemainingCapacity() == 0 || visited.get(nodes.get(index).nodeId) == time) {
                continue;
            }
            int flow = findPath(nodes.get(index).nodeId, Math.min(minCapacity, nodes.get(index).getRemainingCapacity()));
            if (flow != 0) {
                nodes.get(index).currentFlow += flow;
                nodes.get(index ^ 1).currentFlow -= flow;
                return flow;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalNodes = scanner.nextInt();
        int totalEdges = scanner.nextInt();
        source = 1;
        sink = totalNodes;
        for (int i = 0; i < SIZE; i++) {
            network.add(new ArrayList<>());
            visited.add(0);
        }
        for (int i = 0; i < totalEdges; ++i) {
            int currPoint = scanner.nextInt();
            int nextPoint = scanner.nextInt();
            int capacity = scanner.nextInt();
            createNode(currPoint, nextPoint, capacity);
        }
        while (findPath(source, MAX_VALUE) != 0) {
            ++time;
        }
        int maxFlow = 0;
        for (int index : network.get(source)) {
            maxFlow += nodes.get(index).currentFlow;
        }
        System.out.println(maxFlow);
    }

    static class Node {
        public int nodeId;
        public int currentFlow;
        public int maxCapacity;

        public Node(int nodeId, int maxCapacity) {
            this.nodeId = nodeId;
            this.currentFlow = 0;
            this.maxCapacity = maxCapacity;
        }

        public int getRemainingCapacity() {
            return maxCapacity - currentFlow;
        }
    }
}