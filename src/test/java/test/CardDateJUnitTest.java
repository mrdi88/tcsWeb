/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.view.CardView;
import java.util.Date;
import java.util.Objects;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 *
 * @author Dima
 */
@ContextConfiguration(locations = {"/tcsDataBase.xml", "/tcsFacade.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardDateJUnitTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private CardFacade cardFacade;

    public CardDateJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:mysql://localhost:3306/tcs", "root", "root123");
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            builder.bind("java:comp/env/tcsDS", ds);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            Assert.fail();
        } catch (NamingException ex) {
            ex.printStackTrace();
            Assert.fail();
        }
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
    public void t1() {
        int i = 1000;
        CardView card;
        card  = cardFacade.getCardByNumber("056,22570");
        Date d=card.getCar().getCreateDate();
        Date pre_d=card.getCar().getCreateDate();
        while (i>0){
           card  = cardFacade.getCardByNumber("056,22570");
           d=card.getCar().getCreateDate();
           if (!Objects.equals(d, pre_d)){
               System.out.println("no equal");
           }
           pre_d=d;
           i--;
        }
        
    }
}
