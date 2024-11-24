import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int modulo = 1000000007;
    private static long[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStream = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(inputStream[0]);
        int K = Integer.parseInt(inputStream[1]);
        answer = new long[N + 1];
        answer[0] = 1;
        answer[1] = 1;
        System.out.println(((factorial(N) % modulo) * (((getBinaryPower(answer[K], modulo - 2) % modulo)
                * (getBinaryPower(answer[N - K], modulo - 2) % modulo)) % modulo)) % modulo);
    }

    public static long factorial(int n) {
        for (int i = 1; i <= n; i++) {
            answer[i] = ((answer[i - 1] % modulo) * (i % modulo)) % modulo;
        }
        return answer[n];
    }

    public static long getBinaryPower(long number, int weight) {
        long res = 1;
        while (weight > 0 ) {
            if ((weight & 1) == 1) {
                res *= number;
                res %= modulo;
            }
            number *= number;
            number %= modulo;
            weight >>= 1;
        }
        return res;
    }
}