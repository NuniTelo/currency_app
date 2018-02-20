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
    public List<currency> helloWorld() throws Exception{

        repository.deleteAll();

        // save a couple of currencys
        repository.save(new currency("USD", 134.5,new Date().toString()));
        repository.save(new currency("EUR", 114.6,new Date().toString()));

        // fetch all currencys
        System.out.println("currencys found with findAll():");
        System.out.println("-------------------------------");
        for (currency currency : repository.findAll()) {
            System.out.println(currency);
        }
        return repository.findByType("USD");


    }

    @RequestMapping(value="greet",method = RequestMethod.GET)
    public String  greetMe(@RequestParam(value = "name",required = false,defaultValue ="") String name,
                           @RequestParam(value = "age",required = false,defaultValue = "0" ) Integer age
                           ){
        return "Gretings "+name+"! Your age is "+age+"!" ;
    }}
