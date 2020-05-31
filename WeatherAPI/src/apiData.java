/*import java.net.URI; //IGNORE these are for method 2 
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class apiData {
	public static java.util.List<String> callAPI(String city) {
		//Method 1
		BufferedReader reader;  
		String line;
		StringBuffer responseContent = new StringBuffer();
		
		try {
			//this is creating a new instance, url that is the website I want to access
			URL url = new URL ("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=imperial&APPID=6cd7668f1c79645fc6153b96c393646f ");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			//trying to use the Get method on the API to retrieve the data
			connection.setRequestMethod("GET");
			//making a timeout in case it takes too long so it doesn't make the rest of the program not work
			connection.setConnectTimeout(50000); // in milliseconds
			connection.setReadTimeout(5000);
			
			//check status
			int status = connection.getResponseCode();
			//System.out.println("The status is " + status); //should be 200
			 if (status > 299) {
				 System.out.println("You dittly darn meeper, you're trying to find the weather of a non-existent city, try again.");
			 	return apiData.callAPI(userInput.callFunction());
			 } else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				 while ((line = reader.readLine()) != null) {
					 responseContent.append(line);
				 }
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return Arrays.asList(responseContent.toString(),city);
		/*// Method 2 New and simpler java.net.http.HttpClient
		String desiredURL = "https://api.openweathermap.org/data/2.5/weather?q=San Francisco,us&APPID=9a2c63f3974bd4f61ac65506d93e2ebd";
		//System.out.println(desiredURL);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=San Francisco,us&APPID=9a2c63f3974bd4f61ac65506d93e2ebd")).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenAccept(System.out::println)
			.join();*/
		}
	
	public static JSONObject parseData(String responseBody) {
		/* notes: JSON uses keys and then values, use (key:"ID" or something)
		 * then you request it as a string, getString()
		 * or as an int getInt();
		 */
		JSONObject weatherData = new JSONObject(responseBody);
		
		// getting the weather key array which is the description(rain,sunny, etc)
		JSONArray weatherArray = weatherData.getJSONArray("weather");
		
		// getting the object inside the array 
		JSONObject weatherObject = weatherArray.getJSONObject(0); 
		
		// getting the key value pairs for my desired data, camel case, (object)String so weatherIcon etc
		String weatherDescription = weatherObject.getString("description");
		String weatherIcon = weatherObject.getString("icon");
		
		// getting temperature in the main object
		JSONObject mainObject = weatherData.getJSONObject("main");
		
		// getting temperatures
		double mainTemperatureCurrent = mainObject.getDouble("temp");
		double mainTempMin = mainObject.getDouble("temp_min");
		double mainTempMax = mainObject.getDouble("temp_max");
		
		// putting all the data into a readable JSON object with only what I need called allWeatherData
		JSONObject allWeatherData = new JSONObject();
		allWeatherData = allWeatherData.put("weatherDescription", weatherDescription);
		allWeatherData = allWeatherData.put("weatherIcon", weatherIcon);
		allWeatherData = allWeatherData.put("mainTemperatureCurrent", mainTemperatureCurrent);
		allWeatherData = allWeatherData.put("mainTempMax", mainTempMax);
		allWeatherData = allWeatherData.put("mainTempMin", mainTempMin);
		return allWeatherData;
	}
		
}
	
	
