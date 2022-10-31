import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int xl = (int)1e9;
        int xr = -(int)1e9;
        int yl = (int)1e9;
        int yr = -(int)1e9;
        for (int i = 0; i < n; i++) {
            int x, y, h;
            x = sc.nextInt();
            y = sc.nextInt();
            h = sc.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        int h = (Math.max(xr - xl, yr - yl) + 1) / 2;
        int x = (xl + xr) / 2;
        int y = (yl + yr) / 2;
        System.out.println(x + " " + y + " " + h);
    }
}
