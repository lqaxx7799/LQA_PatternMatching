package skip_search;

import java.util.ArrayList;

public class SkipSearch_me {

    public static final int NO_OF_CHARS = 256;

    public static boolean cmp(String pat, String txt, int s) {
        if (s + pat.length() > txt.length()) {
            return false;
        }
        int j = 0;
        while (j < pat.length() && pat.charAt(j) == txt.charAt(j + s)) {
            j++;
        }
        return j == pat.length();
    }

    public static void search(String pat, String txt) {
        int m = pat.length(), n = txt.length();

        ArrayList<Integer>[] z = new ArrayList[NO_OF_CHARS];

        // preprocess
        // z là 1 mảng từ điển, mỗi phần tử chứa danh sách vị trí xuất hiện
        // của ký tự đó trong xâu mẫu
        for (int i = 0; i < NO_OF_CHARS; i++) {
            z[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            z[(int) pat.charAt(i)].add(i);
            System.out.println(pat.charAt(i) + " " + i);
        }

        // search
        int j;
        // dịch j với bước nhảy = m
        for (j = m - 1; j < n; j += m) {
            for (int i = 0; i < z[txt.charAt(j)].size(); i++) {
                // lùi lại s = j - vị trí xuất hiện của kí tự đó trong xâu mẫu
                int s = z[txt.charAt(j)].get(i);
                if (cmp(pat, txt, j - s)) {
                    System.out.println("Matched at " + (j - s));
                }
            }
        }
    }

    public static void main(String[] args) {
        String pat = "TEXT";
        String txt = "THIS IS TEXTEXT AND THIS IS TEXT AGAIN";
        search(pat, txt);
    }
}
