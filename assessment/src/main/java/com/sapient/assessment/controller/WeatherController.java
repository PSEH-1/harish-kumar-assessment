package com.sapient.assessment.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController()
public class WeatherController {
	
	@RequestMapping(value = "/forecast/{city}/{country}/prediction", method=RequestMethod.GET)
	public String getWeatherForeCast(@PathVariable final String city, @PathVariable final String country)
	{
		 Map<String, Object> map = getRestOutputMap("https://samples.openweathermap.org/data/2.5/forecast?q="+city+","+country+"&appid=b6907d289e10d714a6e88b30761fae22");
		 List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
		 
		 String retValue = null;
		 
		 for(Map<String, Object> obj : list)
		 {
			List<Map<String, Object>> weather = (List<Map<String, Object>>)obj.get("weather");
			if(!weather.get(0).get("main").equals("Clear"))
			{
				retValue = "Carry umbrella";
				return retValue;
			}		
			else
			{
				Map<String, Object> temperature = (Map<String, Object>)obj.get("main");
				Double temperature2 = (Double)temperature.get("temp");
				temperature2 = (Double) (temperature2 - 273.15);
				if(temperature2 > 40)
					retValue = "Use sunscreen lotion";
			}
		 }		 
		 return retValue;	
	}
	
	@RequestMapping(value = "/forecast/{city}/{country}/highTemperature", method=RequestMethod.GET)
	public String getWeatherHighTemp(@PathVariable final String city, @PathVariable final String country)
	{
		 Map<String, Object> map = getRestOutputMap("https://samples.openweathermap.org/data/2.5/forecast?q="+city+","+country+"&appid=b6907d289e10d714a6e88b30761fae22");
		 List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
		 
		 Double retValue = 0.0;
		 
		 for(Map<String, Object> obj : list)
		 {
			 Map<String, Object> temperature = (Map<String, Object>)obj.get("main");
			 Double temperature2 = (Double)temperature.get("temp");
			 temperature2 = (Double) (temperature2 - 273.15);
			 if(temperature2 > retValue)
				 retValue = temperature2;
		 }		 
		 return roundTemperature(retValue);	
	}
	
	@RequestMapping(value = "/forecast/{city}/{country}/lowTemperature", method=RequestMethod.GET)
	public String getWeatherLowTemp(@PathVariable final String city, @PathVariable final String country)
	{	 
		 Map<String, Object> map = getRestOutputMap("https://samples.openweathermap.org/data/2.5/forecast?q="+city+","+country+"&appid=b6907d289e10d714a6e88b30761fae22");;
		 List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
		 
		 Double retValue = 0.0;
		 
		 for(Map<String, Object> obj : list)
		 {
			 Map<String, Object> temperature = (Map<String, Object>)obj.get("main");
			 Double temperature2 = (Double)temperature.get("temp");
			 temperature2 = (Double) (temperature2 - 273.15);
			 if(temperature2 < retValue)
				 retValue = temperature2;
		 }		 
		 return roundTemperature(retValue);	
	}
	
	private Map<String, Object> getRestOutputMap(String url)
	{
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);		
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(result);
		return map;
	}
	
	private String roundTemperature(Double temp)
	{
		DecimalFormat df2 = new DecimalFormat("#.##");
		return df2.format(temp)+" C";
		
	}
}