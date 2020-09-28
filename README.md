# C19-API  
This is a COVID-19 API data includes :
confirmed cases, deaths, recovered, active cases 

[Click here to download the Jar file](https://github.com/MedKramti/c19-api/raw/master/dist/c19.jar)  
[You need also org.json](https://github.com/stleary/JSON-java)

##  The data provider 
<br/>

```
http://c19-api.tn/
```

## Methods 

<br/>

| Method   | Return type | Description |
| ------------- | ------------- |------------- |
|***constructor(String*** countryName ***)***|void|request all information of the given country from the data provider|
| static ***getCountries()***  |  HashMap<String, String>  | Returns HashMap that contains country name and iso 2 |
| public ***in(String***  date  ***)*** | Stats  | Returns stats of a specific country in a giving ***date***
| public ***between(String***  date1, ***String*** date2  ***)***|ArrayList < Stats > | Returns stats of a specific country in a giving date range. |
| public ***stats()***|ArrayList < Stats > | Returns all the stats from the first reported case |

## Stats class

- Attributes
    * *private long* totalConfirmed
    * *private long* dailyDeaths
    * *private long* totalRecovered
    * *private long* totalActive
    * *private LocalDate* date
    
- Methods
    * Getters
    * Setters


##  Example : getCountries()

Returns all countries and their ISO2 code.
```java
HashMap<String, String> countries;
countries = C19.getCountries();
for(String country : countries.keySet())
    System.out.println(country + " " + countries.get(country));
```

## Output

```
...
Jordan JO
Tunisia TN
united-states USA
Kenya KE
...
```


## Example : in(String date)

Returns all countries and their ISO2 code.
```java
C19 Tunisia = new C19("Tunisia");
System.out.println(Tunisia.in("2020-09-26"));
```
## Output

```
Stats{totalConfirmed=14392, dailyDeaths=11, totalRecovered=5032, totalActive=9169, date=2020-09-26}
```
## Example 2 : in(String date)

<br/>

```java
C19 Tunisia = new C19("Tunisia");
Stats stats = Tunisia.in("2020-09-26");
System.out.println("confirmed = "+ stats.getTotalConfirmed());
System.out.println("daily deaths = "+ stats.getDailyDeaths());
System.out.println("recovered = "+ stats.getTotalRecovered());
System.out.println("active = "+ stats.getTotalActive());
System.out.println("date = "+ stats.getDate());
```
## Output
```
confirmed = 14392
daily deaths = 11
recovered = 5032
active = 9169
date = 2020-09-26
```

## Example : between(String date1, String date2)

<br/>

```java
C19 Tunisia = new C19("Tunisia");
ArrayList<Stats> res;
res = Tunisia.between("2020-09-24","2020-09-25");
for (Stats stat : res)
    System.out.println(stat);
```
## Output
```
Stats{totalConfirmed=13305, dailyDeaths=6, totalRecovered=5032, totalActive=8093, date=2020-09-24}
Stats{totalConfirmed=13305, dailyDeaths=0, totalRecovered=5032, totalActive=8093, date=2020-09-25}
```

## Example : stats()

<br/>

```java
C19 Tunisia = new C19("Tunisia");
ArrayList<Stats> res;
res = Tunisia.stats();
for (Stats stat : res)
   System.out.println(stat);
```

## Output
```
Stats{totalConfirmed=1, dailyDeaths=0, totalRecovered=0, totalActive=1, date=2020-03-04}
Stats{totalConfirmed=1, dailyDeaths=0, totalRecovered=0, totalActive=1, date=2020-03-05}
Stats{totalConfirmed=1, dailyDeaths=0, totalRecovered=0, totalActive=1, date=2020-03-06}
...
Stats{totalConfirmed=14392, dailyDeaths=11, totalRecovered=5032, totalActive=9169, date=2020-09-26}
```

#### By
[Mohamed kramti](https://github.com/MedKramti)


Please contact mohamedkramti99@gmail.com with any questions.






