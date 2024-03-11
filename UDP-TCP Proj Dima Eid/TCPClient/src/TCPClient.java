
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        DataOutputStream totheServer = null;
        DataInputStream fromtheServer = null;

        try{

            Socket sockets = new Socket("localhost", 6789);
            fromtheServer = new DataInputStream(sockets.getInputStream());
            totheServer = new DataOutputStream(sockets.getOutputStream());
            System.out.println();
            System.out.print("Enter the number of searching attempts:");
            int nSearch = s.nextInt();
            int i = 0;
            while (i < nSearch) {
                System.out.print("Enter car Id is either capital or small: ");
                String c = s.next();
                totheServer.writeUTF(c);
                String res = fromtheServer.readUTF();
                System.out.println(res);
                System.out.println();
                i++;
            }

        }
        catch(IOException e){ System.out.println(e.toString()); }
    }

}