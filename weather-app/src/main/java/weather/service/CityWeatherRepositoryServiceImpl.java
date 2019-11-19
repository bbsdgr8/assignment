package weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weather.model.CityWeather;
import weather.repository.CityWeatherRepository;

@Service
public class CityWeatherRepositoryServiceImpl implements CityWeatherRepositoryService {

	@Autowired
	CityWeatherRepository cityWeatherRepository;
	
	@Override
	public CityWeather findByCityAndDate(String city, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByCityAndDate(String city, String date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createCityWeather(CityWeather cityWeather) {
		cityWeatherRepository.save(cityWeather);
		
	}

}
