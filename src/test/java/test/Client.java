package test;

import java.net.*;
import java.io.*;

public class Client {
    public void doJob(){
        int serverPort = 1000; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "192.168.1.101"; // это IP-адрес компьютера, где исполняется наша серверная программа. 
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Yes! I just got hold of the program.");
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом. 
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
//            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//            String line = null;
//            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
//            System.out.println();
            String line = null;
            int i=0;
            
            while (true) {
                line="message "+i;
                line=in.readLine();
                //line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("message from server : " + line);
                //out.writeUTF(line); // отсылаем введенную строку текста серверу.
                //out.flush(); // заставляем поток закончить передачу данных.
                i++;
                //Thread.sleep(500);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}