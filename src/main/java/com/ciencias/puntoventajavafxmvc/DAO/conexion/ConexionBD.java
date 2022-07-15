package com.ciencias.puntoventajavafxmvc.DAO.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String username="sa";
    private static final String pass="muso54";
    private static final String bd="pointsalebd";
    private static Connection con;

    public static Connection connection(){
        String cadena = "jdbc:sqlserver://localhost:1433;databaseName="+bd;
        try{
            con = DriverManager.getConnection(cadena,username,pass);
            System.out.println("conexion extablecida");
        } catch(SQLException ex){
            System.out.println(ex.toString());
        }
        return con;
    }

}
