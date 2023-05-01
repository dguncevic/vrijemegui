/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vrijemegui;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author 19par
 */
public class Updater {
    
      public static double updaterX;
      public static double updaterY;

    public double getX() {
        return updaterX;
    }

    public void setX(double x) {
        this.updaterX = x;
    }

    public double getY() {
        return updaterY;
    }

    public void setY(double y) {
        this.updaterY = y;
    }
    
  
    
    public static String getJson()throws Exception{
        
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+updaterX+"&lon="+updaterY+"&appid=761cf95146c616290e3538bde595e3d7&units=metric");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            System.out.println("Can't fetch the page...");
            System.exit(1);
        }

        String result = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }
        scanner.close();
        return result;
    }
       
    
}
