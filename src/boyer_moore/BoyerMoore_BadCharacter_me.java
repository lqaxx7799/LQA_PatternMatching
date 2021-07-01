package boyer_moore;

public class BoyerMoore_BadCharacter_me {
    public static final int NO_OF_CHARS = 256;
    
    public static int max(int a, int b) {
        return a > b ? a : b;
    }
    
    public static int[] generateBadCharacter(String pat) {
        int[] badCharacters = new int[NO_OF_CHARS];
        int m = pat.length();
        for (int i = 0; i < NO_OF_CHARS; i++) {
            badCharacters[i] = m;
        }
        for (int i = 0; i < m; i++) {
            badCharacters[(int) pat.charAt(i)] = m - i - 1;
        }
        return badCharacters;
    }
    
    public static void search(String pat, String txt) {
        int m = pat.length(), n = txt.length();
        
        int[] badCharacters = generateBadCharacter(pat);
        
        int s = 0;
        while (s <= n - m) {
            System.out.println(s);
            int j = m - 1;
            while (j >= 0 && pat.charAt(j) == txt.charAt(s + j)) {
                j--;
            }
            
            if (j < 0) {
                System.out.println("Matched at shift " + s);
                s += badCharacters[(int) txt.charAt(s + m)];
            } else {
                s += badCharacters[(int) txt.charAt(s + j)];
            }
        }
    }
    
    public static void main(String[] args) {
        String txt = "THIS IS A TEXTEXT AND THE TEXT APPEARED AGAIN";
        String pat = "TEXT";
        search(pat, txt);
    }

}
