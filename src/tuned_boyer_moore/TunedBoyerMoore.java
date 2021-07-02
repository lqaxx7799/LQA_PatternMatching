package tuned_boyer_moore;

public class TunedBoyerMoore {

    public static final int NO_OF_CHARS = 256;

    void preBmBc(char[] x, int m, int[] bmBc) {
        int i;

        for (i = 0; i < NO_OF_CHARS; ++i) {
            bmBc[i] = m;
        }
        for (i = 0; i < m - 1; ++i) {
            bmBc[x[i]] = m - i - 1;
        }
    }

    void TUNEDBM(char[] x, char[] y) {
        int m = x.length, n = y.length;
        int j, k, shift;
        int[] bmBc = new int[NO_OF_CHARS];

        /* Preprocessing */
        preBmBc(x, m, bmBc);
        shift = bmBc[x[m - 1]];
        bmBc[x[m - 1]] = 0;
        for (int i = n; i < n + m; i++) {
            y[i] = x[m - 1];
        }
//        memset(y + n, x[m - 1], m);

        /* Searching */
        j = 0;
        while (j < n) {
            k = bmBc[y[j + m - 1]];
            while (k != 0) {
                j += k;
                k = bmBc[y[j + m - 1]];
                j += k;
                k = bmBc[y[j + m - 1]];
                j += k;
                k = bmBc[y[j + m - 1]];
            }
            
            int i = 0;
            while (j < m && x[j] == y[j + i]) {
                i++;
            }
            if (i == m) {
                System.out.println("Matched at " + j);
            }
            
            j += shift;
            /* shift */
        }
    }

    public static void main(String[] args) {
    }

}
