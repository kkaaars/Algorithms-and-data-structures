import java.math.BigInteger;
import java.util.Scanner;

public class BinaryStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger modulo = BigInteger.valueOf(1000000007);
        int N = scanner.nextInt();
        BigInteger n = factorial(N);
        int K = scanner.nextInt();
        BigInteger k = factorial(K);
        BigInteger m = factorial(N - K).multiply(k);
        BigInteger result = (n.divide(m)).mod(modulo);
        System.out.println(result);
    }

    public static BigInteger factorial(int n) {
        if (n == 0) {
            return BigInteger.valueOf(1);
        }
        return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }
}