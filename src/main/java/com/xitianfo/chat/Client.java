package com.xitianfo.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @Author ycSong
 * @create 2020/1/9 11:16
 */
public class Client {

    public static void main(String[] args) {
        String ipAddress = "127.0.0.1";
        int port = 9090;
        try {
            Socket socket = new Socket(ipAddress, port);
            DataInputStream input = new DataInputStream(socket.getInputStream()); ;
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            Scanner scan = new Scanner(System.in);
            String str = "";
            System.out.println("输入");
            while (scan.hasNext()) {
                str = scan.nextLine();
                pw.println(str);
                pw.flush();
                System.out.println("客户端发送了：" + str);
                if (!input.readUTF().equals(""))
                System.out.println(input.readUTF());
            }
            scan.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
