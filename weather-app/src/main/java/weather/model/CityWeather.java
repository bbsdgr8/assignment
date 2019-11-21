package weather.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="cityWeather")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityWeather {

	@Id
    private ObjectId id;

	@Field
	private Date date;
	@Field
	private String city;
	private String summary;
	private String time;		
	private String icon;
	private String nearestStormDistance;
	private String nearestStormBearing;
	private String precipIntensity;
	private String precipProbability;
	private String temperature;
	private String apparentTemperature;
	private String dewPoint;
	private String humidity;
	private String pressure;
	private String windSpeed;
	private String windGust;
	private String windBearing;
	private String cloudCover;
	private String uvIndex;
	private String visibility;
	private String ozone;
	private String precipType;
	private String precipAccumulation;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNearestStormDistance() {
		return nearestStormDistance;
	}
	public void setNearestStormDistance(String nearestStormDistance) {
		this.nearestStormDistance = nearestStormDistance;
	}
	public String getNearestStormBearing() {
		return nearestStormBearing;
	}
	public void setNearestStormBearing(String nearestStormBearing) {
		this.nearestStormBearing = nearestStormBearing;
	}
	public String getPrecipIntensity() {
		return precipIntensity;
	}
	public void setPrecipIntensity(String precipIntensity) {
		this.precipIntensity = precipIntensity;
	}
	public String getPrecipProbability() {
		return precipProbability;
	}
	public void setPrecipProbability(String precipProbability) {
		this.precipProbability = precipProbability;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getApparentTemperature() {
		return apparentTemperature;
	}
	public void setApparentTemperature(String apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}
	public String getDewPoint() {
		return dewPoint;
	}
	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindGust() {
		return windGust;
	}
	public void setWindGust(String windGust) {
		this.windGust = windGust;
	}
	public String getWindBearing() {
		return windBearing;
	}
	public void setWindBearing(String windBearing) {
		this.windBearing = windBearing;
	}
	public String getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(String cloudCover) {
		this.cloudCover = cloudCover;
	}
	public String getUvIndex() {
		return uvIndex;
	}
	public void setUvIndex(String uvIndex) {
		this.uvIndex = uvIndex;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getOzone() {
		return ozone;
	}
	public void setOzone(String ozone) {
		this.ozone = ozone;
	}
	public String getPrecipType() {
		return precipType;
	}
	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}
	public String getPrecipAccumulation() {
		return precipAccumulation;
	}
	public void setPrecipAccumulation(String precipAccumulation) {
		this.precipAccumulation = precipAccumulation;
	}

}
