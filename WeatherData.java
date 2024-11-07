
public class WeatherData {
	private String date;
	private float hourlyAltimeter;
	private int hourlyDewPoint;
	private int hourlyDryBulb;
	private String hourlySky;
	
	public WeatherData(String nDate, float nHourlyAltimeter, int nHourlyDewPoint, int nHourlyDryBulb, String nHourlySky) {
		date = nDate;
		hourlyAltimeter = nHourlyAltimeter;
		hourlyDewPoint = nHourlyDewPoint;
		hourlyDryBulb = nHourlyDryBulb;
		hourlySky = nHourlySky;
	}
	
	public String getDate() {
		return date;
	}
	
	public float getHourlyAltimeter() {
		return hourlyAltimeter;
	}
	
	public int getHourlyDewPoint() {
		return hourlyDewPoint;
	}
	
	public int getHourlyDryBulb() {
		return hourlyDryBulb;
	}
	
	public String getHourlySky() {
		return hourlySky;
	}
	
}
