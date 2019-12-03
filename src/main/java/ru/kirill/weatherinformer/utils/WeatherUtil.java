package ru.kirill.weatherinformer.utils;

import ru.kirill.weatherinformer.model.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherUtil {

    public WeatherUtil() {
    }

    /* Util method for parse JSON file from openweathermap.org or worldweatheronline.com
     * Get temperature, humidity and description and fill weather fields.
     * Example JSON file you can get at links:
     *
     * worldweatheronline.com
     * http://api.worldweatheronline.com/premium/v1/weather.ashx?key=e3cddeab40834f2fa94105455192611&q=Moscow&num_of_days=1&tp=3&format=json
     *
     * openweathermap.org
     * http://api.openweathermap.org/data/2.5/weather?q=Moscow&mode=json&appid=b1b35bba8b434a28a0be2a3e1071ae5b&units=metric
     */
    public static void parseWeather(Weather weather) {
        String jsonCall = null;
        Integer temperature = null;
        Integer humidity = null;
        String description = null;

        try {
            switch (weather.getWeatherProvider()) {
                case WORLDWEATHERONLINE:
                    jsonCall = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=e3cddeab40834f2fa94105455192611&q="
                            + weather.getCity() + "&num_of_days=1&tp=3&format=json";
                    break;

                case OPENWEATHERMAP:
                    jsonCall = "http://api.openweathermap.org/data/2.5/weather?q=" + weather.getCity()
                            + "&mode=json&appid=b1b35bba8b434a28a0be2a3e1071ae5b&units=metric";
                    break;

                default:
                    break;
            }

            URL url = new URL(jsonCall);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(url.openStream())));
            JSONObject mainMap;
            JSONArray jsonArray;

            switch (weather.getWeatherProvider()) {

                case OPENWEATHERMAP:
                    mainMap = (JSONObject) jsonObject.get("main");
                    jsonArray = (JSONArray) jsonObject.get("weather");

                    temperature = (int) Double.parseDouble(mainMap.get("temp").toString());
                    humidity = (int) Double.parseDouble(mainMap.get("humidity").toString());
                    description = ((JSONObject) jsonArray.get(0)).get("description").toString();
                    break;

                case WORLDWEATHERONLINE:
                    mainMap = (JSONObject) jsonObject.get("data");
                    jsonObject = (JSONObject) ((JSONArray) mainMap.get("current_condition")).get(0);
                    jsonArray = (JSONArray) jsonObject.get("weatherDesc");

                    temperature = Integer.parseInt(jsonObject.get("temp_C").toString());
                    humidity = Integer.parseInt(jsonObject.get("humidity").toString());
                    description = ((JSONObject) jsonArray.get(0)).get("value").toString();

                default:
                    break;
            }

            weather.setTemperature(temperature);
            weather.setHumidity(humidity);
            weather.setDescription(description);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Util method for check request cookies
    public static String getUserId(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies == null) {
            Cookie cookie = new Cookie("userCookie", sessionId);
            cookie.setMaxAge(2592000); //1 month
            response.addCookie(cookie);
        } else {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                    userId = cookie.getValue();
                }
            }
        }
        return userId;
    }
}
