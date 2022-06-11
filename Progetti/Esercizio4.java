
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * L'algoritmo da me proposto, basato sulla programmazione dinamica, segue il seguente schema:
 * ------------------------------------------------------ Sottoproblemi ------------------------------------------------------
 * - contare le codifiche che terminano esattamente nell'ultimo carattere di una stringa di lunghezza i, con 0<=i<=n         -
 * -----------------------------------------------------  Sottosoluzioni -----------------------------------------------------
 * - la somma del numero di codifiche presenti in una stringa di lunghezza i                                                 -
 * ------------------------------------------------------ Soluzione al problema di partenza ----------------------------------
 * - supponendo di aver calcolato tutte le sottosoluzioni f[i], la soluzione al problema di partenza, cioè contare il numero -
 * - di codifiche presenti in una stringa di lunghezza i=n, corrisponde alla sottosoluzione f[n]                             -
 * ---------------------------------------------------------------------------------------------------------------------------
 * 
 * A questo punto, come calcolare le sottosoluzioni?
 * - CASO BASE: lunghezza stringa 0, una stringa vuota corrisponde a una codifica -> 1
 * - CASO GENERALE: lunghezza stringa 1<=i<=n, se alla stringa di lunghezza i tolgo una codifica di lunghezza j che termina 
 *                  esattamente nel carattere i-esimo, cosa rimane? Una stringa di lunghezza i-j, ma questo non è altro che
 *                  un sottoproblema P(i-j) che abbiamo già risolto. Quindi, la sottosoluzione è la somma dei sottoproblemi 
 *                  rimanenti delle codifiche che terminano nell'i-esimo carattere.
 *
 * FORMULA GENERALE: sum(P(i-j) per ogni codifica k che termina esattamente nell'i-esimo carattere della stringa di lunghezza i)
 * con i = lunghezza stringa, j = lunghezza codifica. stringa: "0001", codifica: "001" -> 4 - 3 = 1
 * 
 * Ispirato dall'esempio di Fibonacci, non si è memorizzato un vettore di n posizioni, ma solo un vettore di lunghezza pari
 * alla codifica più lunga, che nell'esercizio è pari a 4. Infatti, per come spiegato nel caso generale, si potrà tornare al 
 * più al sottoproblema P(i-4).
 * 
 * esempio: 
 * S=00100, K=[0,00,001,010,0010, 0100, 0110, 0001]
 * P(0) = 1
 * P(1) = P(0) = 1
 * P(2) = P(1) + P(0) = 2
 * P(3) = P(0) = 1
 * P(4) = P(3) + P(1) + P(0) = 3
 * P(5) = P(4) + P(3) + P(1) = 5
 * 
 *  Algoritmo            
 * +++++++++++++++++++++++++++++++
 * + time ->    O(n*n+n*k)=O(n^2)+
 * + space ->                O(1)+
 * +++++++++++++++++++++++++++++++
 * k: numero codifiche.
 * n*n: Dalla versione Java 7 update 6, substring ha complessità O(n).
 * 
 * To compile: javac Esercizio4.java
 * To execute: java Esercizio4 <file name>
 */

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class CountEncodings {
    final List<String> ENCODINGS = List.of("0", "00", "001", "010", "0010", "0100", "0110", "0001");
    final int N = ENCODINGS.stream().max(Comparator.comparingInt(String::length)).get().length();;
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
        int f[] = new int[N]; // max length of longest encoding
        f[0] = 1; // caso base -> "" empty string

        int idx = 1;
        for (int i = 1; i <= s.length(); i++) {
            int sum = 0;
            String s_i = s.substring(0, i);
            for (String encoding : ENCODINGS) {
                if (s_i.endsWith(encoding))
                    sum += f[(i - encoding.length()) % N];
            }
            f[idx = i % N] = sum;
        }
        return f[idx];
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