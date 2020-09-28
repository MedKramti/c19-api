package tn.c19_api;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Mohamed Kramti
 */
public class Stats {
    private long totalConfirmed;
    private long dailyDeaths;
    private long totalRecovered;
    private long totalActive;
    private LocalDate date;

    
   
    public Stats(long totalConfirmed, long dailyDeaths, long totalRecovered, long totalActive, LocalDate date) {
        this.totalConfirmed = totalConfirmed;
        this.dailyDeaths = dailyDeaths;
        this.totalRecovered = totalRecovered;
        this.totalActive = totalActive;
        this.date = date;
    }
    public Stats() {
        totalConfirmed = dailyDeaths = totalRecovered = totalActive = 0;
        date = null;
    }
    
    public Stats(LocalDate date){
        this.date = date;
        totalConfirmed = dailyDeaths = totalRecovered = totalActive = 0;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stats other = (Stats) obj;
        if (!this.date.equals(other.date)) {
            return false;
        }
        return true;
    }

    public long getTotalConfirmed() {
        return totalConfirmed;
    }

    public long getDailyDeaths() {
        return dailyDeaths;
    }

    public long getTotalRecovered() {
        return totalRecovered;
    }

    public long getTotalActive() {
        return totalActive;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTotalConfirmed(long totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public void setDailyDeaths(long dailyDeaths) {
        this.dailyDeaths = dailyDeaths;
    }

    public void setTotalRecovered(long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public void setTotalActive(long totalActive) {
        this.totalActive = totalActive;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stats{" + "totalConfirmed=" + totalConfirmed + ", dailyDeaths=" + dailyDeaths + ", totalRecovered=" + totalRecovered + ", totalActive=" + totalActive + ", date=" + date + '}';
    }
    
    
    
}
