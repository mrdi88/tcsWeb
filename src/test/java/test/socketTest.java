/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dima
 */
public class socketTest {
    private String ipAddr="192.168.1.101";
    private int port = 1000;
    private Socket socket1;
    private Socket socket2;
    private InetAddress ipAddress;
    
    public socketTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void socket() throws UnknownHostException {
//        Thread serverThread= new Thread(){
//            @Override
//            public void run(){
//                Server server=new Server();
//                server.doJob();
//            }
//        };
//        Thread clientThread= new Thread(){
//            @Override
//            public void run(){
//                Client client=new Client();
//                client.doJob();
//            }
//        };
//        //serverThread.start();
//        clientThread.start();
//        
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(socketTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try{
            ipAddress = InetAddress.getByName(ipAddr);

            new Thread(){
                @Override
                public void run(){
                    try{

                        socket1 = new Socket(ipAddress,port);
                        socket1.setSoTimeout(1000);

                        while(true){
                            try{
                                InputStream sin = socket1.getInputStream();
                                DataInputStream in = new DataInputStream(sin);
                                String line = in.readLine();
                                System.out.println(line);
                            }catch(IOException ex)
                            {
                                try{
                                    if (!ipAddress.isReachable(100)){
                                        System.out.println("NOT REACHABLE");
                                    };
                                    socket2 = new Socket(ipAddress,port);
                                    socket2.setSoTimeout(1000);
                                    System.out.println("RECONNECTING");
                                    socket1.close();
                                    socket1=socket2;
                                }
                                catch(Exception e)
                                {
                                }
                            }
                        }
                    }
                    catch(IOException ex)
                    {
                        System.out.println(ex);
                        ex.printStackTrace();
                    }
                }
            }.start();
            Thread.sleep(600000);
        }catch(Exception w){

        }  
    }
}
    

