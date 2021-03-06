package currency_app.currency_app;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "currency")
public class currency {
    @Id
    private String  id ;
    private String type;
    private double value;
    private String updateDate;

    public currency(String type,double value,String updateDate ){
        this.type=type;
        this.value = value;
       this. updateDate= updateDate;
    }

    public String getType (){
        return this.type;
    }
    public void setType(String newType){
        this.type= newType;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getId() {
        return this.id;
    }
    @Override
    public String toString(){
        return "type:"+this.type+" value:"+this.value+" update Time:"+this.updateDate;
    }
}
