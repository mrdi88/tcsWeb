package test;




import com.avectis.transportcontrol.facade.BarrierFacade;
import com.avectis.transportcontrol.facade.InfoTableFacade;
import com.avectis.transportcontrol.facade.ScannerFacade;
import com.avectis.transportcontrol.facade.TrafficLightFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"/traffic_light-context.xml",
    "/barrier-context.xml","/infotable-context.xml","/scanner-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@TransactionConfiguration(transactionManager = "transactionManager")
public class FacadeControlTest extends AbstractJUnit4SpringContextTests {//AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BarrierFacade barrierFacade;
    @Autowired
    private ScannerFacade scannerFacade;
    @Autowired
    private TrafficLightFacade trafficLightFacade;
    @Autowired
    private InfoTableFacade infoTableFacade;

    @Test
    //@Rollback(false)
    public void firstTest() {
        barrierFacade.Open("barrier1");
        barrierFacade.Close("barrier1");
        barrierFacade.GetState("barrier1");

        barrierFacade.Open("barrier2");
        barrierFacade.Close("barrier2");
        barrierFacade.GetState("barrier2");

        scannerFacade.GetData("scanner1");
        scannerFacade.GetData("scanner2");

        trafficLightFacade.TurnOn("traffic_light1");
        trafficLightFacade.TurnOff("traffic_light1");
        trafficLightFacade.GetState("traffic_light1");

        trafficLightFacade.TurnOn("traffic_light2");
        trafficLightFacade.TurnOff("traffic_light2");
        trafficLightFacade.GetState("traffic_light2");

        infoTableFacade.SendData(new String[]{"hello", "world"}, "InfoTable1");
        infoTableFacade.getDateLastUpdate("InfoTable1");

        infoTableFacade.SendData(new String[]{"hello", "world"}, "InfoTable2");
        infoTableFacade.getDateLastUpdate("InfoTable2");
    }
}
