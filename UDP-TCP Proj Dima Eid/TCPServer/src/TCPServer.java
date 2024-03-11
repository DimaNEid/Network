
import java.io.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TCPServer {

    public static void main(String[] args) {
        try{
            ServerSocket serverSoc = new ServerSocket(6789);
            System.out.println("the server is on and running ");
            Socket socketss = serverSoc.accept();
            DataInputStream inputFromClient = new DataInputStream(
                    socketss.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(
                    socketss.getOutputStream());
            while(true){
                String c = inputFromClient.readUTF().toLowerCase();
                String res = null;
                try {
                    File obj = new File("C:\\Users\\msys\\IdeaProjects\\vehicle.txt");
                    Scanner a = new Scanner(obj);
                    while (a.hasNextLine()) {
                        String data = a.nextLine().toLowerCase();
                        boolean x = data.contains(c);
                        if (x == true){
                            String[] D = data.split(" ", 2);
                            res = D[1];
                            outputToClient.writeUTF(res);
                        }
                    }
                    a.close();
                } catch (FileNotFoundException e) { e.printStackTrace(); }
            }
        }catch(Exception e) {}
    }
}
