package currency_app.currency_app;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrencyRepository extends MongoRepository<currency,String>{
    public List <currency> findByTypeOrderByUpdateDateDesc(String type);
    public currency findTopByOrderByUpdateDateAsc(String type);

}
