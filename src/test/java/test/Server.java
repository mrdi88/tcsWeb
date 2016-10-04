package test;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server {
   public void doJob(){
     int port = 5555; // случайный порт (может быть любое число от 1025 до 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Waiting for a client...");

         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
         System.out.println();

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

        new Thread(){
            DataInputStream in;
            public Thread setIn(DataInputStream in){
                this.in=in;
                return this;
            }
            @Override
            public void run(){
                try {
                    while (true){
                        System.out.println("message from client "+in.readUTF());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.setIn(in).start();
        
         
         String line = null;
         int i=0;
         while(true) {
           Thread.sleep(500);
           line = "message";
           out.writeUTF(line+i); // отсылаем клиенту обратно ту самую строку текста.
           out.flush(); // заставляем поток закончить передачу данных.
           
           i++;
         }
      } catch(Exception x) { x.printStackTrace(); }
   }
}