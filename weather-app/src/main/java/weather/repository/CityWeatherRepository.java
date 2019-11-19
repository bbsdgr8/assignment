package weather.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import weather.model.CityWeather;

@Repository
public interface CityWeatherRepository extends MongoRepository<CityWeather, String> {    

	List<CityWeather> findByCity(String city);

    List<CityWeather> findByDate(String date);    
    
}
