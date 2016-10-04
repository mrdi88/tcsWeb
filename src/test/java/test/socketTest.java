/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
    public void socket() {
        Thread serverThread= new Thread(){
            @Override
            public void run(){
                Server server=new Server();
                server.doJob();
            }
        };
        Thread clientThread= new Thread(){
            @Override
            public void run(){
                Client client=new Client();
                client.doJob();
            }
        };
        //serverThread.start();
        clientThread.start();
        
        try {
            Thread.sleep(60000);
        } catch (InterruptedException ex) {
            Logger.getLogger(socketTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
