import java.util.*;
import java.util.stream.IntStream;

public class Managing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testc = in.nextInt();
        for (int t = 0; t < testc; t++) {
            int n = in.nextInt();
            int[] seq = IntStream.generate(in::nextInt).limit(n).toArray();
            Map<Integer, Integer> C = new HashMap<>();
            int ans = 0;
            for (int j = n - 1; j >= 0; j--) {
                for (int i = 0; i < j; i++) {
                    ans += C.getOrDefault(2 * seq[j] - seq[i], 0);
                }
                C.put(seq[j], C.getOrDefault(seq[j], 0) + 1);
            }
            System.out.println(ans);
        }
    }
}


