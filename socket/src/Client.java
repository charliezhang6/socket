import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        final String DEFAULT_SERVER_HOST="127.0.0.1";
        final int DEFAULT_PORT=8888;
        final String QUIT ="quit";
        Socket socket=null;
        //创建socket
        BufferedWriter writer=null;
        try {
            socket=new Socket(DEFAULT_SERVER_HOST,DEFAULT_PORT);
            writer=new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            BufferedReader consoleReader =new BufferedReader(
                    new InputStreamReader(System.in)
            );
            while (true) {
                String input=consoleReader.readLine();
                writer.write(input+"\n");
                writer.flush();
                String msg=reader.readLine();
                System.out.println(msg);
                if(QUIT.equals(input)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                    System.out.println("关闭socket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
