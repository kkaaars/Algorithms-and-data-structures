import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Algorithm {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int length = scanner.nextInt();
        String first = scanner.nextLine();
        String second = scanner.nextLine();
        first += first;
        String strochka = second + "$" + first;
        long[] answer = cyclicShift(strochka);

        long max = answer[0];
        int index = 0;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] > max) {
                max = answer[i];
                index = i;
            }
        }
        System.out.println(Arrays.toString(answer));
        FileWriter fileWriter = new FileWriter("output.txt");
        if (max == length) {
            fileWriter.write(String.valueOf(index - 2 * length));
            fileWriter.close();
        } else {
            fileWriter.write(String.valueOf(-1));
            fileWriter.close();
        }

    }


    public static long[] cyclicShift(String s) {
        long[] pf = new long[s.length()];
        Arrays.fill(pf, 0);
        for (int i = 1; i < s.length(); i++) {
            long j = pf[i - 1];
            while ((j > 0) && (s.charAt((int) j) != s.charAt(i))) {
                j = pf[(int) (j - 1)];
            }
            if (s.charAt((int) j) == s.charAt(i)) {
                pf[i] = j++;
            }
            pf[i] = j;
        }

        return pf;
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() throws FileNotFoundException {
            br = new BufferedReader(new FileReader("input.txt"));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}