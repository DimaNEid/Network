

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class UDPServer{



    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket(6789);
        byte[] inServer = new byte[2048];
        byte[] outServer = new byte[2048];
        while(true){
            DatagramPacket rcvPkt = new DatagramPacket(inServer,inServer.length);
            socket.receive(rcvPkt);
            String cNumber = new String(rcvPkt.getData(), StandardCharsets.UTF_8);
            cNumber=cNumber.substring(0, 6).toLowerCase();
            System.out.println("Received :"+cNumber);

            try {
                File myObj = new File("C:\\Users\\msys\\IdeaProjects\\vehicle.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().toLowerCase();

                    boolean x = data.contains(cNumber.trim());
                    if (x == true){
                        String[] D = data.split(" ", 2);
                        String result = D[1];
                        outServer = result.getBytes();
                        InetAddress IP = rcvPkt.getAddress();
                        int port = rcvPkt.getPort();
                        DatagramPacket sndPkt = new DatagramPacket(outServer, outServer.length, IP, port);
                        socket.send(sndPkt);
                    }
                    else {
                        System.out.println("vehicle not found");
                    }
                }
                myReader.close();

            } catch (FileNotFoundException e) { e.printStackTrace(); }
        }
    }
}