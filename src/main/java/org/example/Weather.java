package org.example;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Weather {
    public static void main(String[] args) {

        port(8080);//port used by spark framework

        // Define a route to render the form
        get("/", (req, res) -> {
            return "<form action='/' method='post'>" +
                    "Enter the city name: <input type='text' name='cityname'>" +
                    "<input type='submit' value='Submit'>" +
                    "</form>";
        });

        // Define a route to handle form submission
        post("/", (req, res) -> {
            String city = req.queryParams("cityname");
            String apiKey = "d94a1a5aa3c285a26a3d45c0e357d771";
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            try {
                String jsonText=""; //this will contain the JSON response obtained from the openweather API call
                URL url=new URL(apiUrl);
                InputStream is=url.openStream();
                BufferedReader bufferReader=new BufferedReader(new InputStreamReader(is));
                String line;//String to iterate through JSON within the loop
                while((line=bufferReader.readLine())!=null){
                    jsonText+=line;
                }
                is.close();//closing the inputstream
                bufferReader.close();//closing the bufferreader
                Manager m1=new Manager();
                String result=m1.data(jsonText);
                return result;

            } catch (IOException e) {
                e.printStackTrace();
                return "Error fetching temperature information";
            }
        });
    }

}
