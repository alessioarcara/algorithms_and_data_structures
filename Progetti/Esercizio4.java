import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * To compile: javac Esercizio4.java
 * To execute: java Esercizio4 <binary string>
 */

public class Esercizio4 {

    public static int count_iter(String S, HashMap<String, Integer> codes) {

        // codes.put(S.char, value)

        for (int i = 4; i < S.length(); i++) {
            if (codes.containsKey(S.substring(i - 4, i - 3))) {
                System.out.println();
            }
            if (codes.containsKey(S.substring(i - 4, i))) {
            }
        }
        return 0;
    }

    // public static int count(String S, List<String> letters, HashMap<String,
    // Integer> DP) {
    // if (S.isEmpty()) {
    // return 1;
    // }
    // if (DP.containsKey(S)) {
    // return DP.get(S);
    // }
    // int sum = 0;

    // for (String letter : letters) {
    // if (S.startsWith(letter)) {
    // System.out.println(S.substring(letter.length()));
    // sum += count(S.substring(letter.length()), letters, DP);
    // }
    // }
    // DP.put(S, sum);
    // return sum;
    // }

    // public static int count_driver(String S) {
    // List<String> letters = List.of("0", "00", "001", "010", "0010", "0100",
    // "0110", "0001");
    // return count(S, letters, new HashMap<>());
    // }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Esercizio5 <binary string>");
            System.exit(1);
        }

        HashMap<String, Integer> codes = new HashMap<>() {
            {
                put("0", 0);
                put("00", 0);
                put("001", 0);
                put("010", 0);
                put("0010", 0);
                put("0100", 0);
                put("0110", 0);
                put("0001", 0);
            }
        };

        // System.out.println(count("1111", codes, new HashMap<>())); // DP

        System.out.println(count_iter(args[0], codes));
        // numberOfAmbiguosDecodings(args[0], 0, args[0].length() - 1);
    }
}