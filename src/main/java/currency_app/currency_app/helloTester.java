package currency_app.currency_app;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Objects;

@RestController
public class helloTester {

   @Autowired
    CurrencyRepository repository ;



    @RequestMapping("/")
    public String helloWorld() throws Exception{
    //public List<currency> helloWorld() throws Exception{
        //repository.deleteAll();
        return "<h3>Our Services</h3><ul>" +
                "<li><a href=\"/getdata?first_currency=USD&second_currency=LEK\" >USD values to LEK </a></li>"+
                "<li><a href=\"/getdata?currency=EUR&second_currency=LEK\" >EUR values to LEK </a></li>"+
                "<li><a href=\"/getdata?currency=LEK&second_currency=EUR\" >LEK values to EUR </a></li>"+
                "<li><a href=\"/getdata?currency=LEK&second_currency=USD\" >LEK values to USD </a></li>"+
                "<li><a href=\"/getdata?currency=USD&second_currency=EUR\" >USD values to EUR</a></li>"+
                "<li><a href=\"/getdata?currency=EUR&second_currency=USD\" >EUR values to USD</a></li>"+
                "</ul>";

    }
    @CrossOrigin
    @RequestMapping(value="getdata",method = RequestMethod.GET)
    public List<currency>  getdata(@RequestParam(value = "first_currency",required = false,defaultValue ="LEK") String type1,
                                   @RequestParam(value = "second_currency",required = false,defaultValue ="EUR") String type2
                                   ){
        List <currency>listType1 = repository.findByTypeOrderByUpdateDateDesc(type1) ;
        List <currency>listType2 = repository.findByTypeOrderByUpdateDateDesc(type2) ;
        List <currency> finallist = new ArrayList<>() ;
        for(int j=0;j<listType1.size()-1;j++){
            currency a= new currency("merge",listType1.get(j).getValue()/listType2.get(j).getValue()*100, new Date(listType1.get(j).getUpdateDate()).toString() );
            finallist.add(a);
        }
        return  finallist;
    }

    @CrossOrigin
    @RequestMapping(value="getlast",method = RequestMethod.GET)
    public currency  getlast(@RequestParam(value = "first_currency",required = false,defaultValue ="LEK") String type1,
                             @RequestParam(value = "second_currency",required = false,defaultValue ="EUR") String type2
                             ){

        if (repository.findByTypeOrderByUpdateDateDesc(type1).size()>=50)
            repository.deleteTopByOrderByUpdateDateAsc();
        if (repository.findByTypeOrderByUpdateDateDesc(type1).size()>=50)
            repository.deleteTopByOrderByUpdateDateAsc();
            double def1 ;double def2 ; double vl1 ; double vl2 ;
        if (type1=="USD") def1=1.1;
            else if (type1=="EUR") def1=1.3;
            else def1=1.0;
        if (type2=="USD") def2=1.1;
            else if (type2=="EUR") def2=1.3;
            else def2=1.0;

            if(Math.floor(Math.random()*10)%2==0) {
                vl1 = Math.random() * 0.01 + def1;
                vl2 = def2;
            }
            else{
                vl1 =def1- Math.random()*0.01;
                vl2 =  def2;
            }
            currency crs1 = new currency(type1,vl1*100, Objects.toString(new Date().getTime(), null) );
            currency crs2 = new currency(type2,vl2*100, Objects.toString(new Date().getTime(), null) );
            repository.save(crs1);
            repository.save(crs2);

        return new currency("merge",vl1/vl2*100,Objects.toString(new Date (new Date().getTime()))) ;
    }

}
