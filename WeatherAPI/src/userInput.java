import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class userInput {
	public static String callFunction() {
		 BufferedReader reader =
                 new BufferedReader(new InputStreamReader(System.in));
      String input = null;
	try {
		input = reader.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Invalid entry, please try again.");
	}
    return input;
	}
	
	public static String formatString(String input) {
		// this function formats an inputted string (takes away whitespaces and capitlizes the first letter of words)
		
		// trim leading and trailing whitespaces
		String tempString = input.trim();
		
		// uppercase all words like San (F)francisco
		int stringCounter = 1;
		String formattedString = "";
		for (int i = 0; i < tempString.length(); i++) {
			String c = tempString.substring(i,i+1);
			if (c.isBlank()) {
				stringCounter++;
				formattedString += c;
			} else if(stringCounter == 1) {
				c = c.toUpperCase();
				formattedString += c;
				stringCounter = 0;
			} else {
				formattedString += c;
			}
		}
		return formattedString;
	}
	
	public static void printWeatherData(String userCity, JSONObject userWeatherData) {
		System.out.println(userCity + "'s" + " current temperature is " + userWeatherData.getDouble("mainTemperatureCurrent") + "°F with " + userWeatherData.getString("weatherDescription") + ".");
		System.out.println("The high for today is " + userWeatherData.getDouble("mainTempMax")+ "°F.");
		System.out.println("The low for today is " + userWeatherData.getDouble("mainTempMin")+ "°F.");
	}
}