import java.util.List;

import org.json.JSONObject;



public class weatherMain{
	public static void main(String[] args) {
		System.out.println("Welcome to Kyle's weather app!\nPlease enter your city below:");
		// get city and upper-case it 
		String userCity = userInput.callFunction();
		// get response data as a list and separate each value in the list (responseBody and userCity)
		List<String> apiResponse = apiData.callAPI(userCity);
		String responseBody = apiResponse.get(0);
		userCity = apiResponse.get(1);
		// parse JSON data from API and put it into a readable JSON object
		JSONObject userWeatherData = apiData.parseData(responseBody);
		//typing out the information for the user to see, uppercase the value of userCity first
		userCity = userInput.formatString(userCity);
		userInput.printWeatherData(userCity, userWeatherData);
	}
}
