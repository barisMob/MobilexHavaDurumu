package com.example.mobilexhavadurumu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("main")
    @Expose
    Main response;
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("id")

    public Main getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResponse(Main response) {
        this.response = response;
    }

    public class Main {

        String temp;
        String pressure;
        String humidity;
        String temp_min;
        String temp_max;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(String temp_min) {
            this.temp_min = temp_min;
        }

        public String getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(String temp_max) {
            this.temp_max = temp_max;
        }
    }
}
