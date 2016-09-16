package test;

import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CarView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.DriverView;
import com.avectis.transportcontrol.view.SampleView;
import java.util.Date;
import java.util.TimeZone;
import javax.naming.NamingException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"/tcsDataBase.xml", "/tcsFacade.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test1 extends AbstractJUnit4SpringContextTests {//AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CarFacade carFacade;
    @Autowired
    private CardFacade cardFacade;
    @Autowired
    private QueueFacade queueFacade;
    
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
    @Test
    //@Rollback(false)
    public void firstCarFacadeTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CarView car= createCar();
        car.setId(carFacade.add(car));
        System.out.println("===");
        CarView saved_car=carFacade.get(car.getId());
        System.out.println("add : "+car);
        System.out.println("get : "+saved_car);
        saved_car.getCargo().setSample(null);
        carFacade.update(saved_car);
        saved_car=carFacade.get(car.getId());
        System.out.println("upd : "+saved_car);
    }
    
    private CarView createCar(){
        CarView car= new CarView();
        DriverView driver = new DriverView("Dima777","+375292051312","avectis");
        CargoView cargo = new CargoView();
        SampleView sv=new SampleView();
        sv.setName("f323");
        sv.setGluten(Float.parseFloat("5.6"));
        sv.setHumidity(Float.parseFloat("3.60"));
        sv.setWeediness(Float.parseFloat("1.69"));
        cargo.setSample(sv);
        car.setCreateDate(new Date());
        car.setFirstNumber("0000-EM1");
        car.setSecondNumber("0000-EM2");
        car.setCargo(cargo);
        car.setDriver(driver);
        return car;
    }
    
}
