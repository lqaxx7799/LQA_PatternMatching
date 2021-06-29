package knuth_morris_pratt;

public class KnuthMorrisPratt_me {
    
    public static void KMP(String pat, String txt) {
        int[] lps = calculateLPS(pat);
        int M = pat.length(), N = txt.length();
        
        int i = 0, j = 0;
        while (i < N) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == M) {
                System.out.println("Pattern matched at " + (i - j));
                j = lps[j - 1];
            } else if (i < N && txt.charAt(i) != pat.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
            
        }
    }
    
    public static int[] calculateLPS(String pat) {
        int M = pat.length();
        int[] lps = new int[M];
        
        lps[0] = 0;
        int i = 1, len = 0;
        
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String txt = "THIS IS A DEMO TEXT AND THIS TEXT APPEARED AGAIN";
        String pat = "TEXT";
        
        KMP(pat, txt);
    }

}
