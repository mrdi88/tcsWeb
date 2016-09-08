package suit;

import test.FacadeDAOTest;
import test.CarControllerTest;
import javax.naming.NamingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 *
 * @author Dpoplauski
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    //FacadeControlTest.class,
    FacadeDAOTest.class,
    CarControllerTest.class
})
public class TcsSuitTest {

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
}