/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import java.util.TimeZone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dima
 */
public class Test2 {
    
    public Test2() {
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
    public void hello() throws InterruptedException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date date1 = new Date();
        Thread.sleep(1000);
        Date date2 = new Date();
        System.out.println("date1 :"+date1);
        System.out.println("date2 :"+date2);
        ch(date1,date2);
        date2.setTime(0);
        System.out.println("date1 :"+date1);
        System.out.println("date2 :"+date2);
    }
    public void ch(Date d1, Date d2){
        d1.setTime(0);
    }
}
