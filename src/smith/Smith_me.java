package smith;

public class Smith_me {

    public static final int NO_OF_CHARS = 256;
    
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int[] generateBmBc(String pat) {
        int[] badChars = new int[NO_OF_CHARS];
        int m = pat.length();
        for (int i = 0; i < NO_OF_CHARS; i++) {
            badChars[i] = m;
        }
        for (int i = 0; i < m - 1; i++) {
            badChars[(int) pat.charAt(i)] = m - i - 1;
        }
        return badChars;
    }
    
    public static int[] generateQsBc(String pat) {
        int[] badChars = new int[NO_OF_CHARS];
        int m = pat.length();
        for (int i = 0; i < NO_OF_CHARS; i++) {
            badChars[i] = m + 1;
        }
        for (int i = 0; i < m; i++) {
            badChars[(int) pat.charAt(i)] = m - i;
        }
        return badChars;
    }

    public static void search(String pat, String txt) {
        int m = pat.length(), n = txt.length();

        int[] bmBc = generateBmBc(pat);
        int[] qsBc = generateQsBc(pat);

        int s = 0;
        while (s < n - m) {
            int j = 0;
            while (j < m && pat.charAt(j) == txt.charAt(s + j)) {
                j++;
            }
            if (j == m) {
                System.out.println("Matched at " + s);
            }
            
            s += max(bmBc[(int) txt.charAt(s + m - 1)], qsBc[(int) txt.charAt(s + m)]);
        }
    }

    public static void main(String[] args) {
        String txt = "THIS IS A TEXTEXT AND THE TEXT APPEARED AGAIN";
        String pat = "TEXT";
        search(pat, txt);
    }

}
