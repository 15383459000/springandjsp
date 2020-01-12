package com.xitianfo.chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author meteor
 */
public class FileServer {

    private String address;
    private int port;
    private String basePath;


    /**
     * 主函数
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage fileserver <port> <path>");
            System.exit(-1);
        }

        FileServer fileServer = new FileServer("0.0.0.0", Integer.valueOf(args[0]), args[1]);
        fileServer.start();
    }

    /**
     * 构造函数
     */
    public FileServer(String address, int port, String basePath) {
        this.address = address;
        this.port = port;
        this.basePath = basePath;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(address, port));
        System.out.println("服务器在" + port + "端口启动了,要请求的资源文件在" + basePath);
        try {
            while (true) {

                System.out.println("等待新请求...");
                // 新连接进入
                Socket socket = serverSocket.accept();

                // 用来读取数据的管道
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 用来写出数据的管道
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // 等待输入，等待超过3秒后放弃本次请求
                boolean ready = waitForReaderReady(reader);

                // 客户端数据传输失败
                if (!ready) {
                    socket.close();
                    continue;
                }

                String requestLine = reader.readLine();

                String path = requestLine.split(" ")[1];
                System.out.println("正在请求文件：" + path);

//            /Users/meteor/Desktop/static/a.html
                File file = new File(basePath, path);

                if (!file.exists() || file.isDirectory()) {
                    System.out.println("file not found");
                    writer.write("HTTP/1.1 404 notfound\r\n");
                    writer.write("content-length:9\r\n");
                    writer.write("\r\n");
                    writer.write("not found");
                    writer.flush();
                } else {
                    InputStream ins = new FileInputStream(file);
                    byte[] buffer = new byte[1024];

                    writer.write("HTTP/1.1 200 OK\r\n");
                    writer.write("content-length:" + file.length() + "\r\n");
                    writer.write("\r\n");
                    writer.flush();
                    int len;

                    while ((len = ins.read(buffer)) != -1) {
                        socket.getOutputStream().write(buffer, 0, len);
                    }
                    socket.getOutputStream().flush();
                }
                socket.close();
                System.out.println("请求完成。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待客户端数据传输完成，在传输完成的一瞬间返回true，等3秒还没完成的话返回false
     * @param reader
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private boolean waitForReaderReady(BufferedReader reader) throws IOException, InterruptedException {
        int retry = 10 * 100 * 3;
        while (retry-- > 0) {
            if (reader.ready()) {
                return true;
            }
            Thread.sleep(10);
        }
        return false;
    }

}
