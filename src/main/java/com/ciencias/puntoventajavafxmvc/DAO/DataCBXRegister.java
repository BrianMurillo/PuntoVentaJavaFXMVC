package com.ciencias.puntoventajavafxmvc.DAO;

public class DataCBXRegister {

    public static Integer[] arrayDays(){
        Integer [] days = new Integer[31];
        for (int i=0; i<days.length; i++){
            days[i] = i+1;
        }
        return days;
    }

    public static String[] arrayMouths(){
        String[] months = {"January","February","March","April","May","June","July",
                "August","September","October","November","December"};
        return  months;
    }

    public static Integer[] arrayYears(){
        Integer[] years = new Integer[70];
        int contYears = 1;
        for (int i=0; i<70 ; i++){
            years[i] = 1944 + contYears;
            contYears++;
        }
        return years;
    }
}