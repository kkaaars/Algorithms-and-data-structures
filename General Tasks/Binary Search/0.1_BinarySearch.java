import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner file = new Scanner(System.in);
        int size = file.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = file.nextInt();
        }

        int numberOfRequest = file.nextInt();
        int requestNumber = 0;
        while (requestNumber != numberOfRequest) {
            int x = file.nextInt();
            int b = SearchTheElement(array, x);
            int l = findNextGreaterOrEqual(array, x);
            int r = findNextGreater(array, x);
            requestNumber++;
            System.out.println(b + " " + l + " " + r);
        }

    }

    public static int SearchTheElement(int[] array, int elem) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == elem) {
                return 1;
            } else if (array[mid] < elem) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }

    public static int findNextGreater(int[] array, int elem) {
        int left = 0;
        int right = array.length - 1;
        int result = array.length;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (array[mid] > elem) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static int findNextGreaterOrEqual(int[] array, int elem) {
        int left = 0;
        int right = array.length - 1;
        int result = array.length;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (array[mid] >= elem) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}