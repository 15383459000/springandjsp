package com.xitianfo.chat; /**
 * @Author ycSong
 * @create 2020/1/9 11:22
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 该类为多线程类，用于服务端
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {
        try{
            //获取Socket的输出流，用来向客户端发送数据
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String str = null;
            while((str = buf.readLine()) != null && str.equals("")){
                //接收从客户端发送过来的数据
                System.out.println(str);
                out.writeChars("接收到你的消息了"+str);
                out.flush();
            }
            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
