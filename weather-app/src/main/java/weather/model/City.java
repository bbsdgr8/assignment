package weather.model;

public class City {
	private String latitude ;
	private String longitude ;
	private String timezone ;
	private String offset;
	private CityWeather currently;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public CityWeather getCurrently() {
		return currently;
	}
	public void setCurrently(CityWeather currently) {
		this.currently = currently;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	
}
