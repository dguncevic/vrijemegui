/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beans;

/**
 *
 * @author 19par
 */
public class Main {

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }
    
    private double temp;
    private double feels_like;
    
    @Override
    public String toString(){
        
        return "Temp: "+temp+"\nFeels like: "+feels_like;
    }
    
    
}
