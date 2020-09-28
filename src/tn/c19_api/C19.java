
package tn.c19_api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.*;
/**
 *
 * @author Mohamed Kramti
 */
public class C19 {
    
   private static HttpURLConnection connection;
   private static HashMap<String, String> countries=new HashMap<String, String>();
   
   String country;
   public ArrayList<Stats> res;
   boolean countryExist=false;
   
   public C19(String country){
       this.country = country;
       if (!getCountries().keySet().contains(country)){
           System.out.println("Country does not exist is our server");
           return;
       }
       countryExist=true;
       StringBuilder responseContent;
       responseContent = connect("http://127.0.0.1:8080/COVID-19/api?country=" + country);
       
       if (responseContent==null){
           System.out.println("no reponse from the server");
           return;
       }
       
       parse(responseContent);

   }
   private static StringBuilder connect(String myURL){
       BufferedReader reader;
       String line;
       StringBuilder responseContent = new StringBuilder();

       try {
           URL url = new URL(myURL);
           connection = (HttpURLConnection) url.openConnection();

           connection.setRequestMethod("GET");
           connection.setReadTimeout(5000);
           connection.setConnectTimeout(5000);

           int status = connection.getResponseCode();
           if (status == 200) {
               reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
               while ((line = reader.readLine()) != null) {
                   responseContent.append(line);
               }
               reader.close();
           }

       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           connection.disconnect();
       }
       return responseContent;
   }
   
   private void parse(StringBuilder responseContent){
       res = new ArrayList<Stats>();
       JSONArray stats = new JSONArray(responseContent.toString());
       
       for (int i=0;i<stats.length();i++){
           JSONObject stat = stats.getJSONObject(i);
           
           long tConfirmed = stat.getLong("total_confirmed");
           long dDeaths = stat.getLong("daily_deaths");
           long tRecovered = stat.getLong("total_recovered");
           long tActive = stat.getLong("total_active");
           String stringDate = stat.getString("date");
           
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate date = LocalDate.parse(stringDate, formatter);
           
           Stats countryStats = new Stats(tConfirmed, dDeaths, tRecovered, tActive, date);
           res.add(countryStats);
       }
   }

   private static void addCountries(){
       if (C19.countries.isEmpty()){
           StringBuilder responseContent;
           responseContent = connect("http://127.0.0.1:8080/COVID-19/api");
           
           JSONArray result = new JSONArray(responseContent.toString());
           for (int i = 0; i < result.length(); i++) {
               JSONObject country = result.getJSONObject(i);
               C19.countries.put(country.getString("country"),country.getString("iso"));
           }
       }
   }
   
   public static HashMap<String, String> getCountries(){
       if (C19.countries.isEmpty())
           addCountries();

      return C19.countries;
      
   }
   
   public Stats in(String date){
       if (!countryExist){
           System.out.println("Country does not exist in our server");
           return new Stats();
       }
       LocalDate d = this.parseDate(date);
       Stats st = new Stats(d);
       if (d!=null){
           if (res.contains(st)) {
               return res.get(res.indexOf(st));
           }
       }
       return st;
   }
   
   public ArrayList<Stats> between(String d1, String d2){
       if (!countryExist) {
           System.out.println("Country does not exist in our server");
           return null;
       }
       
        LocalDate date1 = this.parseDate(d1);
        LocalDate date2 = this.parseDate(d2);

        if (date1==null || date2==null)
            return null;

        if (date1.isAfter(date2))
            return null;

        
        int peroid = Period.between(date1, date2).getDays();
        
        int date1Position =  res.indexOf(new Stats(date1));
        
        if (date1Position+peroid+1>res.size()){
            System.out.println("error last date is : " + res.get(res.size() - 1).getDate());
            return null;
        }

        ArrayList<Stats> result = new ArrayList(this.res.subList(date1Position, date1Position + peroid+1));
        return result;
        
   }
   
   private LocalDate parseDate(String date){
       LocalDate d=null;
       try {
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           d = LocalDate.parse(date, formatter);
       } catch (DateTimeParseException e) {
           System.out.println("Invalid format, please use yyyy-MM-dd");
       }
       return d;
   }

   public ArrayList<Stats> stats(){
       return res;
   }
   
   
}
