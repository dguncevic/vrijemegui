
package com.mycompany.beans;

import java.util.ArrayList;


public class MyWeather {

    private ArrayList<weather> weather;
    private Main main;
    private String name;

    public ArrayList<weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
