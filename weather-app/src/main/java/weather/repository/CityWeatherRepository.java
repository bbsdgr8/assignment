package weather.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import weather.model.CityWeather;

@Repository
public interface CityWeatherRepository extends MongoRepository<CityWeather, String> {    

	List<CityWeather> findByCity(String city);
	
	@Query(value="{date : {$lte: ?0}}", delete = true)
	void deleteByDate(Date date);

    
    @Query(value = "{'city': ?0, 'date': ?1}")
    CityWeather findByCityAndDate(String city, Date date);
    
}
