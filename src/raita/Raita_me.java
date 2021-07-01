package raita;

public class Raita_me {

    public static final int NO_OF_CHARS = 256;
    
    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int[] generateBadCharacters(String pat) {
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

    public static void search(String pat, String txt) {
        int m = pat.length(), n = txt.length();

        int[] badChars = generateBadCharacters(pat);
        int firstChar = pat.charAt(0),
                middleChar = pat.charAt(m / 2),
                lastChar = pat.charAt(m - 1);

        int s = 0;
        while (s <= n - m) {
            if (lastChar == txt.charAt(s + m - 1) && middleChar == txt.charAt(s + m / 2) && firstChar == txt.charAt(s)) {
                int j = 1;
                while (j < m - 1 && pat.charAt(j) == txt.charAt(s + j)) {
                    j++;
                }
                if (j == m - 1) {
                    System.out.println("Matched at " + s);
                }
            }
            s += badChars[(int) txt.charAt(s + m - 1)];
        }
    }

    public static void main(String[] args) {
        String txt = "THIS IS A TEXTEXT AND THE TEXT APPEARED AGAIN";
        String pat = "TEXT";
        search(pat, txt);
    }

}
