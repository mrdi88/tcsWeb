package test;




import com.avectis.transportcontrol.facade.CarFacade;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CarView;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.DriverView;
import com.avectis.transportcontrol.view.QueueView;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"/tcsDataBase.xml", "/tcsFacade.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@TransactionConfiguration(transactionManager = "transactionManager")
public class FacadeDAOTest extends AbstractJUnit4SpringContextTests {//AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CarFacade carFacade;
    @Autowired
    private CardFacade cardFacade;
    @Autowired
    private QueueFacade queueFacade;

    @Test
    //@Rollback(false)
    public void firstCarFacadeTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CarView car= createCar();
        car.setId(carFacade.add(car));
        System.out.println("===");
        CarView saved_car=carFacade.get(car.getId());
        car.getDriver().setId(saved_car.getDriver().getId());
        car.getCargo().setId(saved_car.getCargo().getId());
        System.out.println("add : "+car);
        System.out.println("get : "+saved_car);
        //get car
        assertEquals(car,saved_car);
        //create car list
        car.setId(0);
        car.getCargo().setId(0);
        car.getDriver().setId(0);
        Long startTime=new Date().getTime();
        for (int i=0; i<9; i++){
            car.setCreateDate(new Date());
            carFacade.add(car);
        }
        Long endTime=new Date().getTime();
        System.out.println("create time: "+(endTime-startTime));
        //get list
        List<CarView> cvList=carFacade.getList(null, null);
        assertEquals(cvList.size(),10);
        System.out.println("car list ===");
        startTime=new Date().getTime();
        for (CarView cv:cvList){
            //System.out.println(cv);
        }
        endTime=new Date().getTime();
        System.out.println("read time: "+(endTime-startTime));
        //update 
        saved_car.getDriver().setName("Sasha");
        carFacade.update(saved_car);
        car = carFacade.get(saved_car.getId());
        assertEquals(car.getDriver().getName(),saved_car.getDriver().getName());
        //delete
        cvList=carFacade.getList(null, null);
        startTime=new Date().getTime();
        for (CarView cv:cvList){
            System.out.println("delete start: "+cv);
            carFacade.delete(cv);
        }
        endTime=new Date().getTime();
        System.out.println("delete time: "+(endTime-startTime));
        //check deleted
        cvList=carFacade.getList(null, null);
        assertEquals(cvList.size(),0);
    }
    @Test
    //@Rollback(false)
    public void secondCardFacadeTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        CarView car= createCar();
        car.setId(carFacade.add(car));
        //new Card
        CardView card = new CardView();
        card.setCar(car);
        card.setCreateDate(new Date());
        card.setCardNumber("6565");
        card.setId(cardFacade.add(card));
        CardView saved_card=cardFacade.getCard(card.getId());
        card.getCar().getDriver().setId(saved_card.getCar().getDriver().getId());
        card.getCar().getCargo().setId(saved_card.getCar().getCargo().getId());
        System.out.println("addCard : "+card);
        System.out.println("getCard : "+saved_card);
        //get car
        assertEquals(card,saved_card);
        saved_card.getCar().getDriver().setName("Ricky");
        cardFacade.update(saved_card);
        cardFacade.getCard(saved_card.getId());
        assertEquals(saved_card.getCar().getDriver().getName(),"Ricky");
        //create card list
        card.setId(0);
        card.getCar().setId(0);
        card.getCar().getCargo().setId(0);
        card.getCar().getDriver().setId(0);
        Long startTime=new Date().getTime();
        for (int i=0; i<9; i++){
            card.getCar().setCreateDate(new Date());
            card.getCar().setId(0);
            card.getCar().setId(carFacade.add(card.getCar()));
            card.setCreateDate(new Date());
            card.setCardNumber("6565");
            cardFacade.add(card);
        }
        Long endTime=new Date().getTime();
        System.out.println("create time: "+(endTime-startTime));
        //get list
        //delete
        List<CardView> cvList= cardFacade.getList();
        assertEquals(cvList.size(),10);
        startTime=new Date().getTime();
        for (CardView cv:cvList){
            System.out.println("delete : "+cv);
            cardFacade.delete(cv);
            carFacade.delete(cv.getCar());
        }
        endTime=new Date().getTime();
        System.out.println("delete time: "+(endTime-startTime));
        //check
        cvList= cardFacade.getList();
        assertEquals(cvList.size(),0);
    }
    @Test
    public void thirdQueueFacade(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        //greate car
        QueueView queue=new QueueView();
        queue.setName("R01");
        queue.getCards().add(createCard());
        queue.getCards().add(createCard());
        queue.getCards().add(createCard());
        queue.setId(queueFacade.addQueue(queue));
        //get
        QueueView saved_queue = queueFacade.getQueue(queue.getId());
        System.out.println("add qv: "+queue);
        System.out.println("get qv: "+saved_queue);
        assertEquals(queue.getCards().size(),saved_queue.getCards().size());
        //add second
        queue=new QueueView();
        queue.setName("R02");
        queue.getCards().add(createCard());
        queue.getCards().add(createCard());
        queue.getCards().add(createCard());
        queue.getCards().add(createCard());
        queue.getCards().add(createCard());
        queue.setId(queueFacade.addQueue(queue));
        System.out.println("R02 created: "+queue);
        //update
        int  size=queue.getCards().size();
        saved_queue=queueFacade.getQueue(queue.getId());
        System.out.println("R02 get    : "+queue);
        saved_queue.getCards().remove(0);
        queueFacade.update(saved_queue);
        saved_queue=queueFacade.getQueue(queue.getId());
        assertEquals(saved_queue.getCards().size(),size-1);
        //delete card from queues
        saved_queue=queueFacade.getQueue(saved_queue.getId());
        queueFacade.deleteCardFromQueues(saved_queue.getCards().get(0));
        saved_queue=queueFacade.getQueue(saved_queue.getId());
        assertEquals(saved_queue.getCards().size(),size-2);
        //delete
        List<QueueView> qvList=queueFacade.getQueueList();
        assertEquals(qvList.size(),2);
        for (QueueView qv:qvList){
            System.out.println("delete qv: "+qv);
            queueFacade.delete(qv);
        }
        qvList=queueFacade.getQueueList();
        assertEquals(qvList.size(),0);
        //delete cards, cars
        List<CardView> cvList=cardFacade.getList();
        for (CardView cv:cvList){
                cardFacade.delete(cv);
                carFacade.delete(cv.getCar());
        }
    }
    private CarView createCar(){
        CarView car= new CarView();
        DriverView driver = new DriverView("Dima","+375292051312","avectis");
        CargoView cargo = new CargoView();
        car.setCreateDate(new Date());
        car.setCarNumber("4700-EM1");
        car.setTtnNumber("4700-EM2");
        car.setCargo(cargo);
        car.setDriver(driver);
        return car;
    }
    private CardView createCard(){
        CarView car= createCar();
        car.setId(carFacade.add(car));
        CardView card = new CardView();
        card.setCardNumber("4565");
        card.setCar(car);
        card.setCreateDate(new Date());
        card.setId(cardFacade.add(card));
        return card;
    }
}
