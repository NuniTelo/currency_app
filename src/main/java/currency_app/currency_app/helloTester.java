package currency_app.currency_app;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Date;

@RestController
public class helloTester {

   @Autowired
    CurrencyRepository repository ;


    @RequestMapping("/")
    public String helloWorld() throws Exception{
    //public List<currency> helloWorld() throws Exception{

        // save a couple of currencys
        repository.save(new currency("USD", 134.5,new Date().toString()));
        repository.save(new currency("EUR", 114.6,new Date().toString()));
        repository.save(new currency("LEK", 100.0,new Date().toString()));

        // fetch all currencys
        System.out.println("currencys found with findAll():");
        System.out.println("-------------------------------");
        for (currency currency : repository.findAll()) {
            System.out.println(currency);
        }
        //return repository.findByType("USD");
        return "<h3>Our Services</h3><ul>" +
                "<li><a href=\"/getdata?currency=USD\" >USD values </a></li>"+
                "<li><a href=\"/getdata?currency=EUR\" >EUR values </a></li>"+
                "<li><a href=\"/getdata?currency=LEK\" >LEK values </a></li>"+
                "</ul>";

    }

    @RequestMapping(value="getdata",method = RequestMethod.GET)
    public List<currency>  getdata(@RequestParam(value = "currency",required = false,defaultValue ="LEK") String type){
        return repository.findByType(type) ;
    }}
