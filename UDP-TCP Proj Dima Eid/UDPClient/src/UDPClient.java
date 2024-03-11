


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;



public class UDPClient {
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(System.in);
        InetAddress IP = InetAddress.getByName("localhost");
        System.out.print("Enter the number of search attempts: ");
        int n= s.nextInt();
        int i = 0;
        while (i < n) {
            DatagramSocket socketts = new DatagramSocket();
            byte[] sendData = new byte[2048];
            byte[] receiveData = new byte[2048];
            System.out.print("Enter the vehicle number: ");
            String C = s.next();
            sendData = C.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IP, 6789);
            socketts.send(sendPacket);
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
            socketts.receive(receivePacket);
            String res = new String(receivePacket.getData());
            System.out.println(res);
            System.out.println(IP);

            i++;
            socketts.close();
        }
    }
}
