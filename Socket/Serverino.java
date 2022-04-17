package Socket;

import java.io.*;
import java.net.*;

public class Serverino {
    public static final int PORT = 4500;

    public static void main(String[] args) {
        String line;
        Socket conn = null;
        try {
            ServerSocket listen_socket = new ServerSocket(PORT);
            while (true) {
                /* Aspetta un richiesta di connessione */
                conn = listen_socket.accept();
                /* stream di input e output */
                InputStream sin = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(sin));
                PrintStream out = new PrintStream(conn.getOutputStream());
                /* leggi dati inviati dal client */
                line = in.readLine();
                if (line != null) {
                    out.println(line);
                }
                /* Chiudi connessione */
                conn.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
