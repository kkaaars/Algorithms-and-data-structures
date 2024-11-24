import java.io.*;
import java.util.*;

public class VectorSum {

    static class Vector {
        int index;
        int[] coordinates;

        public Vector(int index, int[] coordinates) {
            this.index = index;
            this.coordinates = coordinates;
        }

        public double magnitude() {
            double sum = 0;
            for (int coord : coordinates) {
                sum += coord * coord;
            }
            return Math.sqrt(sum);
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            Vector[] vectors = new Vector[n];

            for (int i = 0; i < n; i++) {
                int[] coordinates = new int[m];
                for (int j = 0; j < m; j++) {
                    coordinates[j] = scanner.nextInt();
                }
                vectors[i] = new Vector(i, coordinates);
            }
            scanner.close();
            
            Arrays.sort(vectors, Comparator.comparingDouble(Vector::magnitude).reversed());
            
            List<Integer> order = new ArrayList<>();
            int[] currentSum = vectors[0].coordinates.clone();
            order.add(vectors[0].index);
            
            boolean[] used = new boolean[n];
            used[vectors[0].index] = true;

            for (int i = 1; i < n; i++) {
                Vector bestVector = null;
                double bestDotProduct = Double.POSITIVE_INFINITY;

                for (Vector vec : vectors) {
                    if (used[vec.index]) continue;
                    double dotProduct = dotProduct(currentSum, vec.coordinates);
                    if (dotProduct < bestDotProduct) {
                        bestDotProduct = dotProduct;
                        bestVector = vec;
                    }
                }

                if (bestVector != null) {
                    order.add(bestVector.index);
                    used[bestVector.index] = true;
                    for (int j = 0; j < m; j++) {
                        currentSum[j] += bestVector.coordinates[j];
                    }
                }
            }
            
            PrintWriter writer = new PrintWriter("output.txt");
            for (int idx : order) {
                writer.println(idx + 1);
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double dotProduct(int[] vector1, int[] vector2) {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++) {
            sum += vector1[i] * vector2[i];
        }
        return sum;
    }
}