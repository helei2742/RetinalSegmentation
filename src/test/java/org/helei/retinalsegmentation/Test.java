package org.helei.retinalsegmentation;

public class Test {
    public static void main(String[] args) {
        String str = "11";
        System.out.println(fun2(str, 0, str.length()-1));
    }

    public static boolean fun1(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j)&&((i-j)<2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                }
            }
        }
        return dp[0][n-1];
    }

    public static boolean fun2(String s, int i, int j) {
        if(i >= j) return true;
        return s.charAt(i) == s.charAt(j) && fun2(s, i+1, j-1);
    }
}
