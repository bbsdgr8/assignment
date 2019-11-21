package weather.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weather.model.City;
import weather.model.CityWeather;
import weather.util.DateUtil;

@Service
public class DarkSkyAPIService {	
	private static final Logger logger = LogManager.getLogger("WeatherAppLogger");
	
	@Autowired
	CityWeatherRepositoryServiceImpl cityWeatherRepositoryService;
	
	@Value("${proxy.url}")
	String proxyUrl;
	
	@Value("${proxy.port}")
	int proxyPort;
	
	@Value("${proxy.required}")
	boolean isProxyRequired;
	
	@Value("#{${darkSky.city.map}}")
	Map<String, String> cityMap;
	
	@Value("${darkSky.url}")
	String darkSkyUrl;
	
	@Value("${darkSky.options}")
	String darkSkyOptions;
	
	@Value("${darkSky.key}")
	String darkSkyKey;
	
	public List<CityWeather> getTodayWeather() {
		DateUtil dateUtil = new DateUtil();
		Date dateToday = dateUtil.formatDate(new Date());
		logger.info("Initialize get weather today:" + dateToday);
		List<CityWeather> cityWeatherList = new ArrayList<CityWeather>();
		for (String key : cityMap.keySet()) {
			CityWeather dbCityWeather = cityWeatherRepositoryService.findByCityAndDate(key, dateToday);
			if (null == dbCityWeather) {
				CityWeather cityWeather = fetchDarkSkyAPI(key);
				if (null != cityWeather) {					
					cityWeatherRepositoryService.createCityWeather(cityWeather, key, dateToday);
					cityWeatherList.add(cityWeather);					
				}
			} else {
				cityWeatherList.add(dbCityWeather);
				logger.info("Weather for city: " + key + " and date: " + dateToday + "already exists");
			}			
		}
		return cityWeatherList;
	}
	
	private CityWeather fetchDarkSkyAPI(String cityKey) {
		RestTemplate restTemplate;
		String apiUrl = darkSkyUrl + darkSkyKey + "/" +  cityMap.get(cityKey) + darkSkyOptions;
		logger.info("DarkSky API request: "+ apiUrl);
		if (isProxyRequired) {
			SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUrl, proxyPort));
			clientHttpReq.setProxy(proxy);
			
			restTemplate = new RestTemplate(clientHttpReq);
		} else {
			restTemplate = new RestTemplate();
		}
		
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
		logger.info("API result for "+ cityKey +": " +result.getBody());
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {						
			City city = objectMapper.readValue(result.getBody(), City.class);			
			return city.getCurrently();					    
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Scheduled(cron = "0 0 */12 * * *")
	public void houseKeepRecords() {
		Date today = new Date();
		Date threeDaysAgo = new Date(today.getTime() - (3*60*60*24*1000));
		cityWeatherRepositoryService.deleteByDate(threeDaysAgo);
		System.out.println("yes");
	}
}
