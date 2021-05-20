import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        final int DEFAULT_PORT=8888;
        final String QUIT ="quit";
        ServerSocket serverSocket=null;
        try {
            //绑定端口
            serverSocket=new ServerSocket(DEFAULT_PORT);
            System.out.println("正在监听"+DEFAULT_PORT+"端口");
            while(true){
                Socket socket=serverSocket.accept();
                System.out.println("客户端"+socket.getPort()+"已连接");
                BufferedReader reader =new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                BufferedWriter writer =new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );
                //读取信息
                String msg=null;
                while ((msg=reader.readLine())!=null) {
                    System.out.println("客户端"+socket.getPort()+':'+msg);
                    writer.write("收到"+msg+"\n");
                    writer.flush();
                    if(QUIT.equals(msg)){
                        System.out.println("客户端"+socket.getPort()+"已退出");
                        break;
                    }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
            try {
                serverSocket.close();
                System.out.println("关闭服务器");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }}

    }
}
