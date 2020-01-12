package com.xitianfo.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author ycSong
 * @create 2020/1/9 10:51
 */
public class Server {

    public static void main(String[] args) throws IOException {


        int port = 9090;// 端口
        try {
            // 1、监听端口
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {

                // 2、等待读取数据
                Socket socket = serverSocket.accept();
                System.out.println("与客户端连接成功！");
                //每一个用户构建一个线程
                new Thread(new ServerThread(socket)).start();
                // 3、读取数据准备
//                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 4、读取数据
//                String str = br.readLine();
                // 5、按照通讯的协议对数据进行解读
//                System.out.println(socket.getRemoteSocketAddress() + "说：" + str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
