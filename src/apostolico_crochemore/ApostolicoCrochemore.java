package apostolico_crochemore;

// https://stackjava.com/algorithm/thuat-toan-tim-kiem-apostolico-crochemore.html
// https://www-igm.univ-mlv.fr/~lecroq/string/node12.html

// TIME COMPLEXITY:
// preprocessing phase: O(m)
// searching phase: O(n)

public class ApostolicoCrochemore {

    static void AXAMAC(String pat, String txt) {
        int m = pat.length(), n = txt.length();
        int i, j, k, ell;
//        ArrayList<Integer> kmpNext = new ArrayList<>();
        int kmpNext[] = new int[100];

        /* Preprocessing */
        computeLPSArray(pat, m, kmpNext);
        
        ell = 1;
        while (pat.charAt(ell - 1) == pat.charAt(ell)) {
            ell += 1;
        }
        if (ell == m) {
            ell = 0;
        }

        /* Searching */
        i = ell;
        j = k = 0;
        while (j <= n - m) {
            while (i < m && pat.charAt(i) == txt.charAt(i + j)) {
                ++i;
            }
            if (i >= m) {
                while (k < ell && pat.charAt(k) == txt.charAt(j + k)) {
                    ++k;
                }
                if (k >= ell) {
                    System.out.println(j);
                }
            }
            j += (i - kmpNext[i]);
            if (i == ell) {
                k = Math.max(0, k - 1);
            } else if (kmpNext[i] <= ell) {
                k = Math.max(0, kmpNext[i]);
                i = ell;
            } else {
                k = ell;
                i = kmpNext[i];
            }
        }
    }
    
    static void computeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                } else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        String x = "GCAGAGAG";
        char[] X = x.toCharArray();
        String y = "GCATCGCAGAGAGTATACAGTACG";
        char[] Y = y.toCharArray();
        AXAMAC(x, y);
    }

}
