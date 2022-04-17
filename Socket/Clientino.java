package Socket;

import java.io.*;
import java.net.*;

public class Clientino {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("localhost", 4500);
            System.out.println("CONNESSO");

            InputStream sin = s.getInputStream();
            OutputStream sout = s.getOutputStream();

            /* preparazione stream dal server */
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(sin));
            /* preparazione stream verso il server */
            PrintWriter toServer = new PrintWriter(new OutputStreamWriter(sout));
            /* preparazione stream dall’utente */
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String line;
            /* Leggo stringa da tastiera */
            System.out.print(">");
            line = null;
            line = in.readLine();
            if (line.equals(""))
                throw new IOException();
            /* Invio stringa al server */
            toServer.println(line);
            toServer.flush();
            /* Aspetto risposta dal server */
            line = fromServer.readLine();
            if (line == null)
                throw new IOException();
            /* Visualizzo la risposta del server */
            System.out.println(line);
        } catch (Exception e) {
            System.err.println(e);
            // TODO: handle exception
        }
    }
}
