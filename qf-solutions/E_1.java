import java.util.*;

public class E_1 {
    private static int[] depth = new int[]{};
    private static final List<List<Integer>> graph = new ArrayList<>();
    private static int[] p = new int[]{};

    private static void dfs(int v, int d, int par) {
        depth[v] = d;
        p[v] = par;
        for (int u : graph.get(v)) {
            if (depth[u] == -1) {
                dfs(u, d + 1, v);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, k;
        n = sc.nextInt();
        k = sc.nextInt();
        depth = new int[n];
        p = Arrays.copyOf(p, n);
        for (int i = 0; i < n; i++) {
            depth[i] = -1;
            p[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            a--;
            b--;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int[] cities = new int[k];
        for (int i = 0; i < k; i++) {
            cities[i] = sc.nextInt();
        }

        dfs(cities[0] - 1, 0, -1);
        int mx = 0;
        int furthest = 0;
        for (int i = 0; i < k; i++) {
            if (mx < depth[cities[i] - 1]) {
                mx = depth[cities[i] - 1];
                furthest = i;
            }
        }

        if (mx % 2 == 1) {
            System.out.println("NO");
            return;
        }

        int[] path = new int[mx + 1];
        int x = cities[furthest] - 1;
        int ptr = 0;
        while (x != -1 && ptr < mx) {
            path[ptr++] = x;
            x = p[x];
        }

        int v = path[path.length / 2];
        for (int i = 0; i < n; i++) {
            depth[i] = -1;
        }

        dfs(v, 0, -1);
        boolean ok = true;
        for (int i = 0; i < k && ok; i++) {
            ok = (depth[cities[i] - 1] == depth[cities[0] - 1]);
        }
        System.out.println((ok ? "YES\n" + (v + 1) : "NO"));
    }
}
