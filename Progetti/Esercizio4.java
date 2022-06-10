
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * esempio: 
 * 00010010
 * 
 * formula: sum(F(i-j))
 * sommatoria di tutte le codifiche di lunghezza j che terminano nella stringa di lunghezza i
 * F(0) = 1
 * F(1) = F(0)
 * 
 * To compile: javac Esercizio4.java
 * To execute: java Esercizio4 <binary string>
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class CountEncodings {
    List<String> encodings = List.of("0", "00", "001", "010", "0010", "0100", "0110", "0001");
    int MAX_LENGTH;
    String s;

    public CountEncodings(String inputf) {
        readFile(inputf);
    }

    private void readFile(String inputf) {
        Locale.setDefault(Locale.US);
        try {
            Scanner f = new Scanner(new FileReader(inputf));
            s = f.nextLine();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    public int count() {
        int n = s.length();

        int f[] = new int[n + 1];
        f[0] = 1; // "" empty string

        for (int i = 1; i < n + 1; i++) {
            int sum = 0;
            String s_i = s.substring(0, i);
            for (String encoding : encodings)
                if (s_i.endsWith(encoding))
                    sum += f[i - encoding.length()];
            f[i] = sum;
        }
        System.out.println(Arrays.toString(f));
        return f[n];
    }

}

public class Esercizio4 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java Esercizio5 <file name>");
            System.exit(1);
        }
        CountEncodings c = new CountEncodings(args[0]);
        System.out.println(c.count());
    }
}