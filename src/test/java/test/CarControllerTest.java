package test;


import com.avectis.transportcontrol.web.controller.archive.ArchiveController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = {"/tcsDataBase.xml", "/tcsFacade.xml","/scanner-context.xml", "/mvc-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class CarControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ArchiveController carController;

    @Test
    public void professionTest() {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        try {
            carController.handleRequest(req, new MockHttpServletResponse());
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
