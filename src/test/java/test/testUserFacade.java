package test;

import com.avectis.transportcontrol.facade.UserFacade;
import com.avectis.transportcontrol.util.Password;
import com.avectis.transportcontrol.util.Role;
import com.avectis.transportcontrol.view.UserRoleView;
import com.avectis.transportcontrol.view.UserView;
import javax.naming.NamingException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
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
public class testUserFacade extends AbstractJUnit4SpringContextTests {//AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserFacade userFacade;
    
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
    public void firstTest() {
        System.out.println(userFacade.getUsers());
        UserView user = new UserView();
        user.setEnabled(true);
        //bcrypt
        user.setPassword(Password.hashPassword("123"));
        user.setUsername("dima");
        UserRoleView rv = new UserRoleView();
        rv.setUser(user.getUsername());
        rv.setRole(Role.ROLE_ADMIN);
        user.getUserRole().add(rv);
        userFacade.addUser(user);
        UserView userv = userFacade.getUserByName("dima");
        System.out.println(userv);
        assertEquals(true,Password.checkPassword("123", user.getPassword()));
        rv.setRole(Role.ROLE_USER);
        rv.setUserRoleId(null);
        userv.getUserRole().add(rv);
        userFacade.update(userv);
        System.out.println(userv);
        userv=userFacade.getUserByName(userv.getUsername());
        //userFacade.deleteUser(userv);
    }
}
