package dahai;

public class TestWuPan {

    static int ans = 0;

    public static void f(int n, int step, int taijie) {
        System.out.println("剩余台阶数：" + n + ",走了" + step + "步,上了" + taijie + "个台阶");
        if (n < 0)
            return;
        if (n == 0 && step % 2 == 0) {
            ans++;
            System.out.println("第" + ans + "种情况");
        }
        f(n - 1, step + 1, 1);
        f(n - 2, step + 1, 2);
    }

    public static void main(String[] args) {
        f(7, 0, 0);
        System.out.println(ans);
    }

}
