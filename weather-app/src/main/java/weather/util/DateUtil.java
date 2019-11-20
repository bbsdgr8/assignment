package weather.util;


import java.text.ParseException;
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(currentDate);		
		return date;		
	}
	
	public String getLocalDateToday() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy zzz");
		return dateFormat.format(currentDate);	
	}
	
	public Date formatDate(Date date) {						
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String strDate = dateFormat.format(date);			
			Date newDate = dateFormat.parse(strDate);			
			return newDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
