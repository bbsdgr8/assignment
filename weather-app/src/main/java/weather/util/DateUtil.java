package weather.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public String timestampToDate(String ts) {		
		int tsInt = Integer.parseInt(ts);
		Date currentDate = new Date(tsInt);
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String date = dateFormat.format(currentDate);		
		return date;		
	}
	
	public String timestampToDate(long ts) {				
		Date currentDate = new Date(ts);
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String date = dateFormat.format(currentDate);		
		return date;		
	}
}
