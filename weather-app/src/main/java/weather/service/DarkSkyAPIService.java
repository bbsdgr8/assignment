package weather.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
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
	
	public String getTodayWeather() {
		for (String key : cityMap.keySet()) {
			fetchDarkSkyAPI(key);
		}
		return "";
	}
	
	private String fetchDarkSkyAPI(String cityKey) {
		RestTemplate restTemplate;
		String apiUrl = darkSkyUrl + darkSkyKey + "/" +  cityMap.get(cityKey) + darkSkyOptions;
		System.out.println(apiUrl);
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
		System.out.println(cityKey +": " +result.getBody());
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {			
			DateUtil dateUtil = new DateUtil();
			City city = objectMapper.readValue(result.getBody(), City.class);
			String dateToday = dateUtil.timestampToDate(new Date().getTime());
			CityWeather cityWeather = city.getCurrently();
			cityWeather.setDate(dateToday);
		    cityWeather.setCity(cityKey);
		    cityWeatherRepositoryService.createCityWeather(cityWeather);
			System.out.println();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
