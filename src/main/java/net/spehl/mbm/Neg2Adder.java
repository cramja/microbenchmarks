package net.spehl.mbm;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Add together 2 base -2 numbers
 */
public class Neg2Adder {

    static int v(int[] src, int i) {
        if (i < src.length) {
            return src[i];
        }
        return 0;
    }

    static class NM2 {
        int[] n;

        public NM2(int[] n) {
            this.n = n;
        }

        public NM2 add(NM2 o) {
            int maxLen = Math.max(o.n.length, n.length) + 4;
            int[] s = new int[maxLen];
            for (int i = 0; i < s.length; i++) {
                s[i] = s[i] + v(n, i) + v(o.n, i);
                if (s[i] == 1 || s[i] == 0) {
                    continue;
                }
                if (s[i] == 2) {
                    s[i + 1] += 1;
                    s[i + 2] += 1;
                    s[i] = 0;
                } else if (s[i] == 3) {
                    s[i + 1] += 1;
                    s[i + 2] += 1;
                    s[i] = 1;
                } else if (s[i] == 4) {
                    s[i + 2] += 1;
                    s[i] = 0;
                } else {
                    throw new AssertionError("");
                }
            }
            return new NM2(s);
        }

        public static NM2 fromString(String s) {
            // most sig digit on the left
            s = s.trim();
            int[] n = new int[s.length()];
            for (int i = 0; i < s.length(); i++) {
                assert s.charAt(i) == '1' || s.charAt(i) == '0';
                n[n.length - 1 - i] =  s.charAt(i) == '1' ? 1 : 0;
            }
            return new NM2(n);
        }

        public String toString() {
            char[] chars = new char[n.length];
            for (int i = n.length - 1; i >= 0; i--) {
                assert n[i] == 1 || n[i] == 0;
                chars[n.length - 1 - i] = n[i] == 1 ? '1' : '0';
            }
            return new String(chars);
        }

        public int toDecimal() {
            int sum = 0;
            int place = 1;
            for (int i = 0; i < n.length; i++) {
                sum += (n[i] * place);
                place *= -2;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        sc.close();

        NM2 n1 = NM2.fromString(s1);
        NM2 n2 = NM2.fromString(s2);

        System.out.println(n1.toString());
        System.out.println(n2.toString());

        final NM2 add = n1.add(n2);
        System.out.println(add.toString());

        System.out.printf("%d + %d = %d ? %b%n", n1.toDecimal(), n2.toDecimal(), add.toDecimal(),
                           ((n1.toDecimal() + n2.toDecimal()) == add.toDecimal()));
    }

}
